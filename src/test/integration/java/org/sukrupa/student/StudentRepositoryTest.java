package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class StudentRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DatabaseHelper databaseHelper;

    private StudentRepository repository;
	private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").gender("Male").build();
	private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").gender("Female").build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").gender("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("abcdef").id("123").build();

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        databaseHelper.save(sahil, pat, renaud);

        assertThat(repository.findAll(), hasItems(sahil, pat, renaud));
    }

    @Test
    public void shouldPersistAndReloadAllFields() {
        databaseHelper.save(pat);

        assertThat(repository.findAll(), hasItems(pat));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        databaseHelper.save(sahil, pat, renaud);
        assertThat(repository.parametricSearch("Nursery", "", "", "", "", "", ""), hasItems(renaud, sahil));
    }

    @Test
    public void shouldReturnStudentBasedOnStudentId(){
        databaseHelper.save(pat);
        assertThat(repository.find("123"),is(pat));
    }
}
