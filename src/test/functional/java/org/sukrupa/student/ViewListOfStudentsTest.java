package org.sukrupa.student;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.sukrupa.app.students.*;
import org.sukrupa.base.FunctionalTestBase;
import org.sukrupa.platform.config.*;
import org.sukrupa.platform.db.*;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.sukrupa.platform.hamcrest.SchoolAdminMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
public class ViewListOfStudentsTest extends FunctionalTestBase {

    @Autowired
    private HibernateSession hibernateSession;

    @After
    public void tearDown() throws Exception {
        hibernateSession.deleteAllCreatedObjects();
        driver.get("http://localhost:8080/authentication/logout");
    }

    @Test
    public void shouldDisplayListOfAllStudentsOrderedByName() {
        Talent running = new Talent("running");
        Talent jumping = new Talent("jumping");
        Talent flying = new Talent("flying");
        save(running, jumping, flying);

        Student rebecca = new StudentBuilder().name("rebecca").studentId("1").female().age(25).talents(running, jumping, flying).build();
        Student bob = new StudentBuilder().name("bob").studentId("2").male().age(22).talents(flying).build();
        Student alex = new StudentBuilder().name("alex").studentId("3").male().age(42).build();
        save(rebecca, bob, alex);

        List<StudentRow> students = new ListOfStudentsPage(driver).getStudents();

        assertThat(students, hasSize(3));
        assertThat(students.get(0), matches(alex));
        assertThat(students.get(1), matches(bob));
        assertThat(students.get(2), matches(rebecca));
    }

    public void save(Object... students) {
        hibernateSession.saveAndCommit(students);
    }
}
