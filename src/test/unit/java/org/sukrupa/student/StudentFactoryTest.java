package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

public class StudentFactoryTest {

    private StudentFactory studentFactory;


    @Before
    public void setUp() {
        studentFactory = new StudentFactory();
    }

    @Test
    public void shouldCreateAStudent() {
        Student student = studentFactory.create("SK123", "Frank", "11-10-1982");

        assertThat(student.getStudentId(), is("SK123"));
        assertThat(student.getName(), is("Frank"));
        assertThat(student.getDateOfBirth(), is(new LocalDate(1982, 10, 11)));
    }

    @Test
    public void shouldCreateAStudentWithTalents() {
        Talent someTalent = new TalentBuilder().description("Some Talent").build();

        Student student = studentFactory.create(null, null, "11-10-1982", someTalent);

        assertThat(student.getTalents(), hasOnly(someTalent));
    }

}
