package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.student.StudentsController.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentsControllerTest {

    @Mock
    private StudentRepository repository;

    private StudentsController controller;

    private Map<String, List<?>> studentsListModel = new HashMap<String, List<?>>();
    private HashMap<String,Student> studentModel = new HashMap<String,Student>();
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
        controller.all(studentsListModel);
        List<List<Student>> pages = (List<List<Student>>) studentsListModel.get("pages");
        assertThat(pages.get(0), is(asList(sahil, pat)));
    }

    @Test
    public void shouldPickStudentsViewForDisplayingAllStudents() {
        assertThat(controller.all(studentsListModel), is("studentList"));
    }

    @Test
    public void shouldDisplayOnePage() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        when(repository.findAll()).thenReturn(students);
        assertThat(controller.all(studentsListModel), is("studentList"));
        assertThat(studentsListModel.get("pages").size(), is(1));
    }

    @Test
    public void shouldDisplayTwoPages() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        when(repository.findAll()).thenReturn(students);
        assertThat(controller.all(studentsListModel), is("studentList"));
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
        when(repository.find("123")).thenReturn(pat);
        controller.viewStudent("123", studentModel);
        assertThat(studentModel.get("student"),is(pat));
    }

    @Test
    public void shouldPickStudentViewForDisplayingSingleStudent() {
	    when(repository.find("123")).thenReturn(pat);
        assertThat(controller.viewStudent("123", studentModel),is("studentView"));
    }

    @Test
    public void shouldDisplayingErrorWhenAskedForInvalidStudentID() {
        assertThat(controller.viewStudent("0987ihuyi", studentModel),is("studentViewFailed"));
    }
}
