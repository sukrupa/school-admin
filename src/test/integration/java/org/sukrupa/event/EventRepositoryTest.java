package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.EventBuilder;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.DatabaseHelper;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.hasItems;
import static org.sukrupa.platform.hamcrest.Matchers.hasOnly;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class EventRepositoryTest {

	private final Student sahil = new StudentBuilder().name("Sahil").studentId("1").build();
	private final Student suhas = new StudentBuilder().name("Suhas").studentId("2").build();
    private final Event sportsEvent = new EventBuilder().title("Sports Day").date(new Date(21, 12, 2011)).build();
    private final Event independeceDayEvent = new EventBuilder().title("Independence Day").date(new Date(15, 8, 2011)).build();

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private DatabaseHelper databaseHelper;

	private EventRepository eventRepository;

	@Before
	public void setUp() {
		databaseHelper.save(sahil, suhas);
		eventRepository = new EventRepository(sessionFactory);
	}

	@Test
	public void shouldLoadAndPopulateASavedEvent() {
		Event event = save(new EventBuilder().attendees(sahil, suhas).build());
		assertThat(eventRepository.list(), hasOnly(event));
	}

	@Test
	public void shouldLoadEventById() {
		Event event = save(new EventBuilder().build());
		assertThat(eventRepository.load(event.getId()), is(event));
	}
    
    @Test
    public void shouldLoadAllEventsWithMostRecentEventFirst(){
        databaseHelper.save(independeceDayEvent);
        databaseHelper.save(sportsEvent);
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
		Event eventSaved = save(new EventBuilder().notes(null).coordinator(null).venue(null).build());
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
