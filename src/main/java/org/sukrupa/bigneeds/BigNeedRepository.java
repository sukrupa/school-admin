package org.sukrupa.bigneeds;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.needs.Need;
import org.sukrupa.needs.NeedRepository;

import java.util.List;

@Repository
public class BigNeedRepository extends NeedRepository<BigNeed> {

    @Autowired
    public BigNeedRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public BigNeed findByName(String itemName) {
        return (BigNeed) query("from BigNeed where ITEM_NAME = ?").setParameter(0, itemName).uniqueResult();
    }

    @Override
    public BigNeed getNeedById(long id) {
        return (BigNeed) session().get(BigNeed.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<BigNeed> getList() {
        return (List<BigNeed>) query("from BigNeed order by priority").list();
    }
}
