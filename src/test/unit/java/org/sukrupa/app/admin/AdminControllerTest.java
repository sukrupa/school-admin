package org.sukrupa.app.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentListPage;
import org.sukrupa.student.StudentSearchParameter;
import org.sukrupa.student.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminControllerTest {

    @Mock
       private StudentService service;

       private AdminController controller;

    private HashMap<String, Object> studentModel = new HashMap<String, Object>();

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        controller = new AdminController(service);
    }

     @Test
    public void shouldDisplayResultsWhenSearchingBySponsor() {
        StudentSearchParameter searchParam = mock(StudentSearchParameter.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        StudentListPage students = mock(StudentListPage.class);
        List<String> validCriteria = asList("someCriteria");
        Student student = mock(Student.class);

        when(searchParam.getValidCriteria()).thenReturn(validCriteria);
        when(request.getQueryString()).thenReturn("TestQueryString");
        when(service.getPage(searchParam, 23, "TestQueryString")).thenReturn(students);
        when(students.getStudents()).thenReturn(asList(student));

        String view = controller.monthlyReports(23, searchParam, studentModel, request);

        assertThat(view, is("admin/monthlyreportsPage"));
        assertThat(studentModel.get("page"), is((Object) students));
       
    }

    @Test
    public void checkRandomWord(){
        
    }
}
