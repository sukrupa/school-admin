package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class StudentFactory {

    public Student create(String studentId, String studentName, String studentDateOfBirth) {
       Student student = new Student(studentId,studentName,studentDateOfBirth);
       return student;
    }
}
