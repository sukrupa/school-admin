package org.sukrupa.student;

import com.google.common.collect.ImmutableClassToInstanceMap;

public class TalentBuilder {


    private String description;

    public TalentBuilder description(String description) {
        this.description = description;
        return this;
    }

    public Talent build() {
        return new Talent(description);
    }
}
