package org.sukrupa.event;

import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
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
        Set<Student> attendees = createAttendees();
        Event event = new EventBuilder().title("Dummy event").date(new Date(29, 8, 2010, 10, 10, 10, 0)).venue("DD").coordinator("coord").description("desc").notes("notes").attendees(attendees).build();
        assertThat(event.getAttendees(), is(attendees));
    }

    private Set<Student> createAttendees() {
        Student sahil = new StudentBuilder().name("Sahil").build();
        Student renaud = new StudentBuilder().name("Renaud").build();
        Student pat = new StudentBuilder().name("pat").build();
        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        attendees.add(renaud);
        attendees.add(pat);
        return attendees;
    }

    @Test
    public void shouldCreateCommaSeparatedAttendeeNameListForDisplay() {
        Set<Student> attendees = createAttendees();
        List<String> expectedAttendeeNames = new ArrayList<String>();
        for(Student attendee: attendees){
            expectedAttendeeNames.add(attendee.getName());
        }
        Event event = new EventBuilder().attendees(attendees).build();
        assertThat(event.getAttendeesForDisplay(), is(StringUtils.join(expectedAttendeeNames, ", ")));
    }

	@Test
	public void shouldCreateEventWithNullValuesForNonMandatoryFields() {
		EventCreateParameter eventCreateParameter = new EventCreateParameter();
		eventCreateParameter.setCoordinator("");
		eventCreateParameter.setNotes("");
		eventCreateParameter.setVenue("");
		eventCreateParameter.setTime("");
		eventCreateParameter.setDate("12-12-2001");

		Event event = Event.createFrom(eventCreateParameter);

		assertThat(event.getNotes(), nullValue());
		assertThat(event.getVenue(), nullValue());
		assertThat(event.getCoordinator(), nullValue());
	}

    @Test
	public void shouldNotDisplayTimeIfMidnight() {
        Event event = new EventBuilder().date(new Date(31, 01, 2011, 00, 00)).build();
        assertThat(event.getTime(), nullValue());
    }
}
