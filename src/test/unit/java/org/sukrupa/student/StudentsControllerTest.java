package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.student.StudentRepository.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentsControllerTest {


    @Mock
    private StudentRepository repository;

    private StudentsController controller;

    private Map<String, Object> studentsListModel = new HashMap<String, Object>();
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
    public void shouldDisplayFirstPage() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        when(repository.parametricSearch(Matchers.<StudentSearchParameter>anyObject())).thenReturn(new StudentListPage(students.subList(0, 5), 1, 2));
        controller.list(new StudentSearchParameterBuilder().page(1).build(), studentsListModel);
        StudentListPage page = (StudentListPage) studentsListModel.get("page");
        assertThat(page.getPageNumber(), is(1));
        assertThat(page.getStudents().size(), is(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE));
    }

    @Test
    public void shouldDisplaySecondPage() {
        List<Student> students = createListOfStudents(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        when(repository.parametricSearch(Matchers.<StudentSearchParameter>anyObject())).thenReturn(new StudentListPage(students.subList(5, 6), 2, 2));
        controller.list(new StudentSearchParameterBuilder().page(2).build(), studentsListModel);
        StudentListPage page = (StudentListPage) studentsListModel.get("page");
        assertThat(page.getPageNumber(), is(2));
        assertThat(page.getStudents().size(), is(1));
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
