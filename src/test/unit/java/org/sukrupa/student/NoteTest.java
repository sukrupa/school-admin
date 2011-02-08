package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class NoteTest {

    @Test
    public void shouldBeEqual() {
        assertThat(new Note("foo bar", new LocalDate(2005, 6, 10)), is(new Note("foo bar", new LocalDate(2005, 6, 10))));
    }

    @Test
    public void shouldNotBeEqual() {
        assertThat(new Note("foo", new LocalDate(2005, 6, 10)), not(is(new Note("foo", new LocalDate(2005, 6, 11)))));
        assertThat(new Note("foo", new LocalDate(2005, 6, 10)), not(is(new Note("bar", new LocalDate(2005, 6, 10)))));
    }
}