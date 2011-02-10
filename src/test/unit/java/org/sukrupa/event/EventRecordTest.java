package org.sukrupa.event;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventRecordTest {

    @Test
    public void shouldReturnStudentIdsOfAttendees() {
        EventRecord eventRecord = new EventRecordBuilder().attendees("1", "2", "3").build();
        assertThat(eventRecord.getStudentIdsOfAttendees(), is(new String[]{"1", "2", "3"}));
    }
}
