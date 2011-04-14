package org.sukrupa.app.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.sukrupa.app.admin.talents.TalentForm;
import org.sukrupa.student.TalentRepository;

@Service
public class TalentValidator implements Validator{

    private TalentRepository talentRepository;

    @Autowired
    public TalentValidator(TalentRepository talentRepositoryIn){
        this.talentRepository = talentRepositoryIn;
    }

    @Override
     public boolean supports(Class<?> classIn) {
        return TalentForm.class.isAssignableFrom(classIn);
    }

    @Override
    public void validate(Object target, Errors errors){
        TalentForm talentParam = (TalentForm)target;

        String talentEntered = talentParam.getDescription();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "talent", "talent.required", "Please enter a talent.");
        //check for duplicates
    }
}
