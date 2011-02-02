package org.sukrupa.platform;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHelper {

    @Autowired
    private SessionFactory sessionFactory;

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

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
