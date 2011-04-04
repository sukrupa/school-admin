package org.sukrupa.student;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sukrupa.app.students.ViewStudentPage;
import org.sukrupa.base.FunctionalTestBase;
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.HibernateSession;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
public class ViewStudentTest extends FunctionalTestBase {

    private Student suhas = new StudentBuilder().name("suhas").studentId("123").notes(new Note("hello")).build();

    @Autowired
    private HibernateSession hibernateSession;

    @After
    public void tearDown() throws Exception {
        hibernateSession.deleteAllCreatedObjects();
        driver.get("http://localhost:8080/authentication/logout");
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
        hibernateSession.saveAndCommit(students);
    }
}

