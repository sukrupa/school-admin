package org.sukrupa.event;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventCreateParameterTest {

    @Test
    public void shouldReturnStudentIdsOfAttendees() {
        EventCreateParameter eventCreateParameter = new EventCreateParameter();
        eventCreateParameter.setAttendees("1, 2, 3");
        assertThat(eventCreateParameter.getStudentIdsOfAttendees(), is(new String[]{"1", "2", "3"}));
    }
}
