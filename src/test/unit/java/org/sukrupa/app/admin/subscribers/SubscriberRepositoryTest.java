//package org.sukrupa.app.admin.subscribers;
//
//
//import org.junit.Before;
//import org.hibernate.SessionFactory;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.sukrupa.platform.db.HibernateSession;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class SubscriberRepositoryTest {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    private HibernateSession hibernateSession;
//
//    private SubscriberRepository subscriberRepository= new SubscriberRepository(sessionFactory);
//
//    @Before
//    public void setUp() throws Exception {
//        subscriberRepository = new SubscriberRepository(sessionFactory);
//    }
//
//     @Test
//     public void shouldSaveASubscriber(){
//         Subscriber subscriber =new Subscriber("Carlos","Carlos@gmail.com");
//         subscriberRepository.put(subscriber);
//
//         Subscriber retrievedSubscriber = subscriberRepository.findByName("Carlos");
//         assertThat(retrievedSubscriber.getSubscriberName(), is(subscriber.getSubscriberName())  );
//
//
//     }
//
//
//}