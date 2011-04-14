package org.sukrupa.student;

import org.springframework.stereotype.Component;

@Component
public class TalentFactory {

    public Talent create(String descriptionIn){
        return new Talent(descriptionIn);
    }
}
