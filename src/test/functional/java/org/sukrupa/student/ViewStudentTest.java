package org.sukrupa.student;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.app.students.ViewStudentPage;
import org.sukrupa.platform.DatabaseHelper;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Ignore("pat, pavithra: work in progress")
public class ViewStudentTest {

    private WebDriver driver = new HtmlUnitDriver();
    private Student suhas;
    @Autowired
    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() throws Exception {
        suhas = new StudentBuilder().name("suhas").studentId("123").build();
        save(suhas);
    }

    @Test
    public void shouldDisplayAStudent() {
        ViewStudentPage page = new ViewStudentPage(driver, suhas.getStudentId());
        assertThat(page.getStudentName(), is("suhas"));
    }

    @Test
    public void shouldAddAndListNotes() {
        ViewStudentPage page = new ViewStudentPage(driver, suhas.getStudentId());
        page.addNote("This is a new note");
        //page.refresh();
        List<String> notes = page.getNotes();
        assertThat(notes, hasItem("This is a new note"));
    }

    public void save(Object... students) {
        databaseHelper.saveAndCommit(students);
    }

    @After
    public void tearDown() throws Exception {
        databaseHelper.deleteAllCreatedObjects();
    }
}

