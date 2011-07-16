package org.sukrupa.app.admin;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.sukrupa.student.AnnualClassUpdateService;

import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AnnualUpdateControllerTest {

    private AnnualUpdateController controller;

    @Mock
    private AnnualClassUpdateService annaulClassUpdateService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new AnnualUpdateController(annaulClassUpdateService);
    }

    @Test
    public void shouldPromoteAllStudents() {
        controller.performAnnualUpdate();

        verify(annaulClassUpdateService).promoteStudentsToNextClass();
    }

    @Test
    public void shouldUndoPromoteAllStudents(){

        controller.undoAnnualUpdate();
        verify(annaulClassUpdateService).undoPromoteStudentsToNextClass();

    }

}
