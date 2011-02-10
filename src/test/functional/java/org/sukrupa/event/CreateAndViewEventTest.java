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
import org.sukrupa.app.event.CreateEventPage;
import org.sukrupa.app.event.ViewEventPage;
import org.sukrupa.platform.DatabaseHelper;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class CreateAndViewEventTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;

    private Student alex;
    private Student bob;

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }

    @Test
    public void shouldCreateAndViewAnEvent() {
        givenThereAreSomeRegisteredStudents();
        whenICreateAndSaveAnEvent();
        thenIShouldBeAbleToViewTheEventDetails();
    }

    private void givenThereAreSomeRegisteredStudents() {
        alex = save(new StudentBuilder().studentId("1").name("alex").build());
        bob = save(new StudentBuilder().studentId("2").name("bob").build());
    }

    private void whenICreateAndSaveAnEvent() {
        new CreateEventPage(driver)
                .navigateTo()
                .title("a funky event")
                .date("31-08-2011")
                .time("14:00")
                .venue("palace grounds")
                .description("bla bla bla")
                .coordinator("karthik")
                .attendees(alex.getStudentId() + ", " + bob.getStudentId())
                .notes("bring food!")
                .save();
    }

    private void thenIShouldBeAbleToViewTheEventDetails() {
        ViewEventPage viewEventPage = new ViewEventPage(driver);

        assertThat(viewEventPage.getTitle(), is("a funky event"));
        assertThat(viewEventPage.getDate(), is("31-08-2011"));
        assertThat(viewEventPage.getDay(), is("Wednesday"));
        assertThat(viewEventPage.getTime(), is("14:00"));
        assertThat(viewEventPage.getVenue(), is("palace grounds"));
        assertThat(viewEventPage.getDescription(), is("bla bla bla"));
        assertThat(viewEventPage.getNotes(), is("bring food!"));
        // TODO assertThat(viewEventPage.getAttendees(), hasItems(alex.getStudentId(), bob.getStudentId()));
    }

    private Student save(Student student) {
        databaseHelper.saveAndCommit(student);
        return student;
    }
}
