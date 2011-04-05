package org.sukrupa.event;

import static com.natpryce.makeiteasy.MakeItEasy.an;
import static com.natpryce.makeiteasy.MakeItEasy.make;
import static com.natpryce.makeiteasy.MakeItEasy.with;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.db.HibernateSession;
import org.sukrupa.student.Builders;
import static org.sukrupa.student.Builders.*;
import static org.sukrupa.student.Builders.*;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class EventRepositoryTest {

	private final Student sahil = new StudentBuilder().name("Sahil").studentId("1").dateOfBirth(new LocalDate(1987,1,12)).build();
	private final Student suhas = new StudentBuilder().name("Suhas").studentId("2").dateOfBirth(new LocalDate(1987,1,12)).build();
    
    private final Event sportsEvent = make(an(Event, with(title, "Sports Day"), with(date, new Date(21, 12, 2011))));
    private final Event independeceDayEvent = make(an(Event, with(title, "Independence Day"), with(date, new Date(15, 8, 2011))));
    

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private HibernateSession hibernateSession;

	private EventRepository eventRepository;

	@Before
	public void setUp() {
		hibernateSession.save(sahil, suhas);
		eventRepository = new EventRepository(sessionFactory);
	}

	@Test
	public void shouldLoadAndPopulateASavedEvent() {
        HashSet<Student> eventAttendees = new HashSet<Student>();
        eventAttendees.add(sahil);
        eventAttendees.add(suhas);

        Event event = save(make(an(Event, with(attendees, eventAttendees))));
		assertThat(eventRepository.list(), hasOnly(event));
	}

	@Test
	public void shouldLoadEventById() {
		Event event = save(make(an(Event)));
		assertThat(eventRepository.load(event.getId()), is(event));
	}
    
    @Test
    public void shouldLoadAllEventsWithMostRecentEventFirst(){
        hibernateSession.save(independeceDayEvent);
        hibernateSession.save(sportsEvent);
        List<Event> events = eventRepository.list();
        assertThat(events.get(0),is(sportsEvent));
        assertThat(events.get(1),is(independeceDayEvent));
    }

	@Test(expected = Exception.class)
	public void shouldThrowExceptionIfIdDoesNotExist() {
		int nonExistingId = 99;
		eventRepository.load(nonExistingId);
	}

	@Test
	public void shouldSaveEmptyNonMandatoryFieldsAsNull() {
        String nullString  = null;
		Event eventSaved = save(make(an(Event, with(venue, nullString), with(notes, nullString), with(coordinator, nullString))));
		Event eventLoaded = eventRepository.load(eventSaved.getId());
		assertThat(eventLoaded.getNotes(), nullValue());
		assertThat(eventLoaded.getVenue(), nullValue());
		assertThat(eventLoaded.getCoordinator(), nullValue());
	}



	private Event save(Event event) {
		eventRepository.save(event);
		return event;
	}
}
