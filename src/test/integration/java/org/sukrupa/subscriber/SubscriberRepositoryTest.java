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
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class SubscriberRepositoryTest {

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

    @Test
    public void shouldCheckIfSubscriberNameAndEmailExists(){
        subscriberRepository.addSubscriber(carlos);
        String subscriberName=carlos.getSubscriberName();
        String subscriberEmail=carlos.getSubscriberEmail();

        Subscriber retreivedSubscriber= subscriberRepository.findByNameAndEmail(subscriberName, subscriberEmail);

       assertThat(retreivedSubscriber.getSubscriberName(), is(carlos.getSubscriberName()) );
        assertThat(retreivedSubscriber.getSubscriberEmail(),is(carlos.getSubscriberEmail()));
    }

    @Test
    public void shouldNotAddDuplicateSubscriber(){
        Subscriber carlosTwin=new Subscriber(carlos.getSubscriberName(),carlos.getSubscriberEmail());

        subscriberRepository.addSubscriber(carlos);
         assertThat(subscriberRepository.getList(),hasItem(carlos));
        
        subscriberRepository.addSubscriber(carlosTwin);
        subscriberRepository.deleteSubscriber(carlos);
        assertFalse(subscriberRepository.duplicateNameAndEmailExists(carlos));


    }



}