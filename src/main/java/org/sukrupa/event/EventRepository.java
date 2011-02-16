package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public EventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Event event) {
        session().save(event);
    }

    public List<Event> list() {
        return session().createCriteria(Event.class).addOrder(Order.desc("date")).list();
    }

    public Event load(Integer eventId) {
        return (Event) session().load(Event.class, eventId);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
