package org.sukrupa.student;

import org.junit.Test;
import org.sukrupa.platform.date.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class NoteTest {

    @Test
    public void shouldBeEqual() {
        assertThat(new Note("foo bar", new Date(10, 6, 2005)), is(new Note("foo bar", new Date(10, 6, 2005))));
    }

    @Test
    public void shouldNotBeEqual() {
        assertThat(new Note("foo", new Date(10, 6, 2005)), not(is(new Note("foo", new Date(11, 6, 2005)))));
        assertThat(new Note("foo", new Date(10, 6, 2005)), not(is(new Note("bar", new Date(10, 6, 2005)))));
    }
}