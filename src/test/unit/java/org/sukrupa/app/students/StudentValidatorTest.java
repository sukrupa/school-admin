package org.sukrupa.app.students;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.date.DateManipulation;
import org.sukrupa.student.StudentProfileForm;
import org.sukrupa.student.StudentUpdateParameterBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentValidatorTest {
    private static final String VALID_DOB = "11-11-2001";

    @Test
    public void shouldAddANameErrorIfNoNameProvided() {
        StudentProfileForm paramsWithNoName = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(VALID_DOB)
                .name("").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoName, "bob");

        new StudentValidator().validate(paramsWithNoName, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("name"), is(1));
    }

    @Test
    public void shouldAddStudentIdIfNoIdProvided() {
        StudentProfileForm paramsWithNoStudentId = new StudentUpdateParameterBuilder()
                .studentId("")
                .dateOfBirth(VALID_DOB)
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoStudentId, "bob");

        new StudentValidator().validate(paramsWithNoStudentId, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("studentId"), is(1));
    }

    @Test
    public void shouldThrowErrorIfNoDateIsProvided() {
        StudentProfileForm paramsWithNoDateOfBirth = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("")
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoDateOfBirth, "bob");

        new StudentValidator().validate(paramsWithNoDateOfBirth, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));

    }

    @Test
    public void shouldThrowErrorIfIncorrectDateFormat() {
        StudentProfileForm paramsWithWrongDate = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("33/22/2001")
                .name("Steve").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithWrongDate, "Steve");

        new StudentValidator().validate(paramsWithWrongDate, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));
    }


    @Test
    public void shouldNotAcceptFutureDate() {
        DateManipulation.freezeDateToMidnightOn_31_12_2010();
        Date futureDate = new Date(1,1,2011);
        StudentProfileForm paramsWithFutureDate = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(futureDate)
                .name("futurechild").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithFutureDate,"futurechild");
        new StudentValidator().validate(paramsWithFutureDate, errors);
        assertThat(errors.getErrorCount(),is(1));
        assertThat(errors.getFieldErrorCount("dateOfBirth"),is(1));
        DateManipulation.unfreezeTime();
    }

    @Test
    public void shouldNotHaveErrorsIfCorrectMandatoryFieldProvided() {
        StudentProfileForm allFieldsProvided = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(VALID_DOB)
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(allFieldsProvided, "bob");

        new StudentValidator().validate(allFieldsProvided, errors);

        assertThat(errors.getErrorCount(), is(0));
    }
}