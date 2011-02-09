package org.sukrupa.event;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class ViewEventTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }

    @Test
    public void shouldDisplayEvent() {
        Student alex = save(new StudentBuilder().name("alex").build());
        Student bob = save(new StudentBuilder().name("bob").build());
        Event event = save(new EventBuilder().attendees(alex, bob).build());

        ViewEventPage eventPage = new ViewEventPage(driver, event.getId());

        assertThat(eventPage.getTitle(), is(event.getTitle()));
        assertThat(eventPage.getDate(), is(event.getDate().toString()));
        assertThat(eventPage.getDay(), is(event.getDay()));
        assertThat(eventPage.getTime(), is(event.getTime()));
        assertThat(eventPage.getVenue(), is(event.getVenue()));
        assertThat(eventPage.getDescription(), is(event.getDescription()));
        assertThat(eventPage.getNotes(), is(event.getNotes()));
        assertHasSameAttendees(eventPage.getAttendees(), event.getAttendees());
    }

    private void assertHasSameAttendees(Set<String> actual, Set<Student> expected) {
        for (Student attendee : expected) {
            assertThat(actual, hasItem(attendee.getName()));
        }
    }

    private <T> T save(T event) {
        databaseHelper.saveAndCommit(event);
        return event;
    }
}
