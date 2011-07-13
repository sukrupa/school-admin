package org.sukrupa.app.admin.subscribers;


import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SubscriberRepositoryTest {

    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Query firstQuery;
    @Mock
    private Query secondQuery;

    private SubscriberRepository subscriberRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subscriberRepository = new SubscriberRepository(sessionFactory);
    }



    @Test
     public void shouldSaveASubscriber(){
        Subscriber subscriber = new Subscriber("Carlos","Carlos@gmail.com");

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createQuery("from Subscriber where SUBSCRIBERNAME = ?")).thenReturn(firstQuery);
        when(firstQuery.setParameter(0, (Object)"Carlos")).thenReturn(secondQuery);
        when(secondQuery.uniqueResult()).thenReturn(subscriber);

        Subscriber retrievedSubscriber = subscriberRepository.findByName("Carlos");

         assertThat(retrievedSubscriber.getSubscriberName(), is(subscriber.getSubscriberName()) );
         assertThat(retrievedSubscriber.getSubscriberEmail(),is(subscriber.getSubscriberEmail()));

     }
}