package org.sukrupa.student;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentTest {

    @Test
    public void shouldBeEqual() {
        assertThat(new Student("Patric").equals(new Student("Patric")), is(true));
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertThat(new Student("Patric").hashCode(), is(new Student("Patric").hashCode()));
    }

    @Test
    public void shouldNotBeEqualIfDifferentName() {
        assertThat(new Student("Patric").equals(new Student("Mr Jones")), is(false));
    }

}
