package org.sukrupa.event;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Ignore;
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

import javax.management.DescriptorAccess;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.event.EventRepository.ATTENDEES_SEPARATOR;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private DatabaseHelper databaseHelper;

    private EventRepository eventRepository;

    private Student sahil;
    private Student renaud;
    private Student sahil1;

    @Before
    public void setUp() {
        sahil = new StudentBuilder().name("Sahil").studentId("123459").build();
        sahil1 = new StudentBuilder().name("Sahil1").studentId("11111").build();
        renaud = new StudentBuilder().name("Renaud").studentId("345458").build();
        databaseHelper.save(sahil, renaud);
        eventRepository = new EventRepository(sessionFactory);
    }

    @Test
    public void shouldValidateAttendees() {
	    Set<Student> attendees = Sets.<Student>newHashSet(sahil, renaud, sahil1);

        Set<String> eventRecordAttendees = new HashSet<String>();
        for(Student each : attendees)
            eventRecordAttendees.add(each.getStudentId());

        EventRecord eventRecord = new EventRecordBuilder().date("12/01/2010").time("13:45").attendees(Joiner.on(ATTENDEES_SEPARATOR).join(eventRecordAttendees)).build();

        assertThat(eventRepository.validAttendees(eventRecord.getAttendees()).contains("11111"), is(true));
    }

    @Test
    public void shouldSaveEventWithValidAttendees() {
	    Set<Student> attendees = Sets.<Student>newHashSet(sahil, renaud);

        Set<String> eventRecordAttendees = new HashSet<String>();
        for(Student each : attendees)
            eventRecordAttendees.add(each.getStudentId());

        Event event = new EventBuilder().attendees(attendees).datetime(new EventDate(2010, 01, 12, 13, 45, 0, 0)).build();

        EventRecord eventRecord = new EventRecordBuilder().date("12/01/2010").time("13:45").attendees(Joiner.on(ATTENDEES_SEPARATOR).join(eventRecordAttendees)).build();

        assertThat(eventRepository.save(eventRecord), is(true));
	   assertThat(eventRepository.getAll().contains(event), is(true));
    }

    @Test
    public void shouldNotSaveEventWithInvalidAttendees() {
	    Set<Student> attendees = Sets.<Student>newHashSet(sahil, renaud, sahil1);

        Set<String> eventRecordAttendees = new HashSet<String>();
        for(Student each : attendees)
            eventRecordAttendees.add(each.getStudentId());

        EventRecord eventRecord = new EventRecordBuilder().date("12/01/2010").time("13:45").attendees(Joiner.on(ATTENDEES_SEPARATOR).join(eventRecordAttendees)).build();
	    assertThat(eventRepository.save(eventRecord), is(false));
    }

    private void save(EventRecord eventRecord) {
        eventRepository.save(eventRecord);
        databaseHelper.flushHibernateSessionToForceReload();
    }

}
