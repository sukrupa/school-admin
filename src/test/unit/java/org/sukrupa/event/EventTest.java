package org.sukrupa.event;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sukrupa.student.Builders;
import static org.sukrupa.student.Builders.*;

import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.sukrupa.platform.date.DateManipulation.freezeDateToMidnightOn_31_12_2010;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;

public class EventTest {

    @Before
    public void setUp() throws Exception {
        freezeDateToMidnightOn_31_12_2010();
    }

    @After
    public void tearDown() throws Exception {
        unfreezeTime();
    }

    @Test
    public void shouldBeEqual() {
        assertThat(make(an(Event)), is(make(an(Event))));
    }

    @Test
    public void shouldReturnTheCorrectStudentList() {
        Set<Student> eventAttendees = createAttendees();

        Event event = make(an(Event, with(attendees, eventAttendees)));

        assertThat(event.getAttendees(), is(eventAttendees));
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
        Event event = make(an(Event, with(Builders.attendees, attendees)));
        assertThat(event.getAttendeesForDisplay(), is(StringUtils.join(expectedAttendeeNames, ", ")));
    }

	@Test
	public void shouldCreateEventWithNullValuesForNonMandatoryFields() {
		EventForm eventForm = new EventForm();
		eventForm.setCoordinator("");
		eventForm.setNotes("");
		eventForm.setVenue("");
		eventForm.setEndTime("10:00");
		eventForm.setDate("12-12-2001");
        eventForm.setEndTimeAmPm("am");

		Event event = eventForm.createEvent();

		assertThat(event.getNotes(), nullValue());
		assertThat(event.getVenue(), nullValue());
		assertThat(event.getCoordinator(), nullValue());
	}

    @Test
    public void shouldCreateStartAndEndDatesWithAmOrPmApplied() {
        EventForm eventForm = new EventForm();
        eventForm.setCoordinator("Joel");
		eventForm.setNotes("Cool");
		eventForm.setVenue("TW");
        eventForm.setStartTime("05:00");
        eventForm.setStartTimeAmPm("am");
        eventForm.setEndTime("01:00");
        eventForm.setEndTimeAmPm("pm");
        eventForm.setDate("12-12-2001");

        Event event = eventForm.createEvent();

        assertThat(event.getEndTime(), is("01:00"));
        assertThat(event.isEndTimePm(), is(true));

        assertThat(event.getStartTime(), is("05:00"));
        assertThat(event.isStartTimePm(), is(false));
    }

}
