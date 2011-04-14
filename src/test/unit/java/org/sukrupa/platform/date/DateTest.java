package org.sukrupa.platform.date;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTimeZone.UTC;
import static org.joda.time.format.DateTimeFormat.forPattern;

public class DateTest {

    @Test
    public void shouldFormatDate() {
        assertThat(new Date(7, 12, 2011).toString(), is("07-12-2011"));
    }

    @Test
    public void shouldReturnDay() {
        assertThat(new Date(9, 2, 2011).getDay(), is("Wednesday"));
    }

    @Test
    public void shouldReturnTime() {
        assertThat(new Date(9, 2, 2011, 13, 14).getTime(), is("01:14"));
        assertThat(new Date(9, 2, 2011, 00, 00).getTime(), is("00:00"));
    }

    @Test
    public void shouldParseDateAndTime() {
        assertThat(Date.parse("10-08-2011", "01:12", "pm"), is(new Date(10, 8, 2011, 13, 12)));
        assertThat(Date.parse("10-08-2011", "00:00", "am"), is(new Date(10, 8, 2011)));
    }

    @Test
    public void shouldReturnTrueIfDateIsInThePast() {
        DateManipulation.freezeDateToMidnightOn_31_12_2010();
        assertThat(new Date(1,1,2010).isInThePast(),is(true));
        DateManipulation.unfreezeTime();
    }
    @Test
    public void shouldReturnFalseIfDateIsNotInThePast() {
        DateManipulation.freezeDateToMidnightOn_31_12_2010();
        assertThat(new Date(1,1,2011).isInThePast(),is(false));
        DateManipulation.unfreezeTime();
    }
    @Test
    public void shouldReturnFalseIfSameDate() {
        DateManipulation.freezeDateToMidnightOn_31_12_2010();
        assertThat(new Date(31,12,2011).isInThePast(),is(false));
        DateManipulation.unfreezeTime();
    }

    @Test
    public void shouldCreateADateWithThe12HourClock() {
        assertThat(Date.parse("01-01-2009", "01:00", "pm").getTime(), is("01:00"));
        assertThat(Date.parse("01-01-2009", "01:00", "am").getTime(), is("01:00"));
    }

    @Test
    public void shouldDetermineIfADateIsInTheAfternoon() {
        assertThat(new Date(1,1,1, 14,0,0,0).isInTheAfternoon(), is(true));
        assertThat(new Date(1,1,1, 11,0,0,0).isInTheAfternoon(), is(false));
    }

}
