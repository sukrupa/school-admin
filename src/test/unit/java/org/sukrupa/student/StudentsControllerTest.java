package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

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
