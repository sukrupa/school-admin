package org.sukrupa.event;

import org.junit.Test;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EventTest {

    @Test
    public void identicalEventsAreEqual(){
        Event event1 = new EventBuilder().title("Dummy event").date(new Date(2010,8,29)).time(new Time(10,10,10))
                .venue("DD").coordinator("coord").description("desc").notes("notes").build();
        Event event2 = new EventBuilder().title("Dummy event").date(new Date(2010,8,29)).time(new Time(10,10,10))
                .venue("DD").coordinator("coord").description("desc").notes("notes").build();
        assertThat(event1.equals(event2),is(true));
    }

    @Test
    public void shouldReturnTheCorrectStudentList(){
        Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").sex("Male").build();
        Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").sex("Female").build();
        Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").sex("male").dateOfBirth("1985/05/24").studentClass("4th grade").studentId("abcdef").build();
        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        attendees.add(renaud);
        attendees.add(pat);
        Event event1 = new EventBuilder().title("Dummy event").date(new Date(2010,8,29)).time(new Time(10,10,10))
                .venue("DD").coordinator("coord").description("desc").notes("notes").attendees(attendees).build();
        assertThat(attendees.equals(event1.getAttendees()),is(true));
    }























}
