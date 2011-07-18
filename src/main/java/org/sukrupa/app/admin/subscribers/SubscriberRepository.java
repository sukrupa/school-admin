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
        if (duplicateNameAndEmailExists(subscriber)) return;
        session().save(subscriber);
    }

    public Subscriber findByName(String subscriberName){
        return (Subscriber) query("from Subscriber where SUBSCRIBERNAME = ?").setParameter(0, subscriberName).uniqueResult();
    }

     public Subscriber findById(int id){
        return (Subscriber) query("from Subscriber where id = ?").setParameter(0, id).uniqueResult();
    }

    public Subscriber findByNameAndEmail(String subscriberName, String subscriberEmail){
          Query query = session().createQuery("from Subscriber where SUBSCRIBERNAME = :subcriberName and SUBSCRIBEREMAIL = :subcriberEmail");
         query.setParameter("subcriberName", subscriberName);
         query.setParameter("subcriberEmail", subscriberEmail);
         return (Subscriber) query.uniqueResult();
    }

    public void deleteSubscriber(Subscriber subscriber) {
        session().delete(subscriber);
    }

    public boolean duplicateNameAndEmailExists(Subscriber subscriber){
        Subscriber retrievedSubscriber=findByNameAndEmail(subscriber.getSubscriberName(),subscriber.getSubscriberEmail());
        if(retrievedSubscriber == null) return false;
        else return true;

    }


}
