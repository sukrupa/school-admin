package org.sukrupa.app.admin;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.student.StudentService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminControllerTest {

    private AdminController controller;
    private StudentService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new AdminController(service);
    }
    @Test
    public void shouldDirectToMonthlyReportsPage(){
        String result = controller.monthlyReports();
        System.out.println(result);
        assertThat(controller.monthlyReports(), is("admin/monthlyreportsPage"));
    }



}
