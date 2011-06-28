package org.sukrupa.need;




import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.need.BigNeed;


import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;

@Repository
public class BigNeedRepository {

  // private static final Logger LOG = Logger.getLogger(StudentService.class);


    private SessionFactory sessionFactory;

    @Autowired
    public BigNeedRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
         }

    public void put(BigNeed bigNeed) {
        session().saveOrUpdate(bigNeed);
        //session().flush();
    }

    public BigNeed update(BigNeed bigNeed) {
        session().save(bigNeed);
        session().flush();
        return bigNeed;
    }



    private Query query(String hql) {
        return session().createQuery(hql);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }


    public void save(BigNeed bigNeed) {
        session().save(bigNeed);
    }

    public BigNeed load(int priority) {
         return (BigNeed) session().load(BigNeed.class, priority);
    }

    public List<BigNeed> list() {
         return session().createCriteria(BigNeed.class).addOrder(Order.asc("priority")).list();
    }
}
