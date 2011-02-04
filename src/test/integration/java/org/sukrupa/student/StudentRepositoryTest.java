package org.sukrupa.student;

import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.joda.time.DateMidnight;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
    private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1)).gender("Male").talents(music, sport).build();
    private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").gender("Female").dateOfBirth(new LocalDate(1990, 7, 24)).build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").gender("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("123").build();

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
        List<Student> students = repository.findAll();
        assertThat(students, hasSize(3));
        assertThat(students, hasItems(sahil, pat, renaud));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        databaseHelper.save(sahil, pat, renaud);

	    List<Student> students = repository.parametricSearch(new StudentSearchParameterBuilder().studentClass("Nursery").build());
	    assertThat(students, Matchers.<Object>hasSize(2));
	    assertThat(students, hasItems(renaud, sahil));
    }

    @Test
    public void shouldReturnStudentsBetweenEighteenAndTwentyTwo() {
        databaseHelper.save(sahil, pat, renaud);
        List<Student> students = repository.parametricSearch(new StudentSearchParameterBuilder().ageFrom("18").ageTo("22").build());
        assertThat(students.size(), is(1));
        assertThat(students, hasItems(renaud));
    }

    @Test
    public void shouldPopulateTalents() {
        databaseHelper.save(sahil);
        assertThat(repository.findAll().get(0).getTalents(), hasItems(music, sport));
    }

    @Test
    public void shouldReturnStudentBasedOnStudentId() {
        databaseHelper.save(pat);
        assertThat(repository.find("123"), is(pat));
    }

    @Test
    @Ignore("[suhas, pradeep] WIP")
    public void shouldPersistStudentWithNotes() {
        Note noteOne = new Note("note1");
        Note noteTwo = new Note("note2");
        pat.addNote(noteOne);
        pat.addNote(noteTwo);
        databaseHelper.save(pat);
        Student loaded = repository.find("123");
        assertThat(loaded.getNotes(), hasItems(noteOne, noteTwo));
    }

    @Test
    public void shouldUpdateStudentInDatabase() {
        final Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").build();
        final Student philNew = new StudentBuilder().studentId("12345")
                .name("Philippa").studentClass("2 Std").gender("Female").religion("Catholic").area("Chamundi Nagar")
                .caste("ST").subCaste("AK").build();
        databaseHelper.save(philOld);
        Student s = repository.findAll().get(0);
        UpdateStudentParameter updateParameter = new UpdateStudentParameterBuilder().studentId(s.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std").build();
        boolean status = repository.update(updateParameter);
        Student retrievedPhil = repository.findAll().get(0);
        assertThat(retrievedPhil, is(philNew));
        assertThat(status, is(true));
    }

    @Test
    public void shouldFailToUpdateNonexistantStudent() {
        assertThat(repository.update(new UpdateStudentParameterBuilder().build()), is(false));
    }
}
