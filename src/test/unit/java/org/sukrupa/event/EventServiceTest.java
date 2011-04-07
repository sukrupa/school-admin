package org.sukrupa.event;

import com.google.common.collect.Sets;
import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Builders;

import static junit.framework.Assert.assertEquals;
import static org.sukrupa.student.Builders.*;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentRepository;

import java.util.*;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.SchoolAdminMatchers.containsAttendees;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

public class EventServiceTest {

    private final Student pat = new StudentBuilder().studentId("1").build();
    private final Student jim = new StudentBuilder().studentId("2").build();
    private final Event event = make(an(Event));
    private final Event sportsEvent = make(an(Event, with(title, "Sports")));
    private final Event annualEvent = make(an(Event, with(title, "Annual Day")));
    private final List<Event> events = new ArrayList<Event>(Arrays.asList(sportsEvent, annualEvent));

	@Mock
	private EventRepository eventRepository;

	@Mock
	private StudentRepository studentRepository;

	private EventService service;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		service = new EventService(eventRepository, studentRepository);
	}

	@Test
	public void shouldSaveEventWithStudents() {
		when(studentRepository.findByStudentIds(pat.getStudentId(), jim.getStudentId())).thenReturn(newHashSet(pat, jim));
		service.save(event, pat.getStudentId(), jim.getStudentId());
		verify(eventRepository).save(contains(pat, jim));
	}

	@Test
	public void shouldReturnEmptyListForValidStudents() {
		when(studentRepository.findByStudentIds((String[]) anyVararg())).thenReturn(newHashSet(pat, jim));
		assertThat(service.validateStudentIdsOfAttendees(Sets.newHashSet(pat.getStudentId(), jim.getStudentId())).isEmpty(), is(true));
	}

	@Test
	public void shouldReturnListOfInvalidStudentIds() {
		when(studentRepository.findByStudentIds((String[]) anyVararg())).thenReturn(newHashSet(pat, jim));
		assertThat(service.validateStudentIdsOfAttendees(Sets.newHashSet(pat.getStudentId(), jim.getStudentId(), "97543")), hasOnly("97543"));
	}

	private Event contains(Student... attendees) {
		return argThat(containsAttendees(attendees));
	}

    @Test
    public void shouldListAllEvents()
    {
        when(eventRepository.list()).thenReturn(events);
        assertThat(service.list(),hasItems(sportsEvent,annualEvent));
    }

    @Test
    public void shouldUpdateEvent() {

        Set<Student> newAttendees = newHashSet();
        newAttendees.add(pat);
        newAttendees.add(jim);

        EventCreateOrUpdateParameter updateParameter = new EventUpdateParameterBuilder().id(1)
                                .title("Spice Girls")
                                .date(new Date(12, 12, 2011))
                                .time("10:10")
                                .description("Spice Girls 4 Lyf")
                                .venue("P-81")
                                .coordinator("Joel Tellez")
                                .notes("Sick as party")
                                .attendees(newAttendees)
                                .build();

        Event newEvent = new EventBuilder().title("Spice Girls")
                                                    .attendees(newAttendees)
                                                    .date(new Date(12, 12, 2011))
                                                    .description("Spice Girls 4 Lyf")
                                                    .venue("P-81")
                                                    .coordinator("Joel Tellez")
                                                    .notes("Sick as party")
                                                    .build();

        when(eventRepository.load(1)).thenReturn(sportsEvent);
        when(service.update(updateParameter)).thenReturn(newEvent);

        Event updatedEvent = service.update(updateParameter);
        assertEquals(newEvent, updatedEvent);

    }

}
