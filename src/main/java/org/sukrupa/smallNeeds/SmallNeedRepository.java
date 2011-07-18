package org.sukrupa.smallNeeds;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.needs.Need;
import org.sukrupa.needs.NeedRepository;

import java.util.List;

@Component
public class SmallNeedRepository extends NeedRepository<SmallNeed>{

    @Autowired
    public SmallNeedRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public SmallNeed findByName(String itemName) {
        return (SmallNeed) query("from SmallNeed where ITEM_NAME = ?").setParameter(0, itemName).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public SmallNeed getNeedById(long id) {
        return (SmallNeed) session().get(SmallNeed.class, id);
        //return (SmallNeed) query("from SmallNeed where ID = ?").setParameter(0, id).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SmallNeed> getList() {
       return (List<SmallNeed>) query("from SmallNeed order by priority").list();
    }



}
