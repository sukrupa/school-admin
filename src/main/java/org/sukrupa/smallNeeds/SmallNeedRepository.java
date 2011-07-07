package org.sukrupa.smallNeeds;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.sukrupa.bigneeds.BigNeed;

public class SmallNeedRepository {

    private  SessionFactory sessionFactory;
    public SmallNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }
      private Session session() {
        return sessionFactory.getCurrentSession();
    }

     private Query query(String hibernateQueryLanguage) {
        return session().createQuery(hibernateQueryLanguage);
    }

    public void put(SmallNeed smallNeed) {
        session().saveOrUpdate(smallNeed);
    }

    public SmallNeed findByName(String itemName) {
        return (SmallNeed) query("from SmallNeed where ITEM_NAME = ?").setParameter(0, itemName).uniqueResult();
    }



}
