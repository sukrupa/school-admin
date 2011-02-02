package org.sukrupa.student;

import org.hibernate.classic.Session;

public class StudentCreatorHelper {
    final HibernateHelper hibernateHelper = new HibernateHelper();

    public void save(Session session, Student... students) {
        for (Object student : students) {
            session.save(student);
        }
        hibernateHelper.flushHibernateSessionToForceReload(session);
    }
}