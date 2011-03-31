package org.sukrupa.app.students;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.sukrupa.student.StudentCreateOrUpdateParameter;

@Service
public class StudentValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentCreateOrUpdateParameter.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required", "Missing Student Name. Please re-enter.") ;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "studentId", "studentId.required", "Missing Student ID. Please re-enter.") ;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfBirth", "dateOfBirth.required", "Missing Student Date Of Birth. Please re-enter.") ;
    }
}
