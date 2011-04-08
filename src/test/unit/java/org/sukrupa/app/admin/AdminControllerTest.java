package org.sukrupa.app.admin;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.student.StudentService;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdminControllerTest {

    private AdminController controller;

    @Mock
    private StudentService studentService;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new AdminController(studentService);
    }

    @Test
    public void shouldPromoteAllStudents() {
        controller.performAnnualUpdate();

        verify(studentService).promoteStudentsToNextClass();
    }


}
