package org.sukrupa.event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.Matchers.containsAttendees;

public class EventServiceTest {

    private final Student pat = new StudentBuilder().studentId("1").build();
    private final Student jim = new StudentBuilder().studentId("2").build();
    private final Event event = new EventBuilder().build();
    private final Event sportsEvent = new EventBuilder().title("Sports").build();
    private final Event annualEvent = new EventBuilder().title("Annual Day").build();
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
        when(studentRepository.load(pat.getStudentId(), jim.getStudentId())).thenReturn(newHashSet(pat, jim));
        service.save(event, pat.getStudentId(), jim.getStudentId());
        verify(eventRepository).save(contains(pat, jim));
    }

    @Test
    public void shouldListAllEvents()
    {
        when(eventRepository.list()).thenReturn(events);
        assertThat(service.list(),hasItems(sportsEvent,annualEvent));
    }

    private Event contains(Student... attendees) {
        return argThat(containsAttendees(attendees));
    }
}
