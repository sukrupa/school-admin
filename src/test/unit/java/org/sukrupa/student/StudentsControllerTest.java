package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StudentsControllerTest {


    @Mock
    private StudentService service;

    private StudentsController controller;

    private Map<String, Object> studentsListModel = new HashMap<String, Object>();
    private HashMap<String, Student> studentModel = new HashMap<String, Student>();
    private Student sahil = new StudentBuilder().name("pat").studentClass("LKG").build();
    private Student pat = new StudentBuilder().name("sahil").studentClass("Nursery").build();
    private Student renaud = new StudentBuilder().name("renaud").studentClass("Nursery").build();

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new StudentsController(service);
    }

    @Test
    public void shouldPopulateModelWithAStudent() {
        when(service.load("123")).thenReturn(pat);
        controller.view("123", false, (HashMap) studentModel);
        assertThat(studentModel.get("student"),is(pat));
    }

    @Test
    public void shouldPickStudentViewForDisplayingSingleStudent() {
	    when(service.load("123")).thenReturn(pat);
        assertThat(controller.view("123", false, (HashMap) studentModel),is("students/view"));
    }

    @Test
    public void shouldDisplayingErrorWhenAskedForInvalidStudentID() {
        assertThat(controller.view("0987ihuyi", false, (HashMap) studentModel),is("students/viewFailed"));
    }
}
