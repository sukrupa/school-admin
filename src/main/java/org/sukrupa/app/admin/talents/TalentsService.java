package org.sukrupa.app.admin.talents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.student.Talent;
import org.sukrupa.student.TalentRepository;


@Component
public class TalentsService {


    private TalentRepository talentRepository;

    @Autowired
    public TalentsService(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public void createTalent(TalentForm talentForm) {
       Talent newTalent = talentForm.createTalent();
       talentRepository.save(newTalent);
    }
}
