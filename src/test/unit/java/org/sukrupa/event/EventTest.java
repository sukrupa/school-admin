package org.sukrupa.event;

import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventTest {

    @Test
    public void identicalEventsAreEqual(){

        Event event1 = new Event("Dummy event", new Date(2010,8,29),new Time(10,10,10),"DD","coord","event desc","event notes",null);
        Event event2 = new Event("Dummy event", new Date(2010,8,29),new Time(10,10,10),"DD","coord","event desc","event notes",null);
        assertThat(event1.equals(event2),is(true));
    }

}
