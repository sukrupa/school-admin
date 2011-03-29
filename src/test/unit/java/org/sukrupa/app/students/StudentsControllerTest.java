package org.sukrupa.app.students;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;
import org.sukrupa.student.StudentCreateOrUpdateParameter;
import org.sukrupa.student.StudentService;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StudentsControllerTest {


    @Mock
    private StudentService service;

    private StudentsController controller;

    private HashMap<String, Object> studentModel = new HashMap<String, Object>();
    private Student pat = new StudentBuilder().name("sahil").studentClass("Nursery").build();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new StudentsController(service);
    }

    @Test
    public void shouldPopulateModelWithAStudent() {
        when(service.load("123")).thenReturn(pat);
        controller.view("123", false, studentModel);
        assertThat((Student)studentModel.get("student"),is(pat));
    }

    @Test
    public void shouldPickStudentViewForDisplayingSingleStudent() {
	    when(service.load("123")).thenReturn(pat);
        assertThat(controller.view("123", false, studentModel),is("students/view"));
    }

    @Test
    public void shouldDisplayingErrorWhenAskedForInvalidStudentID() {
        assertThat(controller.view("0987ihuyi", false, studentModel),is("students/viewFailed"));
    }

    @Test
    public void shouldDirectToNewStudentForm() {
        assertThat(controller.newStudent(studentModel), is("students/create"));
    }

    @Test
    public void shouldCreateANewStudent () {
        Student student = mock(Student.class);
        StudentCreateOrUpdateParameter createParameter = new StudentCreateOrUpdateParameter();
        createParameter.setDateOfBirth("11-10-1982");

        when(student.getStudentId()).thenReturn("SK111");
        when(service.create(any(String.class), any(String.class), any(String.class))).thenReturn(student);

        assertThat(controller.create(createParameter), is("redirect:/students/SK111"));
    }

}
