package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.joda.time.DateMidnight;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
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
import java.util.List;
import java.util.Set;

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
    private final Talent cooking = new Talent("Cooking");
    Note todo = new Note("do this and that", new LocalDate(2011, 11, 8));
    private Student sahil = new StudentBuilder().name("Sahil").studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1)).gender("Male").talents(music, sport).notes(todo).build();
    private Student renaud = new StudentBuilder().name("Renaud").studentClass("Nursery").gender("Female").dateOfBirth(new LocalDate(1990, 7, 24)).notes(todo).build();
    private Student pat = new StudentBuilder().name("pat").religion("n/a").caste("huh?").subCaste("hmm").area("DD").gender("male").dateOfBirth(new LocalDate(1985, 5, 24)).studentClass("4th grade").studentId("123").notes(todo).build();

    @BeforeClass
    public static void classSetUp() {
        DateTimeUtils.setCurrentMillisFixed(new DateMidnight(2010, 12, 31).getMillis());
    }

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
        databaseHelper.save(music, sport, cooking);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        databaseHelper.save(pat, renaud);
        List<Student> students = repository.findAll();

        assertThat(students, hasSize(2));
        assertThat(Sets.newHashSet(students), hasItems(pat, renaud));
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
    public void shouldPopulateNotes() {
        Note todo = new Note("do this and that", new LocalDate(2011, 11, 8));
        Note shopping = new Note("buy milk and bread", new LocalDate(2011, 11, 8));

        Student sahil1 = new StudentBuilder().name("Sahil").studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1)).gender("Male").notes(todo, shopping).build();
        databaseHelper.save(sahil1);

        assertThat(repository.findAll().get(0).getNotes(), hasItems(todo, shopping));
    }

    @Test
    public void shouldReturnStudentBasedOnStudentId() {
        databaseHelper.save(pat);
        assertThat(repository.find("123"), is(pat));
    }


    @Test
    public void shouldReturnListOfTalents() {
        Set<String> talentsDecriptions = new HashSet<String>();
        talentsDecriptions.add("Music");
        talentsDecriptions.add("Sport");
        talentsDecriptions.add("Cooking");
        Set<Talent> talents = repository.findTalents(talentsDecriptions);
        assertThat(talents, hasItems(music, sport, cooking));
    }

    @Test
    public void shouldUpdateStudentInDatabase() {
        Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").build();
        Student philNew = new StudentBuilder().studentId("12345")
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
