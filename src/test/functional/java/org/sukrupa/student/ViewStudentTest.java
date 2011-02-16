package org.sukrupa.student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.app.student.ViewStudentPage;
import org.sukrupa.platform.DatabaseHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
public class ViewStudentTest {

    private Student suhas = new StudentBuilder().name("suhas").studentId("123").notes(new Note("hello")).build();

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {

    }

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }

    @Test
    public void shouldDisplayAStudent() {
        save(suhas);

        ViewStudentPage page = new ViewStudentPage(driver, suhas.getStudentId());

        assertThat(page.getStudentName(), is("suhas"));
    }

    @Test
    public void shouldListNotes() {
        save(suhas);
        ViewStudentPage page = new ViewStudentPage(driver, suhas.getStudentId());
        assertThat(page.getNotes(), hasItem("hello"));
    }

    public void save(Object... students) {
        databaseHelper.saveAndCommit(students);
    }
}

