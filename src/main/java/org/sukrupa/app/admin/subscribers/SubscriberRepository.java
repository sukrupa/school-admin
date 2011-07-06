package org.sukrupa.app.admin.subscribers;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.bigneeds.BigNeedRepository;

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

    public List<Subscriber> getList(){

        return (List<Subscriber>) query("from Subscriber").list();
    }
}
