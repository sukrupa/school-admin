package org.sukrupa.app.admin.subscribers;


import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.admin.SubscriberController;
import static org.hamcrest.CoreMatchers.is;


import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 7/5/11
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberControllerTest {



    @Mock
    private SubscriberRepository subscriberRepository;


    private SubscriberController controller = new SubscriberController(subscriberRepository);

    @Test
    public void ShouldDisplaySubscribersPage(){
        System.out.println("ahsdga"+controller.listsubscribers());
        assertThat(controller.listsubscribers(),is("admin/subscribers/viewSubscribers"));
    }
}
