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
    private final StudentCreatorHelper creator = new StudentCreatorHelper();
    private final Student sahil = new StudentBuilder().name("Bob1").studentId("12345").build();
    private final Student renaud = new StudentBuilder().name("Bob2").studentId("34545").build();
    private final Set<Student> attendees = new HashSet<Student>();
    private Event event;

    @Before
    public void setUp() {
        eventRepository = new EventRepository(sessionFactory);
         attendees.add(sahil);
        attendees.add(renaud);
        creator.save(sessionFactory.getCurrentSession(), sahil,renaud);
        EventBuilder builder = new EventBuilder();
        event = builder.title("Dummy event").date(new Date(2010,8,29)).time(new Time(10,10,10)).coordinator("cord").venue("dd").notes("notes").attendees(attendees).description("desc").build();
         saveEvent(event);
    }

    @Test
    public void saveShouldCreateRecordInDatabase(){


        List<Event> eventsList = eventRepository.getAll();
        Event eventRet = eventsList.get(0);
        assertThat(event.equals(eventRet),is(true));
    }

    @Test
    public void saveWithAttendeesShouldStoreSetOfAttendees() {


        Event retrievedEvent = eventRepository.getAll().get(0);
        assertThat(attendees.equals(retrievedEvent.getAttendees()),is(true));
    }

    private void saveEvent(Event event) {
        eventRepository.save(event);
        hibernateHelper.flushHibernateSessionToForceReload(sessionFactory.getCurrentSession());
    }
}
