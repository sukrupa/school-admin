package org.sukrupa.student;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class StudentFactory {

    public Student create(String studentId, String name, String dateOfBirth) {
        return new Student(studentId, name, dateOfBirth);
    }

    public Student create(String studentId, String name, String dateOfBirth, Talent talent) {
        Student student = new Student(studentId, name, dateOfBirth);
        student.getTalents().add(talent);
        return student;
    }

}
