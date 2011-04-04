package org.sukrupa.app.students;

import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.sukrupa.platform.date.Date;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentCreateOrUpdateParameter;

@Service
public class StudentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentCreateOrUpdateParameter.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        StudentCreateOrUpdateParameter studentParam = (StudentCreateOrUpdateParameter) target;
        String dateOfBirthString = studentParam.getDateOfBirth();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Missing Student Name. Please re-enter.") ;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentId", "studentId.required", "Missing Student ID. Please re-enter.") ;
        if (!dateOfBirthString.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d")) {
            errors.rejectValue("dateOfBirth","dateOfBirth.required","Please enter a valid date format");
        } else {
            Date dateOfBirth = Date.parse(dateOfBirthString,"");
            if (!dateOfBirth.isInThePast()) {
                errors.rejectValue("dateOfBirth","dateOfBirth.required","Please use a date in the past");
            }
        }

    }


}
