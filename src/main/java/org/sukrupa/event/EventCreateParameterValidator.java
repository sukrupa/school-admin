package org.sukrupa.event;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.sukrupa.app.validation.ValidatorUtils;

public class EventCreateParameterValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return clazz.equals(EventCreateParameter.class);
    }

    public void validate(Object target, Errors errors) {

        if (ValidatorUtils.missingAnyRequiredFields(errors, "title", "description", "date", "attendees")) {
            errors.reject("emptyField", "Please fill in all required fields.");
        }

    }

}
