package org.sukrupa.app.admin.subscribers;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 7/5/11
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberTest {

    @Test
    public void shouldCreateSponsorMathew(){
        Subscriber mathew = new Subscriber("Mathew", "math@gmail.com");
        assertThat(mathew.getSubscriberName(),is("Mathew"));
        assertThat(mathew.getSubscriberEmail(), is("math@gmail.com"));
    }
}
