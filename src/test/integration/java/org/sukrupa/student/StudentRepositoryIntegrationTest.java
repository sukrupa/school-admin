package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;
import org.sukrupa.platform.DatabaseHelper;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
public class StudentRepositoryIntegrationTest {

	static final String MUSIC = "Music";
	static final String SPORT = "Sport";
	static final String COOKING = "Cooking";
	@Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private DatabaseHelper databaseHelper;

    private StudentRepository repository;

    private final Talent music = new Talent(MUSIC);
    private final Talent sport = new Talent(SPORT);
    private final Talent cooking = new Talent(COOKING);

    private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery")
            .dateOfBirth(new LocalDate(1995, 10, 1)).gender("Male").talents(music, sport).build();
    private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").gender("Female")
            .dateOfBirth(new LocalDate(1990, 7, 24)).build();
    private Student pat = new StudentBuilder().studentId("001").name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD")
            .gender("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("123")
            .father("Renaud").mother("Nice Lady").build();
    private final StudentSearchParameter all = new StudentSearchParameterBuilder().build();

    @BeforeClass
    public static void classSetUp() {
        freezeTime();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        unfreezeTime();
    }

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
        databaseHelper.save(music, sport, cooking);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        databaseHelper.save(pat, renaud);
        List<Student> students = repository.parametricSearch(all).getStudents();

        assertThat(students.size(), is(2));
        assertThat(students, hasItems(pat, renaud));
    }

    @Test
    public void shouldReturnPageNumberOne() {
        databaseHelper.save(pat, renaud, sahil);
        StudentListPage page = repository.parametricSearch(all);
        assertThat(page.getPageNumber(), is(1));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        databaseHelper.save(sahil, pat, renaud);

        List<Student> students = repository.parametricSearch(new StudentSearchParameterBuilder().studentClass("Nursery").page(1).build()).getStudents();
        assertThat(students.size(), is(2));
        assertThat(students, hasItems(renaud, sahil));
    }

    @Test
    public void shouldReturnStudentsBetweenEighteenAndTwentyTwo() {
        databaseHelper.save(sahil, pat, renaud);
        List<Student> students = repository.parametricSearch(new StudentSearchParameterBuilder().ageFrom("18").ageTo("22").page(1).build()).getStudents();
        assertThat(students.size(), is(1));
        assertThat(students, hasItems(renaud));
    }

    @Test
    public void shouldPopulateTalents() {
        databaseHelper.save(sahil);
        assertThat(repository.parametricSearch(all).getStudents().get(0).getTalents(), hasItems(music, sport));
    }

    @Test
    public void shouldPopulateNotesInReverseChronologicalOrder() {
        Note oldNote = new Note("yesterday", new LocalDate(2011, 11, 24));
        Note oldestNote = new Note("long time ago", new LocalDate(2008, 3, 29));
        Note newNote = new Note("today", new LocalDate(2011, 11, 25));

        Student student = new StudentBuilder().notes(oldestNote, newNote, oldNote).build();
        databaseHelper.save(student);

        Iterator<Note> notes = repository.parametricSearch(all).getStudents().get(0).getNotes().iterator();
        assertThat(notes.next(), is(newNote));
        assertThat(notes.next(), is(oldNote));
        assertThat(notes.next(), is(oldestNote));
    }

    @Test
    public void shouldReturnStudentBasedOnStudentId() {
        databaseHelper.save(pat);
        assertThat(repository.find("123"), is(pat));
    }

    @Test
    public void shouldReturnListOfTalents() {
        Set<String> talentsDecriptions = new HashSet<String>();
        talentsDecriptions.add(MUSIC);
        talentsDecriptions.add(SPORT);
        talentsDecriptions.add(COOKING);
        Set<Talent> talents = repository.findTalents(talentsDecriptions);
        assertThat(talents, hasItems(music, sport, cooking));
    }

    @Test
    public void shouldUpdateStudentInDatabase() {
        Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").talents(Sets.newHashSet(cooking, sport)).dateOfBirth(new LocalDate(2000, 05, 03)).build();
        Student philNew = new StudentBuilder().studentId("12345")
                .name("Philippa").studentClass("2 Std").gender("Female").religion("Catholic").area("Chamundi Nagar")
                .caste("ST").subCaste("AK").talents(Sets.newHashSet(music, sport)).dateOfBirth(new LocalDate(2000, 02, 03)).build();
        databaseHelper.save(philOld);
        Student s = repository.parametricSearch(all).getStudents().get(0);
        UpdateStudentParameter updateParameter = new UpdateStudentParameterBuilder().studentId(s.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std")
		        .dateOfBirth("03/02/2000")
		        .talents(Sets.<String>newHashSet(MUSIC, SPORT)).build();
        Student updatedStudent = repository.update(updateParameter);
        assertThat(updatedStudent, is(philNew));
    }

    @Test
    public void shouldFailToUpdateNonexistantStudent() {
        assertThat(repository.update(new UpdateStudentParameterBuilder().build()), Matchers.<Object>nullValue());
    }

    @Test
    public void shouldUpdateStudent() {
        databaseHelper.save(pat);
        
        Note newNote = new Note("foobar");
        pat.addNote(newNote);

        repository.saveOrUpdate(pat);
        databaseHelper.flushHibernateSessionToForceReload();

        Student reloadedStudent = repository.find(pat.getStudentId());
        assertThat(reloadedStudent.getNotes(), hasItem(newNote));
    }
}
