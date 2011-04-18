package org.sukrupa.app.admin.talents;

import org.sukrupa.platform.RequiredByFramework;

public class TalentForm {

    private String description;

    public TalentForm(String descriptionIn){
        this.description = descriptionIn;
    }

    @RequiredByFramework
    public TalentForm(){
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String descriptionIn){
        this.description = descriptionIn;
    }
}
