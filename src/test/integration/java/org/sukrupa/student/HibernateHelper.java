package org.sukrupa.student;

import org.hibernate.classic.Session;

public class HibernateHelper {
    public void flushHibernateSessionToForceReload(Session session) {
        session.flush();
        session.clear();
    }
}