package org.sukrupa.event;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TimeTest {
    @Test
    public void timeDoesNotExistIfEmptyOrNull() {
        assertThat(new Time(null, "am").exists(), is(false));
        assertThat(new Time("", "am").exists(), is(false));
    }
}
