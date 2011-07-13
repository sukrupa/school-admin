package org.sukrupa.subscriber;



import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class SubscriberRepositoryTest {

//    @Mock
//    private SessionFactory sessionFactory;
//    @Mock
//    private Session session;
//    @Mock
//    private Query firstQuery;
//    @Mock
//    private Query secondQuery;
//
//    private SubscriberRepository subscriberRepository;
//
//    @Before
//    public void setUp() throws Exception {
//        initMocks(this);
//        subscriberRepository = new SubscriberRepository(sessionFactory);
//    }
//
//
//
//    @Test
//     public void shouldSaveASubscriber(){
//        Subscriber subscriber = new Subscriber("Carlos","Carlos@gmail.com");
//
//        when(sessionFactory.getCurrentSession()).thenReturn(session);
//        when(session.createQuery("from Subscriber where SUBSCRIBERNAME = ?")).thenReturn(firstQuery);
//        when(firstQuery.setParameter(0, (Object)"Carlos")).thenReturn(secondQuery);
//        when(secondQuery.uniqueResult()).thenReturn(subscriber);
//
//        Subscriber retrievedSubscriber = subscriberRepository.findByName("Carlos");
//
//         assertThat(retrievedSubscriber.getSubscriberName(), is(subscriber.getSubscriberName()) );
//         assertThat(retrievedSubscriber.getSubscriberEmail(),is(subscriber.getSubscriberEmail()));
//
//     }
    public final Subscriber carlos = new Subscriber("carlos","carlos@gmail.com");
    public final Subscriber dude = new Subscriber("dude","dude@gmail.com");

    @Autowired
    private SessionFactory sessionFactory;

    private SubscriberRepository subscriberRepository;

    @Before
    public void setUp() throws Exception {
        subscriberRepository = new SubscriberRepository(sessionFactory);
    }

    @Test
    public void shouldSaveASubscriber(){
        subscriberRepository.addSubscriber(carlos);
        int id= carlos.getId();

        Subscriber retreivedSubscriber = subscriberRepository.findById(id);
        assertThat(retreivedSubscriber,is(carlos));
        assertThat(retreivedSubscriber.getSubscriberName(), is(carlos.getSubscriberName()) );
        assertThat(retreivedSubscriber.getSubscriberEmail(),is(carlos.getSubscriberEmail()));

        subscriberRepository.addSubscriber(dude);
        retreivedSubscriber = subscriberRepository.findByName("dude");
        assertThat(retreivedSubscriber,is(dude));

    }

    @Test
    public void shouldDeleteASubscriber(){
        subscriberRepository.addSubscriber(carlos);
        assertThat(subscriberRepository.getList(),hasItem(carlos));
        subscriberRepository.deleteSubscriber(carlos);
        assertThat(subscriberRepository.getList(),not(hasItem(carlos)));
    }



}