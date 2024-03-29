package org.sukrupa.student;

import org.hibernate.*;
import org.joda.time.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.*;
import org.sukrupa.event.Event;
import org.sukrupa.event.EventBuilder;
import org.sukrupa.platform.config.*;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.db.*;
import org.sukrupa.platform.hamcrest.CollectionMatchers;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertTrue;
import static org.sukrupa.platform.date.DateManipulation.*;
import static org.sukrupa.platform.hamcrest.CollectionMatchers.hasOnly;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringContextLoaderForTesting.class)
@Transactional
public class StudentRepositoryTest {

    static final String MUSIC = "Music";
    static final String SPORT = "Sport";
    static final String COOKING = "Cooking";

    private final Talent music = new Talent(MUSIC);
    private final Talent sport = new Talent(SPORT);
    private final Talent cooking = new Talent(COOKING);

    private final Event spiceGirls = new EventBuilder().title("Spice Girls").build();
    private final Event michaelJackson = new EventBuilder().title("Michael Jackson").build();

    private final Caregiver fabio = new CaregiverBuilder().name("Fabio")
            .occupation("Bus Driver")
            .maritalStatus("Single")
            .education("Awesome")
            .contact("123")
            .salary("RS. 5,000")
            .build();

    private final Caregiver mary = new CaregiverBuilder().name("mary")
            .maritalStatus("Married")
            .salary("RS. 6,000")
            .education("Very good")
            .build();

    private final Caregiver naruto = new CaregiverBuilder().name("Naruto")
            .salary("RS. 7,000")
            .education("Ninja")
            .build();

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateSession hibernateSession;

    @Autowired
    private StudentsSearchCriteriaGenerator studentsSearchCriteriaGenerator;

    private StudentRepository studentRepository;

    private Student sahil = new StudentBuilder()
            .studentId("99").name("Sahil")
            .studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1))
            .gender("Male").talents(music, sport)
            .status(StudentStatus.EXISTING_STUDENT)
            .build();
    private Student jimbo = new StudentBuilder()
            .studentId("1").name("Jimbo")
            .studentClass("Nursery").dateOfBirth(new LocalDate(1975, 10, 1))
            .gender("Male").talents(cooking)
            .status(StudentStatus.EXISTING_STUDENT)
            .build();
    private Student renaud = new StudentBuilder()
            .studentId("2").name("Renaud")
            .studentClass("Nursery").dateOfBirth(new LocalDate(1990, 7, 24))
            .gender("Female")
            .status(StudentStatus.EXISTING_STUDENT)
            .build();

    private Student pat = new StudentBuilder()
            .studentId("123").name("pat")
            .studentClass("4th grade").dateOfBirth(new LocalDate(1985, 5, 24))
            .gender("male")
            .status(StudentStatus.EXISTING_STUDENT)
            .familyStatus(StudentFamilyStatus.SINGLE)
            .build();

    private Student yael = new StudentBuilder()
            .studentId("SK555").name("Yael")
            .studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1))
            .gender("Female").talents(music, sport)
            .status(StudentStatus.EXISTING_STUDENT)
            .familyStatus(StudentFamilyStatus.GENERAL)
            .build();

    private Student yam = new StudentBuilder()
            .studentId("559").name("Yam")
            .studentClass("Nursery").dateOfBirth(new LocalDate(1995, 10, 1))
            .gender("Male").talents(music, sport)
            .status(StudentStatus.EXISTING_STUDENT)
            .familyStatus(StudentFamilyStatus.SINGLE)
            .build();

    private Student peter = new StudentBuilder()
            .studentId("123321").name("Peter")
            .studentClass("Preschool").dateOfBirth(new LocalDate(1990, 10, 1))
            .gender("Male").talents(music, sport)
            .status(StudentStatus.DROPOUT)
            .build();

    private Student toy = new StudentBuilder()
            .studentId("556677").name("Toy")
            .studentClass("4th grade").dateOfBirth(new LocalDate(1987, 10, 1))
            .gender("Male").talents(music, sport)
            .status(StudentStatus.ALUMNI)
            .events(spiceGirls)
            .build();

    private Student joel = new StudentBuilder()
            .studentId("55667788").name("Joel")
            .studentClass("1th grade").dateOfBirth(new LocalDate(1980, 10, 1))
            .gender("Male").talents(music, sport)
            .status(StudentStatus.ALUMNI)
            .events(spiceGirls, michaelJackson)
            .build();

    private Student balaji = new StudentBuilder()
            .studentId("987654").name("Balaji")
            .studentClass("1th grade").dateOfBirth(new LocalDate(1980, 10, 1))
            .gender("Male")
            .status(StudentStatus.EXISTING_STUDENT)
            .father(fabio)
            .mother(mary)
            .guardian(naruto)
            .build();

    private Student mark = new StudentBuilder()
            .studentId("636363").name("Mark")
            .studentClass("1th grade").dateOfBirth(new LocalDate(1911, 11, 1))
            .gender("Male")
            .sponsored("SomeSponsor")
            .sponsorEmail("")
            .sponsorDate(new LocalDate(2011, 07, 13))
            .build();

    private Student kishore = new StudentBuilder()
            .studentId("636360").name("Kishore")
            .studentClass("Preschool").dateOfBirth(new LocalDate(1989, 11, 4))
            .gender("Male")
            .sponsored("Hephzibah")
            .sponsorEmail("hepz@gmail.com")
            .sponsorDate(new LocalDate(2011, 07, 13))
            .build();

    private Student aravind = new StudentBuilder()
            .studentId("636361").name("Aravind")
            .studentClass("10th grade").dateOfBirth(new LocalDate(1989, 11, 1))
            .gender("Male")
            .sponsored("Abhinaya")
            .sponsorEmail("abh@gmail.com")
            .sponsorDate(new LocalDate(2011, 07, 13))
            .build();


    private final StudentSearchParameter all = new StudentSearchParameterBuilder().build();


    @BeforeClass
    public static void classSetUp() {
        freezeDateToMidnightOn_31_12_2010();
    }

    @AfterClass
    public static void classTearDown() throws Exception {
        unfreezeTime();
    }

    @Before
    public void setUp() throws Exception {
        studentRepository = new StudentRepository(sessionFactory, studentsSearchCriteriaGenerator);
        hibernateSession.save(music, sport, cooking, spiceGirls, michaelJackson);
    }

    @Test
    public void shouldPersistAStudent() {
        // Given we have acting, singing and sports talents in the database
        Talent actingTalent = new TalentBuilder().description("Acting").build();
        Talent singingTalent = new TalentBuilder().description("Singing").build();
        Talent sportsTalent = new TalentBuilder().description("Sports").build();
        Caregiver father = new Caregiver();
        Caregiver mother = new Caregiver();
        hibernateSession.save(actingTalent, singingTalent, sportsTalent);

        studentRepository.put(new StudentBuilder()
                .studentId("SK12345")
                .name("John")
                .religion("Hindi")
                .caste("someCaste")
                .subCaste("someSubCaste")
                .dateOfBirth(new LocalDate(2001, 1, 11))
                .father(father)
                .gender("Male")
                .mother(mother)
                .talents(actingTalent, singingTalent)
                .build());

        // Then we should be able to find it again by student id
        Student returnedStudent = studentRepository.findByStudentId("SK12345");

        assertThat(returnedStudent.getStudentId(), is("SK12345"));
        assertThat(returnedStudent.getName(), is("John"));
        assertThat(returnedStudent.getReligion(), is("Hindi"));
        assertThat(returnedStudent.getCaste(), is("someCaste"));
        assertThat(returnedStudent.getDateOfBirth(), is(new LocalDate(2001, 1, 11)));
        assertThat(returnedStudent.getFather(), is(father));
        assertThat(returnedStudent.getMother(), is(mother));
        assertThat(returnedStudent.getTalents(), hasOnly(actingTalent, singingTalent));
    }

    @Test
    public void shouldHaveCountZero() {
        assertThat(studentRepository.getCountBasedOn(all), is(0));
    }

    @Test
    public void shouldHaveCountOne() {
        hibernateSession.save(pat);
        assertThat(studentRepository.getCountBasedOn(all), is(1));
    }

    @Test
    public void shouldLoadStudentBasedOnStudentId() {
        hibernateSession.save(pat);
        assertThat(studentRepository.findByStudentId("123"), is(pat));
    }

    @Test
    public void shouldLoadStudentsBasedOnStudentIds() {
        hibernateSession.save(pat, sahil, renaud);
        assertThat(studentRepository.findByStudentIds(pat.getStudentId(), sahil.getStudentId()), CollectionMatchers.hasOnly(pat, sahil));
    }

    @Test
    public void shouldFindAllStudentsInDatabase() {
        hibernateSession.save(pat, renaud);
        Criteria getPageCriteria = studentsSearchCriteriaGenerator.createOrderedCriteriaFrom(all);
        getPageCriteria.setFirstResult(0);
        getPageCriteria.setMaxResults(100);
        List<Student> students = getPageCriteria.list();
        assertThat(students.size(), is(2));
        assertThat(students, CollectionMatchers.hasOnly(pat, renaud));
    }

    @Test
    public void shouldReturnTheTotalNumberOfStudentsAsSix(){
    hibernateSession.save(pat, sahil, renaud,mark , kishore, aravind);
        int studentCount = studentRepository.getTotalStudentsCount();
        assertThat(studentCount, is(6));
    }

    @Test
    public void shouldReturnTheTotalNumberOfStudentsAsFour(){
    hibernateSession.save( renaud,mark , kishore, aravind);
        int studentCount = studentRepository.getTotalStudentsCount();
        assertThat(studentCount, is(4));
    }

    @Test
    public void shouldReturnTheTotalNumberOfStudentsSponsoredAsTwo(){
    hibernateSession.save( renaud, kishore, aravind);
        int studentCount = studentRepository.getSponsoredStudentsCount();
        assertThat(studentCount, is(2));
    }

    @Test
    public void shouldReturnTheTotalNumberOfStudentsSponsoredAsThree(){
    hibernateSession.save( pat, renaud, mark, kishore, aravind);
        int studentCount = studentRepository.getSponsoredStudentsCount();
        assertThat(studentCount, is(3));
    }

    @Test
    public void shouldReturnNurseryStudents() {
        // Given that renaud and sahil are in the nursery class pat is not
        hibernateSession.save(sahil, pat, renaud);

        // When we search the repositoty for students who are in the nursery class
        List<Student> students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().studentClass("Nursery").page(1).build(), 0, 100);

        // Then we should have only see renaud and sahil in the results
        assertThat(students.size(), is(2));
        assertThat(students, CollectionMatchers.hasOnly(renaud, sahil));
    }

    @Test
    public void shouldReturnDropoutStudents() {
        hibernateSession.save(toy, peter);

        List<Student> students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().studentStatus(StudentStatus.DROPOUT).page(1).build(), 0, 100);

        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(peter));
    }

    @Test
    public void shouldReturnStudentsBetweenEighteenAndTwentyTwo() {
        hibernateSession.save(sahil, pat, renaud);
        List<Student> students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().ageFrom("18").ageTo("22").page(1).build(), 0, 100);
        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(renaud));
    }

    @Test
    public void shouldPopulateTalents() {
        // Given
        hibernateSession.save(sahil);

        // When
        List<Student> students = studentRepository.findBySearchParameter(all, 0, 100);

        // Then
        assertThat(students.get(0).getTalents(), CollectionMatchers.hasOnly(music, sport));
    }

    @Test
    public void shouldReturnStudentsBasedOnMultipleTalents() {
        hibernateSession.save(jimbo, pat, sahil, renaud);

        List<Talent> talents = new ArrayList<Talent>();
        talents.add(cooking);
        talents.add(music);

        List<Student> students = studentRepository.findBySearchParameter(
                new StudentSearchParameterBuilder().withTalents(talents).build(), 0, 100);
        assertThat(students.size(), is(2));
    }

    @Test
    public void shouldReturnStudentsWithFamilyStatusSingle() {
        hibernateSession.save(yael, yam, pat);

        List<Student> students = studentRepository.findBySearchParameter(
                new StudentSearchParameterBuilder().studentFamilyStatus(StudentFamilyStatus.SINGLE.toString()).build(), 0, 100);
        assertThat(students.size(), is(2));
        assertThat(students.contains(yam), is(true));
        assertThat(students.contains(pat), is(true));
    }

    @Test
    public void shouldReturnStudentsWithNoFamilyStatus() throws Exception {
        hibernateSession.save(jimbo, pat, sahil, renaud);
        List<Student> students = studentRepository.findBySearchParameter(
                new StudentSearchParameterBuilder().studentFamilyStatus("").build(), 0, 100);

        assertThat(students.size(), is(3));
        assertThat(students.contains(jimbo), is(true));
        assertThat(students.contains(sahil), is(true));
        assertThat(students.contains(renaud), is(true));
    }


    @Test
    public void shouldReturnUniqueResultsWhenSearchingMultipleTalents() {
        hibernateSession.save(jimbo, pat, sahil, renaud);

        List<Talent> talents = new ArrayList<Talent>();
        talents.add(sport);
        talents.add(music);

        List<Student> students = studentRepository.findBySearchParameter(
                new StudentSearchParameterBuilder().withTalents(talents).build(), 0, 100);
        assertThat(students.size(), is(1));
    }

    @Test
    public void shouldPopulateNotesInReverseChronologicalOrder() {
        Note oldNote = new Note("yesterday", new Date(24, 11, 2011));
        Note oldestNote = new Note("long time ago", new Date(29, 3, 2008));
        Note newNote = new Note("today", new Date(25, 11, 2011));
        Student student = new StudentBuilder().notes(oldestNote, newNote, oldNote).build();
        hibernateSession.save(student);
        List<Student> students = studentRepository.findBySearchParameter(all, 0, 200);
        Iterator<Note> notes = (students.get(0)).getNotes().iterator();
        assertThat(notes.next(), is(newNote));
        assertThat(notes.next(), is(oldNote));
        assertThat(notes.next(), is(oldestNote));
    }

    @Test
    public void shouldUpdateStudent() {
        hibernateSession.save(pat);

        Note newNote = new Note("foobar");
        pat.addNote(newNote);

        studentRepository.put(pat);
        hibernateSession.flushHibernateSessionToForceReload();

        Student reloadedStudent = studentRepository.findByStudentId(pat.getStudentId());
        assertThat(reloadedStudent.getNotes(), hasItem(newNote));
    }

    @Test
    public void shouldReturnAllStudents() {
        hibernateSession.save(pat);
        hibernateSession.save(sahil);
        List<Student> students = studentRepository.findAll();
        assertThat(students, CollectionMatchers.hasOnly(pat, sahil));

    }

    @Test
    public void shouldTreatNullCasteAsEmpty() {
        Student withoutCaste = new StudentBuilder().studentId("98765").caste(null).build();
        hibernateSession.save(withoutCaste);
        StudentSearchParameter blankCaste = new StudentSearchParameterBuilder().caste("").build();
        Criteria getPageCriteria = studentsSearchCriteriaGenerator.createOrderedCriteriaFrom(blankCaste);
        getPageCriteria.setFirstResult(0);
        getPageCriteria.setMaxResults(100);
        List<Student> list = getPageCriteria.list();
        assertThat(list, hasItem(withoutCaste));

    }

    @Test
    public void shouldReturnTheStudentIfWeMatchIdButNotCase() {
        hibernateSession.save(yael);

        Student student = studentRepository.findByStudentId("sk555");

        assertThat(student, is(yael));
    }

    @Test
    public void shouldReturnAStudentIfWeMatchTheNameExactly() {
        // Student yael = new Student("Ak2700", "Yael", "01-01-2001");

        hibernateSession.save(yael);

        String searchTerm = "Yael";
        List<Student> students = studentRepository.findBySearchParameter(searchParametersWithNameAs(searchTerm), 0, 10);

        assertThat(students, hasItem(yael));
    }

    @Test
    public void shouldReturnAStudentIfWeMatchTheNameStringButNotCase() {
        // Student yael = new Student("Ak2700", "Yael", "01-01-2001");

        hibernateSession.save(yael);

        String searchTerm = "yAeL";
        List<Student> students = studentRepository.findBySearchParameter(searchParametersWithNameAs(searchTerm), 0, 10);

        assertThat(students, hasItem(yael));
    }

    @Test
    public void shouldReturnAStudentIfWeMatchTheNamePartially() {
        hibernateSession.save(yael);

        String searchTerm = "Ya";
        List<Student> students = studentRepository.findBySearchParameter(searchParametersWithNameAs(searchTerm), 0, 10);

        assertThat(students, hasItem(yael));
    }

    @Test
    public void shouldReturnAllStudentsIfWeMatchTheNamePartially() {
        hibernateSession.save(yael);
        hibernateSession.save(yam);

        String searchTerm = "Ya";
        List<Student> students = studentRepository.findBySearchParameter(searchParametersWithNameAs(searchTerm), 0, 10);

        assertThat(students, hasItem(yael));
        assertThat(students, hasItem(yam));
    }

    @Test
    public void shouldReturnAllStudentWithAnEvent() {
        studentRepository.put(toy);
        Student student = studentRepository.findByStudentId(toy.getStudentId());
        assertThat(student.getEvents(), CollectionMatchers.hasOnly(spiceGirls));
    }

    @Test
    public void shouldReturnAllStudentHistoryEvents() {
        studentRepository.put(joel);

        Student student = studentRepository.findByStudentId(joel.getStudentId());

        assertThat(student.getEvents(), CollectionMatchers.hasOnly(spiceGirls, michaelJackson));
    }

    /**
     * We discovered that because of the many to many, when we try to compare events, it is
     * recursing back to students because it was including the atendees in the equals comparison
     * which was failing in hibernate
     */
    @Test
    public void shouldBeAbleToCompareTheEventsAStudentHasAttended() {
        studentRepository.put(joel);
        studentRepository.put(toy);

        Student student = studentRepository.findByStudentId(joel.getStudentId());

        for (Event event : student.getEvents()) {
            assertThat(event.equals(event), is(true));
        }
    }

    @Test
    public void shouldReturnStudentWithCaregiversOccupation() {
        studentRepository.put(balaji);

        List<Student> students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().caregiversOccupation("Bus Driver").build(), 0, 100);

        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(balaji));
    }

    @Test
    public void shouldReturnStudentWithSponsoredStatus() {
        studentRepository.put(mark);
        studentRepository.put(balaji);

        List<Student> students;

        students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().sponsored("Yes").build(), 0, 100);

        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(mark));

        students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().sponsored("No").build(), 0, 100);

        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(balaji));

        students = studentRepository.findBySearchParameter(new StudentSearchParameterBuilder().sponsored("Yes").sponsor("").build(), 0, 100);

        assertThat(students.size(), is(1));
        assertThat(students, CollectionMatchers.hasOnly(mark));
    }

    @Test
    public void shouldReturnFathersSalary() {
        studentRepository.put(balaji);
        Student student = studentRepository.findByStudentId(balaji.getStudentId());
        assertThat(student.getFather().getSalary(), is("RS. 5,000"));
    }

    @Test
    public void shouldReturnMothersSalary() {
        studentRepository.put(balaji);
        Student student = studentRepository.findByStudentId(balaji.getStudentId());
        assertThat(student.getMother().getSalary(), is("RS. 6,000"));
    }

    @Test
    public void shouldReturnGuardiansSalary() {
        studentRepository.put(balaji);
        Student student = studentRepository.findByStudentId(balaji.getStudentId());
        assertThat(student.getGuardian().getSalary(), is("RS. 7,000"));
    }

    private StudentSearchParameter searchParametersWithNameAs(String searchTerm) {
        return new StudentSearchParameterBuilder().name(searchTerm).build();
    }


}
