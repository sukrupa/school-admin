package org.sukrupa.app.students;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.validation.Errors;
import org.sukrupa.student.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
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

    private FakeStudentValidator studentValidator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        studentValidator = new FakeStudentValidator();
        controller = new StudentsController(service, studentValidator);
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
        StudentCreateOrUpdateParameters studentToCreate = new StudentCreateOrUpdateParameters();
        studentToCreate.setDateOfBirth("11-10-1982");

        Student studentThatGetsCreated = new Student("SK111","", "01-01-2001");
        when(service.create(any(StudentCreateOrUpdateParameters.class))).thenReturn(studentThatGetsCreated);

        String result = controller.create(studentToCreate, null);

        assertThat(result, is("redirect:/students/SK111"));
    }

    @Test
    public void shouldAddNameErrorIfTheUserDoesNotEnterAName() {
        studentValidator.addErrorTo("name");

        Map<String, Object> model = new HashMap<String, Object>();

        StudentCreateOrUpdateParameters userDidNotEnterName = new StudentUpdateParameterBuilder().name("").build();

        controller.create(userDidNotEnterName, model);

        assertNotNull(model.get("nameError").toString());
    }

    @Test
    public void shouldDefineIfStudentIsActiveOnViewStudent() throws Exception {
        Map<String,Object> model = new HashMap<String, Object>();
        Student student = mock(Student.class);
        when(service.load("id")).thenReturn(student);
        when(student.getStatus()).thenReturn(StudentStatus.ACTIVE);

        controller.view("id", false, model);

        assertThat((String) model.get("statusType"), is("active"));
    }

    @Test
    public void shouldDefineIfStudentIsInactiveOnViewStudent() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        Student student = mock(Student.class);
        when(service.load("id")).thenReturn(student);
        when(student.getStatus()).thenReturn(StudentStatus.INACTIVE);

        controller.view("id", false, model);

        assertThat((String) model.get("statusType"), is("inactive"));
    }

    @Test
    public void shouldDefineIfStudentStatusIsAlumniOrNotSetOnViewStudent() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        Student student = mock(Student.class);
        when(service.load("id")).thenReturn(student);
        when(student.getStatus()).thenReturn(StudentStatus.ALUMNI);

        controller.view("id", false, model);

        assertThat((String) model.get("statusType"), is("default"));
    }

    private class FakeStudentValidator extends StudentValidator {
        private List<String> errorFields;

        public FakeStudentValidator() {
            errorFields = new ArrayList<String>();
        }

        @Override
        public void validate(Object target, Errors errors) {
            for (String field : errorFields) {
                errors.rejectValue(field, "", "made up error");
            }
        }

        public void addErrorTo(String field) {
            errorFields.add(field);
        }
    }
}
