package org.sukrupa.event;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventFormValidator {
    public boolean supports(EventCreateParameter eventCreateParameter) {
        return eventCreateParameter instanceof EventCreateParameter;
    }

    public void validate(EventCreateParameter eventCreateParameter, Errors errors) {
        ValidationUtils.rejectIfEmpty(
                errors, "title", "title.required");
        ValidationUtils.rejectIfEmpty(
            errors, "description", "description.required");
        ValidationUtils.rejectIfEmpty(
            errors, "date", "date.required");
        ValidationUtils.rejectIfEmpty(
            errors, "attendees", "attendees.required");
        try {
            new SimpleDateFormat("dd-mm-yyyy").parse(eventCreateParameter.getDate());
        } catch (ParseException e) {
            errors.rejectValue("date", "date.invalidDate");
        }
    }
}
