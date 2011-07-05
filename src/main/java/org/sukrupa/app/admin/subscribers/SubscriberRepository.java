package org.sukrupa.app.admin.subscribers;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.bigneeds.BigNeedRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 7/5/11
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SubscriberRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SubscriberRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
}
