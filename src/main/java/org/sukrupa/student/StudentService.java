package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.DoNotRemove;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private StudentRepository repository;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 5;

    public Student load(String studentId) {
        return repository.load(studentId);
    }

    public Set<Student> load(String... studentIds) {
        return repository.load(studentIds);
    }

    public Student update(UpdateStudentParameter studentParam) {
        return repository.update(studentParam);
    }

    @DoNotRemove
    StudentService() {}

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addNoteFor(String studentId, String noteMessage) {
        Student student = repository.load(studentId);
        student.addNote(new Note(noteMessage));
        repository.saveOrUpdate(student);

    }
    public StudentListPage getPage(StudentSearchParameter searchParam) {
        int firstIndex = (searchParam.getPage() - 1) * NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

        List<Student> students = repository.parametricSearch(searchParam, firstIndex, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);

        int totalNumberOfResults = repository.countResults(searchParam);
        int totalNumberOfPages = (totalNumberOfResults - 1) / NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1;

        return new StudentListPage(students, searchParam.getPage(), totalNumberOfPages);
    }
}
