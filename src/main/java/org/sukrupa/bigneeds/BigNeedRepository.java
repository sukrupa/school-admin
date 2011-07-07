package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.loader.custom.Return;
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

    public boolean checkForPrioritization(BigNeed bigNeed){
       return getList().size() > bigNeed.getPriority() ? true : false;
    }

    public void put(BigNeed bigNeed) {
        /*List<BigNeed> bigNeedList = getList();
        List<BigNeed> unModifiedBigNeedList;
        if(checkForPrioritization(bigNeed)){
                bigNeedList.subList(0,bigNeed.getPriority()-1);
        }
        else{*/
            session().saveOrUpdate(bigNeed);
        //}
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

    @SuppressWarnings("unchecked")
    public List<BigNeed> getList() {
         return (List<BigNeed>) query("from BigNeed order by priority").list();
    }

    public BigNeed getBigNeed(long id) {
        return (BigNeed) session().get(BigNeed.class,id);
    }

    public void delete(BigNeed bigNeed) {
        session().delete(bigNeed);
    }

    public void edit(BigNeed bigNeed) {
        session().contains(bigNeed);
    }
}
