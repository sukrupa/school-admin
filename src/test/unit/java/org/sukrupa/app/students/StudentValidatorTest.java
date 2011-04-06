package org.sukrupa.app.students;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.sukrupa.platform.date.Date;
import org.sukrupa.platform.date.DateManipulation;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentCreateOrUpdateParameterBuilder;
import org.sukrupa.student.StudentProfileForm;
import org.sukrupa.student.StudentRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentValidatorTest {
    private static final String VALID_DOB = "11-11-2001";

    @Mock
    private StudentRepository studentRepository;

    private StudentValidator studentValidator;

    @Before
    public void setUp() {
        studentValidator = new StudentValidator(studentRepository);
    }

    @Test
    public void shouldAddANameErrorIfNoNameProvided() {
        StudentProfileForm paramsWithNoName = new StudentCreateOrUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(VALID_DOB)
                .name("").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoName, "bob");


        studentValidator.validate(paramsWithNoName, errors);

        assertThat(errors.getFieldErrorCount("name"), is(1));
    }

    @Test
    public void shouldAddStudentIdIfNoIdProvided() {
        StudentProfileForm paramsWithNoStudentId = new StudentCreateOrUpdateParameterBuilder()
                .studentId("")
                .dateOfBirth(VALID_DOB)
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoStudentId, "bob");

        studentValidator.validate(paramsWithNoStudentId, errors);

        assertThat(errors.getFieldErrorCount("studentId"), is(1));
    }

    @Test
    public void shouldReturnErrorIfStudentIDExists() {
        StudentProfileForm paramsWithDuplicateStudentId = new StudentCreateOrUpdateParameterBuilder()
                .studentId("1234")
                .dateOfBirth(VALID_DOB)
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithDuplicateStudentId, "bob");

        Student student = mock(Student.class);
        when(studentRepository.findByStudentId("1234")).thenReturn(student);
        studentValidator.validate(paramsWithDuplicateStudentId, errors);
        verify(studentRepository.findByStudentId("1234"));

        assertThat(errors.getFieldErrorCount("studentId"), is(1));
    }



    @Test
    public void shouldThrowErrorIfNoDateIsProvided() {
        StudentProfileForm paramsWithNoDateOfBirth = new StudentCreateOrUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("")
                .name("bob").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoDateOfBirth, "bob");

        studentValidator.validate(paramsWithNoDateOfBirth, errors);

        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));

    }

    @Test
    public void shouldThrowErrorIfIncorrectDateFormat() {
        StudentProfileForm paramsWithWrongDate = new StudentCreateOrUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth("33/22/2001")
                .name("Steve").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithWrongDate, "Steve");

        studentValidator.validate(paramsWithWrongDate, errors);

        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));
    }


    @Test
    public void shouldNotAcceptFutureDate() {
        DateManipulation.freezeDateToMidnightOn_31_12_2010();
        Date futureDate = new Date(1, 1, 2011);
        StudentProfileForm paramsWithFutureDate = new StudentCreateOrUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(futureDate)
                .name("futurechild").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithFutureDate, "futurechild");
        studentValidator.validate(paramsWithFutureDate, errors);
        assertThat(errors.getFieldErrorCount("dateOfBirth"), is(1));
        DateManipulation.unfreezeTime();
    }

    @Test
    public void shouldNotHaveErrorsIfCorrectMandatoryFieldProvided() {
        StudentProfileForm allFieldsProvided = new StudentCreateOrUpdateParameterBuilder()
                .studentId("abc")
                .dateOfBirth(VALID_DOB)
                .name("bob").gender("male").build();
        Errors errors = new BeanPropertyBindingResult(allFieldsProvided, "bob");

        studentValidator.validate(allFieldsProvided, errors);

        assertThat(errors.getErrorCount(), is(0));
    }

    @Test
    public void shouldAddAGenderErrorIfNoGenderProvided() {
        StudentProfileForm paramsWithNoGender = new StudentCreateOrUpdateParameterBuilder()
                .gender("").build();
        Errors errors = new BeanPropertyBindingResult(paramsWithNoGender, "bob");

        studentValidator.validate(paramsWithNoGender, errors);

        assertThat(errors.getFieldErrorCount("gender"), is(1));
    }
}
