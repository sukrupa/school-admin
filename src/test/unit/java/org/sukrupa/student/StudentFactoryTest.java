package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentFactoryTest {

    @Test
    public void shouldCreateAStudent(){
        StudentFactory studentFactory = new StudentFactory();

        Student student = studentFactory.create("SK123", "Frank", "11-10-1982");

        assertThat(student.getStudentId(), is("SK123"));
        assertThat(student.getName(), is("Frank"));
        assertThat(student.getDateOfBirth(), is(new LocalDate(1982, 10, 11)));
    }

    @Test
    @Ignore("Yael, Jim - WIP for #200")
    public void shouldCreateAStudentWithTalents() {

    }

}
