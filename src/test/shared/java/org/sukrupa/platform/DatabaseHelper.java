package org.sukrupa.platform;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public class DatabaseHelper {

    @Autowired
    private SessionFactory sessionFactory;

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
                delete(savedObjects);
            }
        });
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
    }


    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager);
    }

    private void track(Object[] objects) {
        trackedObjects = ArrayUtils.addAll(trackedObjects, objects);
    }
}
