package org.sukrupa.app.admin.talents;

import org.sukrupa.student.Talent;
import org.sukrupa.student.TalentRepository;

public class TalentsService {

    private TalentRepository talentRepository;

    public TalentsService(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public void createTalent(TalentForm talentForm) {
       Talent newTalent = talentForm.createTalent();
       talentRepository.save(newTalent);
    }
}
