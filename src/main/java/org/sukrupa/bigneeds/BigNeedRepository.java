package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BigNeedRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public BigNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addBigNeed(BigNeed bigNeed, int newPriority) {
        bigNeed.setPriority(getList().size()+1);
        editBigNeed(bigNeed, newPriority);
    }

    public void editBigNeed(BigNeed updatedBigNeed, int newPriority) {
        List<BigNeed> bigNeeds = getList();
        if(updatedBigNeed.getPriority()!=newPriority){
            bigNeeds.remove(updatedBigNeed);
            newPriority=Math.min(bigNeeds.size()+1,newPriority);
            saveList(adjustPriorities(bigNeeds, updatedBigNeed, newPriority));
        } else {
            session().saveOrUpdate(updatedBigNeed);
        }
    }

    private void saveList(List<BigNeed> bigNeeds) {
        for (BigNeed bigNeed : bigNeeds) {
            session().saveOrUpdate(bigNeed);
        }
    }

    private List<BigNeed> adjustPriorities(List<BigNeed> bigNeeds, BigNeed newOrEditedBigNeed, int newPriority) {
        int samePriorityItemIndex = findTheItemThatCurrentlyHasTheSamePriorityAsTheNewItem(bigNeeds, newPriority);
        int indexAtWhichToAddTheNewItem = findPriorityIndexForTheNewItem(newOrEditedBigNeed, newPriority, samePriorityItemIndex);

        bigNeeds.add(indexAtWhichToAddTheNewItem, newOrEditedBigNeed);

        updatePrioritiesOfAllItems(bigNeeds);
        return bigNeeds;
    }

    private void updatePrioritiesOfAllItems(List<BigNeed> bigNeeds) {
        int samePriorityItemIndex;
        for (samePriorityItemIndex = 0; samePriorityItemIndex < bigNeeds.size(); samePriorityItemIndex++) {
            bigNeeds.get(samePriorityItemIndex).setPriority(samePriorityItemIndex + 1);
        }
    }

    private int findPriorityIndexForTheNewItem(BigNeed updatedBigNeed, int newPriority, int samePriorityItemIndex) {
        int indexAtWhichToAddItem;
        if(updatedBigNeed.getPriority() >= newPriority){
            indexAtWhichToAddItem = samePriorityItemIndex;
        } else {
            indexAtWhichToAddItem = samePriorityItemIndex + 1;
        }
        return indexAtWhichToAddItem;
    }

    private int findTheItemThatCurrentlyHasTheSamePriorityAsTheNewItem(List<BigNeed> bigNeeds, int newPriority) {
        int i = 0;
        for (; i < bigNeeds.size(); i++) {
            if (bigNeeds.get(i).getPriority() == newPriority) {
                break;
            }
        }
        return i;
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
        return (BigNeed) session().get(BigNeed.class, id);
    }

    public void delete(BigNeed bigNeed) {
        session().delete(bigNeed);
        List<BigNeed> bigNeeds=adjustingPrioritiesForDelete();
        saveList(bigNeeds);

    }

    private List<BigNeed> adjustingPrioritiesForDelete() {
        List<BigNeed>bigNeeds=getList();
        int i;
         for (i = 0; i < bigNeeds.size(); i++) {
            bigNeeds.get(i).setPriority(i + 1);
         }
        return bigNeeds;
    }
}
