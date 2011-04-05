package org.sukrupa.student;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

import static java.util.Arrays.asList;

@Component
public class StudentFactory {

    public Student create(String studentId, String name, String dateOfBirth, String gender) {
        return new Student(studentId, name, dateOfBirth, gender);
    }

}
