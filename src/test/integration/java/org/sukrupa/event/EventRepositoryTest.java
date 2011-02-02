package org.sukrupa.event;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentRepositoryTest;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class EventRepositoryTest {

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void saveShouldCreateRecordInDatabase(){
        Student sahil = new StudentBuilder().name("Bob1").studentId("12345").build();
	    Student renaud = new StudentBuilder().name("Bob2").studentId("34545").build();
        Student phil = new StudentBuilder().name("Bob3").studentId("321545").build();
        //StudentRepositoryTest test = new StudentRepositoryTest();
        //test.save(sahil,renaud);
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
}
