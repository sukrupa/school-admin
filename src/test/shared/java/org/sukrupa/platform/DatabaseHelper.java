package org.sukrupa.platform;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHelper {

    @Autowired
    private SessionFactory sessionFactory;

    public Transaction beginTransaction() {
        return session().beginTransaction();
    }

    public void commit(Transaction transaction) {
        transaction.commit();
    }

    public void save(Object... objects) {
        for (Object object : objects) {
            session().save(object);
        }
        flushHibernateSessionToForceReload();
    }

    private void flushHibernateSessionToForceReload() {
        session().flush();
        session().clear();
    }

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
}
