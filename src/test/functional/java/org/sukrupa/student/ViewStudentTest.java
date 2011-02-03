package org.sukrupa.student;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.page.ListOfStudentsPage;
import org.sukrupa.page.ViewStudentPage;
import org.sukrupa.platform.DatabaseHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class ViewStudentTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;

    @Test
    public void shouldDisplayAStudent() {
        String studentId = "123";
        Student suhas = new StudentBuilder().name("suhas").male().id(studentId).build();
        save(suhas);
        ViewStudentPage page = new ViewStudentPage(driver, studentId);
        assertThat(page.getStudentName(), is("suhas"));
    }

    public void save(Object... students) {
        databaseHelper.saveAndCommit(students);
    }

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }
}

