package org.sukrupa.app.admin.talents;

import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.Talent;

public class TalentForm {

    private String description;

    public Talent createTalent() {
        return null;
    }

    @DoNotRemove
    public TalentForm(){
    }

    public TalentForm(String descriptionIn){
        this.description = descriptionIn;
    }

    public String getDescription(){
        return description;
    }
}
