package org.sukrupa.platform;

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

    public void save(Object... objects) {
        for (Object object : objects) {
            session().save(object);
        }
        flushHibernateSessionToForceReload();
    }

    public void saveAndCommit(final Object... objects) {
        new TransactionTemplate(transactionManager).execute(new TransactionCallbackWithoutResult() {
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                save(objects);
            }
        });
    }

    private void flushHibernateSessionToForceReload() {
        session().flush();
        session().clear();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
