package org.sukrupa.student;

import com.google.common.collect.ImmutableClassToInstanceMap;

public class TalentBuilder {


    private String description;

    public static Talent talent(String talentDescription) {
        return new TalentBuilder().description(talentDescription).build();
    }

    public TalentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Talent build() {
        return new Talent(description);
    }
}
