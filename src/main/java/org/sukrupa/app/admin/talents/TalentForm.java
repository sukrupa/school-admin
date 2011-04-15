package org.sukrupa.app.admin.talents;

import org.sukrupa.platform.DoNotRemove;
import org.sukrupa.student.Talent;

public class TalentForm {

    private String description;

    public TalentForm(String descriptionIn){
        this.description = descriptionIn;
    }

    @DoNotRemove
    public TalentForm(){
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String descriptionIn){
        this.description = descriptionIn;
    }
}
