package org.sukrupa.event;

import com.google.common.base.Joiner;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.event.EventRepository.ATTENDEES_SEPARATOR;
import static org.sukrupa.platform.Matchers.hasOnly;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private DatabaseHelper databaseHelper;

    private EventRepository eventRepository;

    private Student sahil = new StudentBuilder().name("Sahil").studentId("1").build();
    private Student suhas = new StudentBuilder().name("Suhas").studentId("2").build();
    private Student renaud = new StudentBuilder().name("Renaud").studentId("3").build();

    @Before
    public void setUp() {
        databaseHelper.save(sahil, suhas, renaud);
        eventRepository = new EventRepository(sessionFactory);
    }

    @Test
    public void shouldLoadAndPopulateASavedEvent() {
        Event event = save(new EventBuilder().attendees(sahil, suhas).build());
        assertThat(eventRepository.getAll(), hasOnly(event));
    }

    @Test
    public void shouldLoadEventById() {
        Event event = save(new EventBuilder().attendees(sahil, renaud).build());
        assertThat(eventRepository.getEvent(event.getId()), is(event));
    }

    @Test // FIXME get rid of this test - this is not repository functionality
    public void shouldValidateAttendees() {
        Set<Student> attendees = newHashSet(sahil, renaud, suhas);
        Set<String> eventRecordAttendees = new HashSet<String>();
        for (Student each : attendees)
            eventRecordAttendees.add(each.getStudentId());

        EventRecord eventRecord = new EventRecordBuilder().date("12/01/2010").time("13:45").attendees(Joiner.on(ATTENDEES_SEPARATOR).join(eventRecordAttendees)).build();

        assertThat(eventRepository.validAttendees(eventRecord.getAttendees()).contains(sahil.getStudentId()), is(true));
    }

    @Test // FIXME get rid of this test - this is not repository functionality
    public void shouldNotSaveEventWithInvalidAttendees() {
        Student pat = new StudentBuilder().name("Patric").studentId("4").build();
        Set<Student> attendees = newHashSet(sahil, renaud, pat);

        Set<String> eventRecordAttendees = new HashSet<String>();
        for (Student each : attendees)
            eventRecordAttendees.add(each.getStudentId());

        EventRecord eventRecord = new EventRecordBuilder().date("12/01/2010").time("13:45").attendees(Joiner.on(ATTENDEES_SEPARATOR).join(eventRecordAttendees)).build();
        assertThat(eventRepository.save(eventRecord), is(false));
    }

    private Event save(Event event) {
        databaseHelper.save(event);
        return event;
    }
}
