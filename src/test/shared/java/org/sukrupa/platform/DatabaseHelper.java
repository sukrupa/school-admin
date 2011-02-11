package org.sukrupa.platform;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

import static java.lang.String.format;

@Component
public class DatabaseHelper {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private Object[] trackedObjects = new Object[]{};

    public void save(Object... objects) {
        for (Object object : objects) {
            session().save(object);
        }
        flushHibernateSessionToForceReload();
    }

    public void saveAndCommit(final Object... objects) {
        transactionTemplate().execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                save(objects);
            }
        });
        track(objects);
    }

    public void deleteAndCommit(final Object... savedObjects) {
        transactionTemplate().execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                for (Object savedObject : savedObjects) {
                    delete(session().merge(savedObject));
                }
            }
        });
    }

    public void deleteAllFromTables(String... tables) {
        for (String table : tables) {
            jdbcTemplate().execute(format("delete from %s", table));
        }
    }


    public void delete(Object... savedObjects) {
        for (Object savedObject : savedObjects) {
            session().delete(savedObject);
        }
    }

    public void flushHibernateSessionToForceReload() {
        session().flush();
        session().clear();
    }

    public void deleteAllCreatedObjects() {
        deleteAndCommit(trackedObjects);
        resetTrackedObjects();
    }

    private void resetTrackedObjects() {
        trackedObjects = new Object[]{};
    }

    private TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }


    private void track(Object[] objects) {
        trackedObjects = ArrayUtils.addAll(trackedObjects, objects);
    }
}
