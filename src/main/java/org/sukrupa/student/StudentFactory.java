package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class StudentFactory {

    public Student createBasic(StudentCreateOrUpdateParameter studentParam)  {

        Student student = new Student(studentParam.getStudentId(), studentParam.getName(),studentParam.getDateOfBirth());
        return student;
    }

}
