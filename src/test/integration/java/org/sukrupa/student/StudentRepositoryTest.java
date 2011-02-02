package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class StudentRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private StudentRepository repository;
	private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").sex("Male").build();
	private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").sex("Female").build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").sex("male").dateOfBirth("1985/05/24").studentClass("4th grade").studentId("abcdef").build();
    private final StudentCreatorHelper studentCreatorHelper = new StudentCreatorHelper();

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        studentCreatorHelper.save(session(), sahil, pat, renaud);

        assertThat(repository.findAll(), hasItems(sahil, pat));
    }

    @Test
    public void shouldPersistAndReloadAllFields() {
        studentCreatorHelper.save(session(), pat);

        assertThat(repository.findAll(), hasItems(pat));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        studentCreatorHelper.save(session(), sahil, pat, renaud);
        assertThat(repository.parametricSearch("Nursery", "", "", "", "", "", ""), hasItems(renaud, sahil));
    }

    public void save(Student... students) {
        studentCreatorHelper.save(session(), students);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
