package org.sukrupa.event;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

public class EventTest {

    @Before
    public void setUp() throws Exception {
        freezeTime();
    }

    @After
    public void tearDown() throws Exception {
        unfreezeTime();
    }

    @Test
    public void shouldBeEqual() {
        assertThat(new EventBuilder().build(), is(new EventBuilder().build()));
    }

    @Test
    public void shouldReturnTheCorrectStudentList() {
        Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").gender("Male").build();
        Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").gender("Female").build();
        Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").gender("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("abcdef").build();
        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        attendees.add(renaud);
        attendees.add(pat);
        Event event1 = new EventBuilder().title("Dummy event").date(new Date(29, 8, 2010, 10, 10, 10, 0)).venue("DD").coordinator("coord").description("desc").notes("notes").attendees(attendees).build();
        assertThat(attendees.equals(event1.getAttendees()), is(true));
    }
}
