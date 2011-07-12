package org.sukrupa.app.admin.subscribers;


import org.junit.Before;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.HibernateSession;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class SubscriberRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private SubscriberRepository subscriberRepository;

    @Before
    public void setUp() throws Exception {
        subscriberRepository = new SubscriberRepository(sessionFactory);
    }

     @Test
     public void shouldSaveASubscriber(){
         Subscriber subscriber =new Subscriber("Carlos","Carlos@gmail.com");
         subscriberRepository.addSubscriber(subscriber);

        Subscriber retrievedSubscriber = subscriberRepository.findByName("Carlos");

         assertThat(retrievedSubscriber.getSubscriberName(), is(subscriber.getSubscriberName()) );
         assertThat(retrievedSubscriber.getSubscriberEmail(),is(subscriber.getSubscriberEmail()));

     }

    //shouldDeleteSubscriber
    //shouldGetSubscriberByID
    //shouldGetListOfSubscribers
    


}