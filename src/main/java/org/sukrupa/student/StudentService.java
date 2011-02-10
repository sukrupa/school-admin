package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addNoteFor(String studentId, String noteMessage) {
        Student student = repository.load(studentId);
        student.addNote(new Note(noteMessage));
        repository.saveOrUpdate(student);
    }
}
