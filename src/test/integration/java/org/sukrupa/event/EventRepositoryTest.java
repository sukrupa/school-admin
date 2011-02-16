package org.sukrupa.event;

import org.hamcrest.Matchers;
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
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.eventFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.hasItems;
import static org.sukrupa.platform.Matchers.hasOnly;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

	private final Student sahil = new StudentBuilder().name("Sahil").studentId("1").build();
	private final Student suhas = new StudentBuilder().name("Suhas").studentId("2").build();

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
    public void shouldLoadAllEvents() {
        Event sportsEvent = new EventBuilder().title("Sports Day").date(new Date(1, 1, 2011)).build();
        Event independeceDayEvent = new EventBuilder().title("Independence Day").date(new Date(15, 8, 2011)).build();
        databaseHelper.save(sportsEvent);
        databaseHelper.save(independeceDayEvent);
        assertThat(eventRepository.list(),hasItems(sportsEvent,independeceDayEvent));
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
