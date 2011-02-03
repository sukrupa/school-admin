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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;

import java.util.Arrays;
import java.util.HashSet;

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
	private final Talent music = new Talent("Music");
	private final Talent sport = new Talent("Sport");
	private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").sex("Male").talents(new HashSet(Arrays.asList(music, sport))).build();
	private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").sex("Female").build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").sex("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("abcdef").build();

	@Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
	    databaseHelper.save(music, sport);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        databaseHelper.save(sahil, pat, renaud);

        assertThat(repository.findAll(), hasItems(sahil, pat, renaud));
    }

    @Test
    public void shouldPersistAndReloadAllFields() {
        databaseHelper.save(pat);

        assertThat(repository.findAll().get(0), is(pat));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        databaseHelper.save(sahil, pat, renaud);
        assertThat(repository.parametricSearch("Nursery", "", "", "", "", "", ""), hasItems(renaud, sahil));
    }

	@Test
	public void shouldReturnListOfTalents() {
		databaseHelper.save(sahil);
		assertThat(repository.findAll().get(0).getTalents(),hasItems(music,sport));
	}
}
