package org.sukrupa.event;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.student.Student;

import java.util.List;
import java.util.Set;

@Repository
public class EventRepository {

    private static final String STUDENT_ID = "studentId";
    static final String ATTENDEES_SEPARATOR = ",";

    private SessionFactory sessionFactory;
    private Set<Student> studentListFromDB;

    @Autowired
    public EventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Event event) {
        session().save(event);
    }

    public List<Event> getAll() {
        return session().createCriteria(Event.class).list();
    }

    public Event getEvent(int eventId) {
        return (Event) session().createCriteria(Event.class).add(Restrictions.eq("id", eventId)).uniqueResult();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
