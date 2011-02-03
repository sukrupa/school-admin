package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.config.AppConfigForTestsContextLoader;
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

    private Student sahil;
    private Student renaud;

    @Before
    public void setUp() {
        sahil = new StudentBuilder().name("Bob1").studentId("123459").build();
        renaud = new StudentBuilder().name("Bob2").studentId("345458").build();
        databaseHelper.save(sahil, renaud);
        eventRepository = new EventRepository(sessionFactory);
    }

    @Test
    public void saveShouldLoadEventsFromDatabase() {

        Set<Student> attendees = new HashSet<Student>();
        attendees.add(sahil);
        attendees.add(renaud);
        Event event = new EventBuilder().title("Dummy event").coordinator("cord").venue("dd").notes("notes").attendees(attendees).description("desc").build();
        save(event);
        assertThat(eventRepository.getAll().get(0), is(event));
    }

    @Test
    public void shouldRetrieveStudentsAttendingUsingID(){
        assertThat(eventRepository.retrieveStudent("123459", "345458").get(0).getName(), is("Bob1"));
    }
    private void save(Event event) {
        eventRepository.save(event);
        databaseHelper.flushHibernateSessionToForceReload();
    }


}
