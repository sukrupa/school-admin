package org.sukrupa.app.admin.subscribers;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.sukrupa.app.admin.subscribers.Subscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;


public class SubscriberTest {

     private final  Subscriber mathew = new Subscriber("Mathew", "math@gmail.com");
    @Test
    public void shouldCreateSponsorMathew(){

        assertThat(mathew.getSubscriberName(),is("Mathew"));
        assertThat(mathew.getSubscriberEmail(), is("math@gmail.com"));
    }

    @Test
    public void shouldCheckForEqualityOfTwoSubscribers(){
       Subscriber matthew_twin = new Subscriber(mathew.getSubscriberName(),mathew.getSubscriberEmail());
        assertTrue(matthew_twin.equals(mathew));
    }
}
