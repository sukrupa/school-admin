package org.sukrupa.event;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventAttendeeTest {

    @Test
    public void identicalEventAttendeePairAreEqual(){

        EventAttendee attendee = new EventAttendee(1,"12345");
        EventAttendee attendee2 = new EventAttendee(1,"12345");
        assertThat(attendee.equals(attendee2), is(true));

    }


}
