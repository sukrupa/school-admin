package org.sukrupa.app.admin.subscribers;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class SubscriberControllerTest {


    private HashMap<String, Object> subscriberModel = new HashMap<String, Object>();

    @Mock
    private SubscriberRepository subscriberRepository;


    private SubscriberController subscriberController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subscriberController = new SubscriberController(subscriberRepository);
    }

    @Test
    public void shouldDisplaySubscribersPage() {
        assertThat(subscriberController.shouldDisplaySubscribers(subscriberModel), is("admin/subscribers/viewsubscribers"));
    }

    @Test
    public void shouldDeleteASubscriber() {
        Subscriber subcriberToBeDeleted = mock(Subscriber.class);
        when(subscriberRepository.findById(anyInt())).thenReturn(subcriberToBeDeleted);
        subscriberController.shouldDeleteSubscriber(subscriberModel, anyInt());
        verify(subscriberRepository).deleteSubscriber(subcriberToBeDeleted);
    }
}
