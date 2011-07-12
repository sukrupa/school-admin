package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ListIterator;

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
        bigNeeds.remove(updatedBigNeed);
        saveList(adjustPriorities(bigNeeds, updatedBigNeed, newPriority));
    }

    private void saveList(List<BigNeed> bigNeeds) {
        for (BigNeed bigNeed : bigNeeds) {
            session().saveOrUpdate(bigNeed);
        }
    }

    private List<BigNeed> adjustPriorities(List<BigNeed> bigNeeds, BigNeed updatedBigNeed, int newPriority) {
        int i = 0;
        for (; i < bigNeeds.size(); i++) {
            if (bigNeeds.get(i).getPriority() == newPriority) {
                break;
            }
        }
        bigNeeds.add(updatedBigNeed.getPriority() >= newPriority ? i : i + 1, updatedBigNeed);
        for (i = 0; i < bigNeeds.size(); i++) {
            bigNeeds.get(i).setPriority(i + 1);
        }
        return bigNeeds;
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
    }
}
