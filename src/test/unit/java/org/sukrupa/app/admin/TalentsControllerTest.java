package org.sukrupa.app.admin;

import org.junit.Test;
import org.sukrupa.app.admin.talents.TalentForm;
import org.sukrupa.app.admin.talents.TalentsService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TalentsControllerTest {

    @Test
    public void createShouldCreateTalentUsingTalentsService() throws Exception {
        TalentForm talentForm = mock(TalentForm.class);
        TalentsService talentsService = mock(TalentsService.class);

        TalentsController controller = new TalentsController(talentsService);
        controller.create(talentForm);

        verify(talentsService).create(talentForm);
    }


}
