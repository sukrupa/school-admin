package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.apache.xpath.Arg;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sukrupa.app.services.StudentImageRepository;

import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.date.DateManipulation.freezeDateToMidnightOn;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;
import static org.sukrupa.platform.hamcrest.SchoolAdminMatchers.hasNote;
import static org.sukrupa.student.StudentService.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentServiceTest {

    private static final String MUSIC = "Music";
    private static final String SPORT = "Sport";
    private static final String COOKING = "Cooking";
    private static final String ANNUAL_CLASS_UPDATE = "annual class update";

    private final Talent music = new Talent(MUSIC);
    private final Talent sport = new Talent(SPORT);
    private final Talent cooking = new Talent(COOKING);

    private final StudentSearchParameter all = new StudentSearchParameterBuilder().build();


    private StudentService service;

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private TalentRepository talentRepository;
    @Mock
    private StudentImageRepository studentImageRepository;
    @Mock
    private SystemEventLogRepository systemEventLogRepository;



    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new StudentService(studentRepository, talentRepository, studentImageRepository, systemEventLogRepository);
        freezeDateToMidnightOn(30,12,2011); //This is actually the 29th?
    }

    @After
    public void tearDown() throws Exception {
        unfreezeTime();
    }

    @Test
    public void shouldAddANoteToStudent() {
        String studentId = "42";
        Note note = new Note("Fish like plankton!");

        when(studentRepository.findByStudentId(studentId)).thenReturn(new StudentBuilder().build());

        service.addNoteFor(studentId, note.getMessage());

        verify(studentRepository).put(argThat(hasNote(note)));
    }

    @Test
    public void shouldRetrievePageOneOfOne() {
        when(studentRepository.getCountBasedOn(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        assertThat(service.getPage(all, 1, "").isNextEnabled(), is(false));
        Mockito.verify(studentRepository).findBySearchParameter(all, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageOneOfMultiple() {
        when(studentRepository.getCountBasedOn(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(all, 1, "").isNextEnabled(), is(true));
        Mockito.verify(studentRepository).findBySearchParameter(all, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageTwoOfTwo() {
        when(studentRepository.getCountBasedOn(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(all, 2, "page=2").isNextEnabled(), is(false));
        Mockito.verify(studentRepository).findBySearchParameter(all, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldUpdateStudent() {
        Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").talents(Sets.newHashSet(cooking, sport)).dateOfBirth(new LocalDate(2000, 05, 03)).status(StudentStatus.EXISTING_STUDENT).build();
        Student philNew = new StudentBuilder().studentId("12345")
                .name("Philippa").studentClass("2 Std").gender("Female").religion("Catholic").area("Chamundi Nagar")
                .caste("ST").subCaste("AK").talents(Sets.newHashSet(music, sport)).dateOfBirth(new LocalDate(2000, 02, 03)).status(StudentStatus.EXISTING_STUDENT).sponsored("Tim").sponsorEmail("tim@gmail.com").sponsorDate(new LocalDate(2011, 07, 07)).build();

        when(studentRepository.findByStudentId(philOld.getStudentId())).thenReturn(philOld);
        when(studentRepository.update(philNew)).thenReturn(philNew);
        when(talentRepository.findTalents(Sets.newHashSet(MUSIC, SPORT))).thenReturn(Sets.newHashSet(music, sport));

        StudentForm updateParameters = new StudentCreateOrUpdateParameterBuilder().studentId(philOld.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std")
                .dateOfBirth("03-02-2000")
                .familyStatus("")
                .talents(Sets.<String>newHashSet(MUSIC, SPORT)).status(StudentStatus.EXISTING_STUDENT)
                .sponsored("Tim")
                .sponsorEmail("tim@gmail.com")
                .sponsorStartDate("07-07-2011").build();
        Student updatedStudent = service.update(updateParameters);
        assertThat(updatedStudent, Matchers.is(philNew));
    }

    @Test
    public void shouldUpdateStudentImageIfTheImageIsPassed(){
        StudentForm studentProfileForm = mock(StudentForm.class);
        Image image = mock(Image.class);
        Student mockStudent = mock(Student.class);

        when(studentProfileForm.getStudentId()).thenReturn("12345");
        when(mockStudent.getStudentId()).thenReturn("12345");
        when(studentRepository.findByStudentId("12345")).thenReturn(mockStudent);
        when(studentProfileForm.hasImage()).thenReturn(true);


        service.update(studentProfileForm);

        verify(studentProfileForm).createImage(studentImageRepository);
    }

    @Test
    public void shouldNotUpdateStudentImageIfFormHasNoImage() {
        StudentForm studentProfileForm = mock(StudentForm.class);
        Image image = mock(Image.class);
        Student mockStudent = mock(Student.class);

        when(studentProfileForm.getStudentId()).thenReturn("12345");
        when(studentRepository.findByStudentId("12345")).thenReturn(mockStudent);
        when(studentProfileForm.hasImage()).thenReturn(false);

        service.update(studentProfileForm);

        verify(studentImageRepository, never()).save(null, "12345");
    }

    @Test
    public void shouldUpdateStudentWithSponsor() {
        Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").talents(Sets.newHashSet(cooking, sport)).dateOfBirth(new LocalDate(2000, 05, 03)).status(StudentStatus.EXISTING_STUDENT).build();
        Student philNew = new StudentBuilder().studentId("12345")
                .name("Philippa").studentClass("2 Std").gender("Female").religion("Catholic").area("Chamundi Nagar")
                .caste("ST").subCaste("AK").talents(Sets.newHashSet(music, sport)).dateOfBirth(new LocalDate(2000, 02, 03)).status(StudentStatus.ALUMNI).sponsored("Tim").sponsorEmail("tim@gmail.com").sponsorDate(new LocalDate(2011, 07, 07)).build();
        when(studentRepository.findByStudentId(philOld.getStudentId())).thenReturn(philOld);
        when(studentRepository.update(philNew)).thenReturn(philNew);
        when(talentRepository.findTalents(Sets.newHashSet(MUSIC, SPORT))).thenReturn(Sets.newHashSet(music, sport));

        StudentForm updateParameters = new StudentCreateOrUpdateParameterBuilder().studentId(philOld.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std")
                .dateOfBirth("03-02-2000")
                .familyStatus("")
                .talents(Sets.<String>newHashSet(MUSIC, SPORT))
                .status(StudentStatus.ALUMNI)
                .sponsored("Tim")
                .sponsorEmail("tim@gmail.com")
                .sponsorStartDate("07-07-2011").build();

        Student updatedStudent = service.update(updateParameters);

        assertThat(updatedStudent, Matchers.is(philNew));
    }

    @Test
    public void shouldUpdateStudentStatus() {
        Student philOld = new StudentBuilder().studentId("12345")
                .name("Phil").studentClass("1 Std").gender("Male").religion("Hindu").area("Bhuvaneshwari Slum")
                .caste("SC").subCaste("AD").talents(Sets.newHashSet(cooking, sport)).dateOfBirth(new LocalDate(2000, 05, 03)).status(StudentStatus.EXISTING_STUDENT).build();
        Student philNew = new StudentBuilder().studentId("12345")
                .name("Philippa").studentClass("2 Std").gender("Female").religion("Catholic").area("Chamundi Nagar")
                .caste("ST").subCaste("AK").talents(Sets.newHashSet(music, sport)).dateOfBirth(new LocalDate(2000, 02, 03)).status(StudentStatus.ALUMNI).build();
        when(studentRepository.findByStudentId(philOld.getStudentId())).thenReturn(philOld);
        when(studentRepository.update(philNew)).thenReturn(philNew);
        when(talentRepository.findTalents(Sets.newHashSet(MUSIC, SPORT))).thenReturn(Sets.newHashSet(music, sport));

        StudentForm updateParameters = new StudentCreateOrUpdateParameterBuilder().studentId(philOld.getStudentId())
                .area("Chamundi Nagar")
                .caste("ST")
                .subCaste("AK")
                .religion("Catholic")
                .name("Philippa")
                .gender("Female")
                .studentClass("2 Std")
                .dateOfBirth("03-02-2000")
                .familyStatus("")
                .talents(Sets.<String>newHashSet(MUSIC, SPORT))
                .status(StudentStatus.ALUMNI).build();

        Student updatedStudent = service.update(updateParameters);

        assertThat(updatedStudent, Matchers.is(philNew));
    }

    @Test
    public void shouldFailToUpdateNonexistantStudent() {
        assertThat(service.update(new StudentCreateOrUpdateParameterBuilder().build()), Matchers.<Object>nullValue());
    }

    @Test
    public void shouldCreateStudent() {
        StudentForm studentInputInformation = new StudentForm();
        studentInputInformation.setStudentId("SK20091001");
        studentInputInformation.setName("Yael");
        studentInputInformation.setDateOfBirth("06-03-1982");
        studentInputInformation.setGender("Female");

        Student expectedStudent = new Student("SK20091001","Yael", "06-03-1982", "Female");

        Student actualStudent = service.create(studentInputInformation);

        verify(studentRepository).put(expectedStudent);
        assertEquals(expectedStudent, actualStudent);
    }
}
