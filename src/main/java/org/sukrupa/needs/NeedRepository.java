package org.sukrupa.needs;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.sukrupa.bigneeds.BigNeed;

import java.util.List;

public abstract class NeedRepository<N extends Need> {

    protected SessionFactory sessionFactory;

    public NeedRepository() {
    }

    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    public NeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addNeed(N need, int newPriority) {
       int lastPriority = getList().size() + 1;
        need.setPriority(lastPriority);
        session().save(need);
        editNeed(need, newPriority);
    }

    public void editNeed(N updatedNeed, int newPriority) {
        List<N> needs = getList();
        if(updatedNeed.getPriority()!=newPriority){
            needs.remove(updatedNeed);
            newPriority=Math.min(needs.size()+1,newPriority);
            saveList(adjustPriorities(needs, updatedNeed, newPriority));
        } else {
            session().saveOrUpdate(updatedNeed);
        }
    }

    private void saveList(List<N> needs) {
        for (Need need : needs) {
            session().saveOrUpdate(need);
        }
    }

    private List<N> adjustPriorities(List<N> needs, N newOrEditedNeed, int newPriority) {
        int samePriorityItemIndex = findTheItemThatCurrentlyHasTheSamePriorityAsTheNewItem(needs, newPriority);
        int indexAtWhichToAddTheNewItem = findPriorityIndexForTheNewItem(newOrEditedNeed, newPriority, samePriorityItemIndex);
        newOrEditedNeed.setPriority(newPriority);
        needs.add(indexAtWhichToAddTheNewItem, newOrEditedNeed);

        needs = updatePrioritiesOfAllItems(needs);
        return needs;
    }

    public List<N> updatePrioritiesOfAllItems(List<N> needs) {
        int samePriorityItemIndex;
        for (samePriorityItemIndex = 0; samePriorityItemIndex < needs.size(); samePriorityItemIndex++) {
            needs.get(samePriorityItemIndex).setPriority(samePriorityItemIndex + 1);
        }
        return needs;
    }

    private int findPriorityIndexForTheNewItem(Need updatedNeed, int newPriority, int samePriorityItemIndex) {
        int indexAtWhichToAddItem;
        if(updatedNeed.getPriority() >= newPriority){
            indexAtWhichToAddItem = samePriorityItemIndex;
        } else {
            indexAtWhichToAddItem = samePriorityItemIndex + 1;
        }
        return indexAtWhichToAddItem;
    }

    private int findTheItemThatCurrentlyHasTheSamePriorityAsTheNewItem(List<N> needs, int newPriority) {
        for (Need need : needs) {
            if (need.getPriority() == newPriority) {
                return needs.indexOf(need);
            }
        }
        return needs.size();
    }

    public abstract Need findByName(String itemName);

    protected Query query(String hibernateQueryLanguage) {
        return session().createQuery(hibernateQueryLanguage);
    }

    public abstract List<N> getList();

    public abstract Need getNeedById(long id);

    public void delete(Need need) {
        session().delete(need);

        int lastPriorityOfAllNeeds = getList().size() + 1;
        int priorityOfDeletedNeed = need.getPriority();

        if (priorityOfDeletedNeed != lastPriorityOfAllNeeds) {
            List<N> needs = adjustingPrioritiesForDelete();
            saveList(needs);
        }

    }

    private List<N> adjustingPrioritiesForDelete() {
        List<N>needs=getList();
        int i;
         for (i = 0; i < needs.size(); i++) {
            needs.get(i).setPriority(i + 1);
         }
        return needs;
    }
}
