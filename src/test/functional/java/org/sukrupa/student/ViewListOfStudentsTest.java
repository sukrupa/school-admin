package org.sukrupa.student;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.page.ListOfStudentsPage;
import org.sukrupa.platform.DatabaseHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Ignore("pat, rebecca: work in progress")
public class ViewListOfStudentsTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Autowired
    private DatabaseHelper databaseHelper;

    @Test
    public void shouldDisplayListOfAllStudents() {
        Student rebecca = new StudentBuilder().name("rebecca").female().build();
        Student pat = new StudentBuilder().name("pat").male().build();
        Student renaud = new StudentBuilder().name("renaud").male().build();

        save(rebecca, pat, renaud);

        ListOfStudentsPage page = new ListOfStudentsPage(driver);

        assertThat(page.getStudents().get(0).getName(), is("rebecca"));
        assertThat(page.getStudents().get(1).getName(), is("pat"));
        assertThat(page.getStudents().get(2).getName(), is("renaud"));
    }

    public void save(Object... students) {
        databaseHelper.saveAndCommit(students);
    }
}
