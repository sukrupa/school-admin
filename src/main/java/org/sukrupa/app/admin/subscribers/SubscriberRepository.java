package org.sukrupa.app.admin.subscribers;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubscriberRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SubscriberRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private Query query(String hibernateQueryLanguage) {
        return session().createQuery(hibernateQueryLanguage);
    }


    @SuppressWarnings("unchecked")
    public List<Subscriber> getList() {
         return (List<Subscriber>) query("from Subscriber order by SUBSCRIBERNAME").list();
    }

    public void addSubscriber(Subscriber subscriber){
        session().save(subscriber);
    }

    public Subscriber findByName(String subscriberName){
        return (Subscriber) query("from Subscriber where SUBSCRIBERNAME = ?").setParameter(0, subscriberName).uniqueResult();
    }



}
