package org.sukrupa.app.admin.talents;

import org.apache.commons.lang.StringUtils;
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
        description = description.toLowerCase();
        description = StringUtils.capitalize(description);
        return description.trim();
    }

    public void setDescription(String descriptionIn){
        this.description = descriptionIn;
    }
}
