package org.sukrupa.event;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventCreateParameterValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return clazz.equals(EventCreateParameter.class);
    }

    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "date.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attendees", "attendees.empty");
    }
}
