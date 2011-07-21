package org.sukrupa.app.httpcommunicationservices;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class HTTPSubscriberControllerTest {

    @Mock
    private SubscriberRepository subscriberRepository;

    private HTTPSubscriberController httpSubscriberController;

    private final String subscriberName = "dude";
    private final String subscriberEmail = "dude@tw.com";

    @Mock
    Subscriber subscriber;

    @Test
    public void shouldReturnSuccessWhenSubscriberIsAdded() {
        when(subscriber.getSubscriberName()).thenReturn(subscriberName);
        when(subscriber.getSubscriberEmail()).thenReturn(subscriberEmail);
        String responseString = httpSubscriberController.addSubscriberFromWebsite(subscriber.getSubscriberName(), subscriber.getSubscriberEmail());
        assertThat(responseString, is("Success"));
    }

    @Test
    public void shouldCallAddSubscriberMethodOfSubscriberRepository() {
        httpSubscriberController.addSubscriberFromWebsite(subscriberName, subscriberEmail);
        subscriberRepository.addSubscriber(subscriber);
        when(subscriber.getSubscriberName()).thenReturn(subscriberName);
        assertThat(subscriberName, is(subscriber.getSubscriberName()));
        when(subscriber.getSubscriberEmail()).thenReturn(subscriberEmail);
        assertThat(subscriberEmail, is(subscriber.getSubscriberEmail()));
    }

    @Before
    public void setUp() {
        initMocks(this);
        httpSubscriberController = new HTTPSubscriberController(subscriberRepository);
    }
}
