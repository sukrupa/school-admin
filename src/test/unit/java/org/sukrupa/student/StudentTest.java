package org.sukrupa.student;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentTest {

    @Test
    public void shouldBeEqual() {
        assertThat(student("pat").equals(student("pat")), is(true));
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertThat(student("pat").hashCode(), is(student("pat").hashCode()));
    }

    @Test
    public void shouldNotBeEqualIfDifferentName() {
        assertThat(student("pat").equals(student("mr. jones")), is(false));
    }

    private Student student(String name) {
        return new StudentBuilder().name(name).build();
    }
}
