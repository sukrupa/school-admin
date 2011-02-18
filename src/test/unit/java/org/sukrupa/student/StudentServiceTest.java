package org.sukrupa.student;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.hamcrest.Matchers.hasNote;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;
import static org.sukrupa.student.StudentService.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentServiceTest {

    private final StudentSearchParameter all = new StudentSearchParameterBuilder().build();

    @Mock
    private StudentRepository studentRepository;
	@Mock
	private TalentRepository talentRepository;

    private StudentService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new StudentService(studentRepository, talentRepository);
        freezeTime();
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
        when(studentRepository.count(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        assertThat(service.getPage(all, 1, "").isNextEnabled(), is(false));
        Mockito.verify(studentRepository).findBy(all, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageOneOfMultiple() {
        when(studentRepository.count(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(all, 1, "").isNextEnabled(), is(true));
        Mockito.verify(studentRepository).findBy(all, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageTwoOfTwo() {
        when(studentRepository.count(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(all, 2, "page=2").isNextEnabled(), is(false));
        Mockito.verify(studentRepository).findBy(all, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void  shouldIncrementClassOfEachStudentByOne() {
        // given
        List<Student> students = new ArrayList<Student>();

        Student sahil = new StudentBuilder().name("sahil").studentClass("1 Std").build();
        students.add(sahil);

        Student mark = new StudentBuilder().name("mark").studentClass("1 Std").build();
        students.add(mark);

        when(studentRepository.findAll()).thenReturn(students);

        // when
        service.promoteStudentsToNextClass();

        // then
        Student promotedSahil = new StudentBuilder().name("sahil").studentClass("2 Std").build();
        Student promotedMark = new StudentBuilder().name("mark").studentClass("2 Std").build();

        Mockito.verify(studentRepository).put(promotedSahil);
        Mockito.verify(studentRepository).put(promotedMark);
    }

    @Test
    public void shouldFailToUpdateNonexistantStudent() {
        assertThat(service.update(new UpdateStudentParameterBuilder().build()), Matchers.<Object>nullValue());
    }
}
