package org.sukrupa.event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentRepository;

import static com.google.common.collect.Sets.newHashSet;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.Matchers.containsAttendees;

public class EventServiceTest {

    private final Student pat = new StudentBuilder().studentId("1").build();

    private final Student jim = new StudentBuilder().studentId("2").build();

    private Event event = new EventBuilder().build();

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

    private Event contains(Student... attendees) {
        return argThat(containsAttendees(attendees));
    }
}
