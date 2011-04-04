package org.sukrupa.student;


import org.junit.Test;
import org.sukrupa.platform.date.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class ProfileTest {
    @Test
    public void shouldBeEqual() {
        assertThat(new Profile("foo bar"), is(new Profile("foo bar")));
    }

    @Test
    public void shouldNotBeEqual() {
        assertThat(new Profile("foo"), not(is(new Profile("bar"))));
    }
}
