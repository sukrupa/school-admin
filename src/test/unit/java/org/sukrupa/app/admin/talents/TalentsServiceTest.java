package org.sukrupa.app.admin.talents;

import org.junit.Test;
import org.sukrupa.student.Talent;
import org.sukrupa.student.TalentFactory;
import org.sukrupa.student.TalentRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TalentsServiceTest {

    @Test
    public void createTalentShouldCreateATalentAndSaveItInTheRepository() throws Exception {
        TalentRepository talentRepository = mock(TalentRepository.class);
        TalentFactory talentFactory = mock(TalentFactory.class);
        TalentForm talentForm = mock(TalentForm.class);

        TalentsService talentService = new TalentsService(talentRepository, talentFactory);

        Talent newTalent = mock(Talent.class);

        when(talentFactory.create(talentForm.getDescription())).thenReturn(newTalent);

        talentService.create(talentForm);

        verify(talentRepository).put(newTalent);

    }



}
