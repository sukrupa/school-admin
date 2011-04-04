package org.sukrupa.student;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

import static java.util.Arrays.asList;

@Component
public class StudentFactory {

    public Student create(String studentId, String name, String dateOfBirth) {
        return new Student(studentId, name, dateOfBirth);
    }

    public Student create(String studentId, String name, String dateOfBirth, Talent... talents) {
        Student student = new Student(studentId, name, dateOfBirth);
        student.getTalents().addAll(asList(talents));
        return student;
    }

}
