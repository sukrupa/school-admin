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

    public Event save(Event event) {
        session().save(event);
        return event;
    }

    public Event update(Event event) {
        session().save(event);
        session().flush();
        return event;
    }

    @SuppressWarnings("unchecked")
    public List<Event> list() {
        return session().createCriteria(Event.class).addOrder(Order.desc("endDate")).list();
    }

    public Event load(Integer eventId) {
        return (Event) session().load(Event.class, eventId);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public Event findByTitle(String title) {
        return (Event) session().createQuery("from Event where title = ?").setParameter(0, title).uniqueResult();
    }
}
