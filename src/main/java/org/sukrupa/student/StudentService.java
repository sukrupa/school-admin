package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.db.HibernateConstructor;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
    private StudentRepository repository;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 15;

    public Student load(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public Set<Student> load(String... studentIds) {
        return repository.findByStudentIds(studentIds);
    }


    public int promoteStudentsToNextClass() {
        List<Student> students = repository.findAll();

        for(Student student : students){
            student.promote();
            repository.put(student);
        }

       return students.size();
    }

    public Student update(StudentUpdateParameter studentUpdateParam) {
        return repository.update(studentUpdateParam);
    }

    @HibernateConstructor
    StudentService() {}

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void addNoteFor(String studentId, String noteMessage) {
        Student student = repository.findByStudentId(studentId);
        student.addNote(new Note(noteMessage));
        repository.put(student);

    }
    public StudentListPage getPage(StudentSearchParameter searchParam, int pageNumber, String queryString) {
        int firstIndex = (pageNumber - 1) * NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

        List<Student> students = repository.findBy(searchParam, firstIndex, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);

        int totalNumberOfResults = repository.count(searchParam);
        int totalNumberOfPages = (totalNumberOfResults - 1) / NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1;

        return new StudentListPage(students, pageNumber, totalNumberOfPages, queryString);
    }


}
