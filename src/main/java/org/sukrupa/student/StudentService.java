package org.sukrupa.student;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.sukrupa.app.services.StudentImageRepository;
import org.sukrupa.platform.*;

import java.util.*;

@Service
public class StudentService {

    private static final String ANNUAL_CLASS_UPDATE = "annual class update";
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 15; // protected because the tests use it, seems a bit dodgy.

    private StudentRepository studentRepository;
    private TalentRepository talentRepository;
    private SystemEventLogRepository systemEventLogRepository;
    private StudentImageRepository studentImageRepository;

    @RequiredByFramework
    public StudentService() {
    }

    @Autowired
    public StudentService(StudentRepository studentRepository, TalentRepository talentRepository,
                          StudentImageRepository studentImageRepository, SystemEventLogRepository systemEventLogRepository) {
        this.studentRepository = studentRepository;
        this.talentRepository = talentRepository;
        this.systemEventLogRepository = systemEventLogRepository;
        this.studentImageRepository = studentImageRepository;
    }

    public Student load(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Student create(StudentForm studentForm) {
        Student student = new Student(studentForm.getStudentId(), studentForm.getName(), studentForm.getDateOfBirth(), studentForm.getGender());

        studentRepository.put(student);
        return student;
    }

    public Student update(StudentForm studentForm) {
        Student student = studentRepository.findByStudentId(studentForm.getStudentId());

        if (student == null) { //TODO is this test needed? NOT if studentRepository throws an exception when it doesnt find a student - go have a look at it, write a test that fails then remove this if statment.
            return null;
        }

        setFormDataOnStudent(studentForm, student);

        if(studentForm.hasImage()){
            studentForm.createImage(studentImageRepository);
        }

        return studentRepository.update(student);
    }

    public void setFormDataOnStudent(StudentForm studentForm, Student student) {
        Set<Talent> talents = talentRepository.findTalents(studentForm.getTalentDescriptions());

        student.updateStudent(studentForm, talents);
    }


    @Transactional
    public void addNoteFor(String studentId, String noteMessage) {
        Student student = studentRepository.findByStudentId(studentId);
        student.addNote(new Note(noteMessage));
        studentRepository.put(student);

    }

    public StudentListPage getPage(StudentSearchParameter searchParam, int pageNumber, String queryString) {
        int firstIndex = (pageNumber - 1) * NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

        List<Student> students = studentRepository.findBySearchParameter(searchParam, firstIndex, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);

        int totalNumberOfResults = studentRepository.getCountBasedOn(searchParam);
        int totalNumberOfPages = (totalNumberOfResults - 1) / NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1;

        return new StudentListPage(students, pageNumber, totalNumberOfPages, queryString);
    }


    public StudentReferenceData getStudentReferenceData() {
        return new StudentReferenceData(getTalentRepository());
    }

    public TalentRepository getTalentRepository() {
        return talentRepository;
    }
}

