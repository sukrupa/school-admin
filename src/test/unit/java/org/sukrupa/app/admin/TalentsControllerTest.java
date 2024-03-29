package org.sukrupa.app.admin;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.sukrupa.app.admin.talents.TalentForm;
import org.sukrupa.app.admin.talents.TalentsService;
import org.sukrupa.app.students.StudentsController;
import org.sukrupa.app.students.TalentValidator;
import org.sukrupa.student.TalentRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class TalentsControllerTest {


    @Test
    public void createShouldCreateTalentUsingTalentsService() throws Exception {
        TalentForm talentForm = mock(TalentForm.class);
        TalentsService talentsService = mock(TalentsService.class);
        TalentRepository talentRepository = mock(TalentRepository.class);

        TalentsController controller = new TalentsController(talentsService, talentRepository);
        controller.create(talentForm);

        verify(talentsService).create(talentForm);
    }



}
