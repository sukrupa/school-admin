package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.hsqldb.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void saveShouldCreateRecordInDatabase(){
        EventRepository eventRepository = new EventRepository(sessionFactory);
        Event event = new Event("Dummy event", new Date(2010,8,29),new Time(10,10,10),"DD","coord","event desc","event notes",null);
        eventRepository.save(event);
        List<Event> eventsList = eventRepository.getAll();
        assertThat(eventsList,hasItems(event));
    }
}
