package org.sukrupa.student;


//import org.mockito.stubbing.OngoingStubbing;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemEventLogRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SystemEventLogRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SystemEventLog find(String systemEvent) {
        Criteria criteria = session().createCriteria(SystemEventLog.class).add(Restrictions.eq("event",systemEvent));
        return (SystemEventLog) criteria.uniqueResult();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
