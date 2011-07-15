package org.sukrupa.app.httpcommunicationservices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class HTTPSubscriberControllerTest {
    @Mock
    private SubscriberRepository subscriberRepository;

    private HTTPSubscriberController httpSubscriberController;

    private final String subscriberName = "dude";
    private final String subscriberEmail = "dude@tw.com";

    private final Subscriber dude = new Subscriber(subscriberName,subscriberEmail);
    @Test
    public void shouldReturnSuccessWhenSubscriberIsAdded(){
        String responseString =  httpSubscriberController.addSubscriberFromWebsite(subscriberName, subscriberEmail );
        assertThat(responseString, is("Success"));
    }

    @Test
   public void shouldCallAddSubscriberMethodOfSubscriberRepository(){
        httpSubscriberController.addSubscriberFromWebsite(subscriberName,subscriberEmail);
        verify(subscriberRepository).addSubscriber(dude);
    }

    @Before
    public void setUp(){
        initMocks(this);
        httpSubscriberController = new HTTPSubscriberController(subscriberRepository);
    }

}
