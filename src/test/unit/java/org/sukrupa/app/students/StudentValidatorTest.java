package org.sukrupa.app.students;

import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.sukrupa.student.StudentCreateOrUpdateParameter;
import org.sukrupa.student.StudentUpdateParameterBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StudentValidatorTest {
    @Test
    public void shouldAddANameErrorIfNoNameProvided() {
        StudentCreateOrUpdateParameter paramsWithNoName = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("dob")
                .name("").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoName, "bob");

        new StudentValidator().validate(paramsWithNoName, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("name"), is(1));
    }

    @Test
    public void shouldAddStudentIdIfNoIdProvided() {
       StudentCreateOrUpdateParameter paramsWithNoStudentId = new StudentUpdateParameterBuilder()
                .studentId("")
                .dateOfBirth("dob")
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoStudentId, "bob");

        new StudentValidator().validate(paramsWithNoStudentId, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("studentId"), is(1));
    }

    @Test
    public void shouldAddDateOfBirthIfNoIdProvided() {
       StudentCreateOrUpdateParameter paramsWithNoDateOfBirth = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("")
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoDateOfBirth, "bob");

        new StudentValidator().validate(paramsWithNoDateOfBirth, errors);

        assertThat(errors.getErrorCount(), is(1));
        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));
    }

    @Test
    public void shouldNotHaveErrorsIfMandatoryFieldProvided() {
       StudentCreateOrUpdateParameter allFieldsProvided = new StudentUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("dob")
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(allFieldsProvided, "bob");

        new StudentValidator().validate(allFieldsProvided, errors);

        assertThat(errors.getErrorCount(), is(0));
    }

}
