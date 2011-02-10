package org.sukrupa.event;

import org.junit.Test;
import org.sukrupa.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DateTest {

    @Test
    public void shouldFormatDate() {
        assertThat(new Date(7, 12, 2011).toString(), is("07/12/2011"));
    }

    @Test
    public void shouldReturnDay() {
        assertThat(new Date(9, 2, 2011).getDay(), is("Wednesday"));
    }

    @Test
    public void shouldReturnTime() {
        assertThat(new Date(9, 2, 2011, 13, 14).getTime(), is("13:14"));
    }
}
