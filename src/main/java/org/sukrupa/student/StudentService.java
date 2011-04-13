package org.sukrupa.student;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import org.sukrupa.platform.*;

import java.util.*;

@Service
public class StudentService {

    private static final String ANNUAL_CLASS_UPDATE = "annual class update";
    StudentRepository studentRepository;
    private TalentRepository talentRepository;
    private ReferenceDataRepository referenceDataRepository;
    private StudentFactory studentFactory;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 15;
    private SystemEventLogRepository systemEventLogRepository;
    private int classUpdateCount;

    @DoNotRemove StudentService() {
    }

    @Autowired
    public StudentService(StudentRepository studentRepository, TalentRepository talentRepository,
                          ReferenceDataRepository referenceDataRepository, StudentFactory studentFactory, SystemEventLogRepository systemEventLogRepository) {
        this.studentRepository = studentRepository;
        this.talentRepository = talentRepository;
        this.referenceDataRepository = referenceDataRepository;
        this.studentFactory = studentFactory;
        this.systemEventLogRepository = systemEventLogRepository;
    }

    public Student load(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Student create(StudentForm studentForm) {
        Student student = studentFactory.create(studentForm.getStudentId(),
                studentForm.getName(),
                studentForm.getDateOfBirth(),
                studentForm.getGender());

        studentRepository.put(student);
        return student;
    }

    public Student update(StudentForm studentForm) {
        Student student = studentRepository.findByStudentId(studentForm.getStudentId());
        if (student == null) { //TODO is this test needed? NOT if studentRepository throws an exception when it doesnt find a student - go have a look at it, write a test that fails then remove this if statment.
            return null;
        }

        Set<Talent> talents = talentRepository.findTalents(studentForm.getTalentDescriptions());

        student.updateFrom(studentForm, talents);

        return studentRepository.update(student);
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


    public StudentFormReferenceData getReferenceData() {
        return referenceDataRepository.getStudentFormReferenceData();
    }
}

