package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BigNeedRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BigNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    public void put(BigNeed bigNeed) {
        session().saveOrUpdate(bigNeed);
    }

    public BigNeed findByName(String itemName) {
        return (BigNeed) query("from BigNeed where ITEM_NAME = ?").setParameter(0, itemName).uniqueResult();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    private Query query(String hibernateQueryLanguage) {
        return session().createQuery(hibernateQueryLanguage);
    }

    public List<BigNeed> getList() {
         return (List<BigNeed>) query("from BigNeed").list();
    }
}