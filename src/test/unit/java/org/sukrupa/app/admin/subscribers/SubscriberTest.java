package org.sukrupa.app.admin.subscribers;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.sukrupa.app.admin.subscribers.Subscriber;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class SubscriberTest {

    @Test
    public void shouldCreateSponsorMathew(){
        Subscriber mathew = new Subscriber("Mathew", "math@gmail.com");
        assertThat(mathew.getSubscriberName(),is("Mathew"));
        assertThat(mathew.getSubscriberEmail(), is("math@gmail.com"));
    }
}
