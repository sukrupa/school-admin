package org.sukrupa.smallNeeds;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.bigneeds.BigNeed;

import java.util.List;

@Component
public class SmallNeedRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SmallNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @SuppressWarnings("unchecked")
    public List<SmallNeed> getList() {
        return (List<SmallNeed>) query("from SmallNeed order by priority").list();
    }

    public SmallNeed getSmallNeed(long id) {
        return (SmallNeed) query("from SmallNeed where ID = ?").setParameter(0, id).uniqueResult();
    }

    public void delete(Object smallNeed) {
        session().delete(smallNeed);
    }


}
