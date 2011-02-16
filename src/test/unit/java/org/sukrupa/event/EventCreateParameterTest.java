package org.sukrupa.event;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.Matchers.hasOnly;

public class EventCreateParameterTest {

    @Test
    public void shouldReturnStudentIdsOfAttendees() {
        EventCreateParameter eventCreateParameter = new EventCreateParameter();
        eventCreateParameter.setAttendees("1, 2, 3");
        assertThat(eventCreateParameter.getStudentIdsOfAttendees(), hasOnly("1", "2", "3"));
    }
}
