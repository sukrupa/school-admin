package org.sukrupa.event;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

public class EventCreateOrUpdateParameterTest {

    @Test
    public void shouldReturnStudentIdsOfAttendees() {
        EventCreateOrUpdateParameter eventCreateOrUpdateParameter = new EventCreateOrUpdateParameter();
        eventCreateOrUpdateParameter.setAttendees("1, 2, 3");
        assertThat(eventCreateOrUpdateParameter.getStudentIdsOfAttendees(), hasOnly("1", "2", "3"));
    }
}
