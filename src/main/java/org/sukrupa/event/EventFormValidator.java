package org.sukrupa.event;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventFormValidator {
    public boolean supports(EventRecord eventRecord) {
        return eventRecord instanceof EventRecord;
    }

    public void validate(EventRecord eventRecord, Errors errors) {
        ValidationUtils.rejectIfEmpty(
                errors, "title", "title.required");
        ValidationUtils.rejectIfEmpty(
            errors, "description", "description.required");
        ValidationUtils.rejectIfEmpty(
            errors, "date", "date.required");
        ValidationUtils.rejectIfEmpty(
            errors, "attendees", "attendees.required");
        try {
            new SimpleDateFormat("dd-mm-yyyy").parse(eventRecord.getDate());
        } catch (ParseException e) {
            errors.rejectValue("date", "date.invalidDate");
        }
    }
}
