package org.sukrupa.platform.date;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

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
        assertThat(new Date(9, 2, 2011, 13, 14).getTime(), is("13:14"));
        assertThat(new Date(9, 2, 2011, 00, 00).getTime(), is("00:00"));
    }

    @Test
    public void shouldParseDateAndTime() {
        assertThat(Date.parse("10-08-2011", "13:12"), is(new Date(10, 8, 2011, 13, 12)));
        assertThat(Date.parse("10-08-2011", "00:00"), is(new Date(10, 8, 2011)));
    }
}
