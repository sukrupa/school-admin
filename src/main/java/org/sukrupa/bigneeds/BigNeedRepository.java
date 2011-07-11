package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Repository
public class BigNeedRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BigNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean checkForPrioritization(BigNeed bigNeed){
       return getList().size() >= bigNeed.getPriority() ? true : false;
    }

    public void  addOrEditBigNeed(BigNeed bigNeed) {
       List<BigNeed> unModifiedBigNeedList;

        if(checkForPrioritization(bigNeed)){
                // session().saveOrUpdate(bigNeed);

                unModifiedBigNeedList=returnUnmodifiedListOfBigNeeds(getList(),bigNeed.getPriority());
                ListIterator<BigNeed> bigNeedListIterator = unModifiedBigNeedList.listIterator();
                adjustThePriorities(bigNeedListIterator);



        }
        else{
            session().saveOrUpdate(bigNeed);
        }
        session().saveOrUpdate(bigNeed);
    }

    public void adjustThePriorities(ListIterator<BigNeed> bigNeedListIterator) {
        BigNeed tempBigNeed;
        while (bigNeedListIterator.hasNext()){
            tempBigNeed = bigNeedListIterator.next();
         //   System.out.println("*********"+tempBigNeed.getItemName()+"*****"+tempBigNeed.getPriority());
            tempBigNeed.setPriority(tempBigNeed.getPriority()+1);
         //   System.out.println("New prio*****"+tempBigNeed.getPriority());
            session().update(tempBigNeed);
        }
    }

    public void editBigNeed(BigNeed bigNeed) {

             List<BigNeed> bigNeedList=getList();

             for(BigNeed need : bigNeedList)
             {
                 if(need.getId() == bigNeed.getId())
                 {
                     need.setPriority(bigNeed.getPriority());
                     need.setItemName(bigNeed.getItemName());
                     need.setCost(bigNeed.getCost());
                 }
                 else
                     if(need.getPriority() >= bigNeed.getPriority())
                 {
                     int priority = need.getPriority()+1;
                     need.setPriority(priority);
                 }
             }

            session().flush();

    }
    private List<BigNeed> returnUnmodifiedListOfBigNeeds(List<BigNeed> bigNeedList, int index){
         return bigNeedList.subList(index-1,bigNeedList.size());
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
}
