package org.sukrupa.student;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.student.StudentsController.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentsControllerTest {

    @Mock
    private StudentRepository repository;

    private StudentsController controller;

    private Map<String, List<?>> studentsListModel = new HashMap<String, List<?>>();
    private HashMap<String, Student> studentModel = new HashMap<String, Student>();
    private Student sahil = new StudentBuilder().name("pat").studentClass("LKG").build();
    private Student pat = new StudentBuilder().name("sahil").studentClass("Nursery").build();
    private Student renaud = new StudentBuilder().name("renaud").studentClass("Nursery").build();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new StudentsController(repository);
    }

    @Test
    public void shouldPopulateModelWithPageOfStudents() {
        when(repository.findAll()).thenReturn(asList(sahil, pat));
        controller.list(studentsListModel);
        List<StudentListPage> pages = (List<StudentListPage>) studentsListModel.get("pages");
        assertThat(pages.get(0), is(new StudentListPage(asList(sahil, pat))));
    }

    @Test
    public void shouldRenderListIfNoSearchParameterInListView() throws Exception {
        controller.searchResults(new StudentSearchParameter(), Maps.<String, List<?>>newHashMap());
        Mockito.verify(repository, never()).parametricSearch((StudentSearchParameter) Matchers.anyObject());
    }


    @Test
    public void shouldDisplayOnePage() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        when(repository.findAll()).thenReturn(students);
        assertThat(controller.list(studentsListModel), is("students/list"));
        assertThat(studentsListModel.get("pages").size(), is(1));
    }

    @Test
    public void shouldDisplayTwoPages() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        when(repository.findAll()).thenReturn(students);
        assertThat(controller.list(studentsListModel), is("students/list"));
        assertThat(studentsListModel.get("pages").size(), is(2));
    }

    private List<Student> createListOfStudents(int size) {
        List<Student> students = new ArrayList<Student>();
        for (int i = 0; i < size; i++) {
            students.add(sahil);
        }
        return students;
    }

    @Test
    public void shouldPopulateModelWithAStudent() {
        when(repository.load("123")).thenReturn(pat);
        controller.view("123", studentModel);
        assertThat(studentModel.get("student"), is(pat));
    }

    @Test
    public void shouldPickStudentViewForDisplayingSingleStudent() {
        when(repository.load("123")).thenReturn(pat);
        assertThat(controller.view("123", studentModel), is("students/view"));
    }

    @Test
    public void shouldDisplayingErrorWhenAskedForInvalidStudentID() {
        assertThat(controller.view("0987ihuyi", studentModel), is("students/viewFailed"));
    }
}
