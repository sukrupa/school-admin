package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public EventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Event> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Event.class).list();
    }

    public void save(Event event) {
        sessionFactory.getCurrentSession().save(event);
    }
}
