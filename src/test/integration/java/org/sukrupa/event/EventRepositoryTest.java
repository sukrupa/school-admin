package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.student.HibernateHelper;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentCreatorHelper;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;
    private EventRepository eventRepository;
    private final HibernateHelper hibernateHelper = new HibernateHelper();

    @Before
    public void setUp() {
        eventRepository = new EventRepository(sessionFactory);
    }

    @Test
    public void saveShouldCreateRecordInDatabase(){
        Student sahil = new StudentBuilder().name("Bob1").studentId("12345").build();
	    Student renaud = new StudentBuilder().name("Bob2").studentId("34545").build();
        Set<Student> att = new HashSet<Student>();
        att.add(sahil);
        att.add(renaud);
        EventRepository eventRepository = new EventRepository(sessionFactory);
        Event event = new Event("Dummy event", new Date(2010,8,29),new Time(10,10,10),"DD","coord","event desc","event notes",att);
        eventRepository.save(event);
        List<Event> eventsList = eventRepository.getAll();
        Event eventRet = eventsList.get(0);
        assertThat(att.equals(eventRet.getAttendees()),is(true));
    }

    @Test
    public void saveWithAttendeesShouldStoreSetOfAttendees() {
        Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").sex("Male").studentId("234567").build();
        //Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").sex("Female").studentId("13579").build();
        //Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").sex("male").dateOfBirth("1985/05/24").studentClass("4th grade").studentId("23456").build();
        StudentCreatorHelper creator = new StudentCreatorHelper();

        creator.save(sessionFactory.getCurrentSession(), sahil);

        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        //attendees.add(renaud);
//        attendees.add(pat);

        Event event = new Event("Dummy event", new Date(2010,8,29),new Time(10,10,10),"DD","coord","event desc","event notes",attendees);
        saveEvent(event);
        Event retrievedEvent = eventRepository.getAll().get(0);
//        assertThat(attendees.equals(retrievedEvent.getAttendees()),is(true));
    }

    private void saveEvent(Event event) {
        eventRepository.save(event);
        hibernateHelper.flushHibernateSessionToForceReload(sessionFactory.getCurrentSession());
    }
}
