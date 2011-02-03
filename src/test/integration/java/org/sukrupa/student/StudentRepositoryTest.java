package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
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
import java.util.List;

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
	private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").dateOfBirth(new LocalDate(1995,10,1)).sex("Male").talents(new HashSet(Arrays.asList(music, sport))).build();
	private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").sex("Female").dateOfBirth(new LocalDate(1990, 7, 24)).build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").sex("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("abcdef").build();

	@BeforeClass
	public static void classSetUp() {
        DateTimeUtils.setCurrentMillisFixed(new DateMidnight(2010, 12, 31).getMillis());
	}

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
	public void shouldReturnStudentsBetweenEighteenAndTwentyTwo() {
		databaseHelper.save(sahil,pat,renaud);

		List<Student> students = repository.parametricSearch("", "", "", "", "18", "22", "");
		assertThat(students.size(), is(1));
		assertThat(students, hasItems(renaud));
	}

	@Test
	public void shouldReturnListOfTalents() {
		databaseHelper.save(sahil);
		assertThat(repository.findAll().get(0).getTalents(),hasItems(music,sport));
	}
}
