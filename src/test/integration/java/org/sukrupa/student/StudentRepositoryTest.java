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
import org.sukrupa.platform.config.SpringContextLoaderForTesting;
import org.sukrupa.platform.db.DatabaseHelper;
import org.sukrupa.platform.date.Date;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.sukrupa.platform.hamcrest.Matchers.hasOnly;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class StudentRepositoryTest {

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

    private Student sahil = new StudentBuilder()
            .studentId("1")             .name("Sahil")
            .studentClass("Nursery")    .dateOfBirth(new LocalDate(1995, 10, 1))
            .gender("Male")             .talents(music, sport)
            .build();
    private Student renaud = new StudentBuilder()
            .studentId("2")             .name("Renaud")
            .studentClass("Nursery")    .dateOfBirth(new LocalDate(1990, 7, 24))
            .gender("Female")
            .build();
    private Student pat = new StudentBuilder()
            .studentId("123")           .name("pat")
            .studentClass("4th grade")  .dateOfBirth(new LocalDate(1985, 5, 24))
            .gender("male")
            .build();
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
    public void shouldHaveCountZero(){
        assertThat(repository.count(all),is(0));
    }

    @Test
    public void shouldHaveCountOne(){
        databaseHelper.save(pat);
        assertThat(repository.count(all),is(1));
    }

    @Test
    public void shouldLoadStudentBasedOnStudentId() {
        databaseHelper.save(pat);
        assertThat(repository.findByStudentId("123"), is(pat));
    }

    @Test
    public void shouldLoadStudentsBasedOnStudentIds() {
        databaseHelper.save(pat, sahil, renaud);
        assertThat(repository.findByStudentIds(pat.getStudentId(), sahil.getStudentId()), hasOnly(pat, sahil));
    }

    @Test
    public void shouldFindAllStudentsInDatabase() {
        databaseHelper.save(pat, renaud);
        List<Student> students = repository.findBy(all, 0, 100);

        assertThat(students.size(), is(2));
        assertThat(students, hasItems(pat, renaud));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        databaseHelper.save(sahil, pat, renaud);

        List<Student> students = repository.findBy(new StudentSearchParameterBuilder().studentClass("Nursery").page(1).build(), 0, 100);
        assertThat(students.size(), is(2));
        assertThat(students, hasItems(renaud, sahil));
    }

    @Test
    public void shouldReturnStudentsBetweenEighteenAndTwentyTwo() {
        databaseHelper.save(sahil, pat, renaud);
        List<Student> students = repository.findBy(new StudentSearchParameterBuilder().ageFrom("18").ageTo("22").page(1).build(), 0, 100);
        assertThat(students.size(), is(1));
        assertThat(students, hasItems(renaud));
    }

    @Test
    public void shouldPopulateTalents() {
        databaseHelper.save(sahil);
        assertThat(repository.findBy(all, 0, 100).get(0).getTalents(), hasItems(music, sport));
    }

    @Test
    public void shouldPopulateNotesInReverseChronologicalOrder() {
        Note oldNote = new Note("yesterday", new Date(24, 11, 2011));
        Note oldestNote = new Note("long time ago", new Date(29, 3, 2008));
        Note newNote = new Note("today", new Date(25, 11, 2011));

        Student student = new StudentBuilder().notes(oldestNote, newNote, oldNote).build();
        databaseHelper.save(student);

        Iterator<Note> notes = repository.findBy(all, 0, 100).get(0).getNotes().iterator();
        assertThat(notes.next(), is(newNote));
        assertThat(notes.next(), is(oldNote));
        assertThat(notes.next(), is(oldestNote));
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
        Student s = repository.findBy(all, 0, 100).get(0);
        UpdateStudentParameter updateParameter = new UpdateStudentParameterBuilder().studentId(s.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std")
                .dateOfBirth("03-02-2000")
                .talents(Sets.<String>newHashSet(MUSIC, SPORT)).build();
	    s.updateFrom(updateParameter, Sets.newHashSet(music,sport));
        Student updatedStudent = repository.update(s);
        assertThat(updatedStudent, is(philNew));
    }

    @Test
    public void shouldUpdateStudent() {
        databaseHelper.save(pat);

        Note newNote = new Note("foobar");
        pat.addNote(newNote);

        repository.put(pat);
        databaseHelper.flushHibernateSessionToForceReload();

        Student reloadedStudent = repository.findByStudentId(pat.getStudentId());
        assertThat(reloadedStudent.getNotes(), hasItem(newNote));
    }

    @Test
    public void shouldReturnAllStudents() {
       databaseHelper.save(pat);
       databaseHelper.save(sahil);

       List<Student> students = repository.findAll();

       assertThat(students, hasItems(pat,sahil));

    }
    @Test
    public void shouldTreatNullCasteAsEmpty(){
        Student withoutCaste = new StudentBuilder().studentId("98765").caste(null).build();
        databaseHelper.save(withoutCaste);
        StudentSearchParameter blankCaste = new StudentSearchParameterBuilder().caste("").build();
        List <Student> list = repository.findBy(blankCaste, 0, 100);
        assertThat(list,hasItem(withoutCaste));

    }


}
