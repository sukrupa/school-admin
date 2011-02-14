package org.sukrupa.student;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.sukrupa.platform.Matchers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.Matchers.hasNote;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;
import static org.sukrupa.student.StudentService.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentServiceTest {

    private final StudentSearchParameter getPageOne = new StudentSearchParameterBuilder().page(1).build();
    private final StudentSearchParameter getPageTwo = new StudentSearchParameterBuilder().page(2).build();

    @Mock
    private StudentRepository repository;

    private StudentService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new StudentService(repository);
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

        when(repository.load(studentId)).thenReturn(new StudentBuilder().build());

        service.addNoteFor(studentId, note.getMessage());

        verify(repository).saveOrUpdate(argThat(hasNote(note)));
    }
    @Test
    public void shouldRetrievePageOneOfOne() {
        when(repository.countResults(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        assertThat(service.getPage(getPageOne).isNextEnabled(), is(false));
        Mockito.verify(repository).parametricSearch(getPageOne, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageOneOfMultiple() {
        when(repository.countResults(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(getPageOne).isNextEnabled(), is(true));
        Mockito.verify(repository).parametricSearch(getPageOne, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageTwoOfTwo() {
        when(repository.countResults(org.mockito.Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        assertThat(service.getPage(getPageTwo).isNextEnabled(), is(false));
        Mockito.verify(repository).parametricSearch(getPageTwo, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }
}
