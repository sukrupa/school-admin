package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private DatabaseHelper databaseHelper;

    private EventRepository eventRepository;

    @Before
    public void setUp() {
        eventRepository = new EventRepository(sessionFactory);
    }

    @Test
    public void saveShouldLoadEventsFromDatabase() {
        Student sahil = new StudentBuilder().name("Bob1").studentId("12345").build();
        Student renaud = new StudentBuilder().name("Bob2").studentId("34545").build();
        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        attendees.add(renaud);
        databaseHelper.save(sahil, renaud);

        Event event = new EventBuilder().title("Dummy event").datetime(new DateTime(2010, 8, 29, 10, 10, 10, 0)).coordinator("cord").venue("dd").notes("notes").attendees(attendees).description("desc").build();
        save(event);

        assertThat(eventRepository.getAll().get(0), is(event));
    }

    private void save(Event event) {
        eventRepository.save(event);
        databaseHelper.flushHibernateSessionToForceReload();
    }
}
