package org.sukrupa.app.admin.talents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.Talent;
import org.sukrupa.student.TalentFactory;
import org.sukrupa.student.TalentRepository;


@Component
public class TalentsService {


    private TalentRepository talentRepository;
    private TalentFactory talentFactory;

    @DoNotRemove
    public TalentsService(){
    }

    @Autowired
    public TalentsService(TalentRepository talentRepository, TalentFactory talentFactory) {
        this.talentRepository = talentRepository;
        this.talentFactory = talentFactory;
    }

    public Talent create(TalentForm talentForm) {
       Talent newTalent = talentFactory.create(talentForm.getDescription());
       talentRepository.put(newTalent);
        return newTalent;
    }
}
