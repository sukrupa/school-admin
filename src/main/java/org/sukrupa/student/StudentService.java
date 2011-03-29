package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.platform.DoNotRemove;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    StudentRepository studentRepository;
    private TalentRepository talentRepository;
    private ReferenceDataRepository referenceDataRepository;
    private StudentFactory studentFactory;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 15;

    @DoNotRemove
    StudentService() {
    }

    @Autowired
    public StudentService(StudentRepository studentRepository, TalentRepository talentRepository,
                          ReferenceDataRepository referenceDataRepository, StudentFactory studentFactory){
        this.studentRepository = studentRepository;
        this.talentRepository = talentRepository;
        this.referenceDataRepository = referenceDataRepository;
        this.studentFactory = studentFactory;
    }

    public Student load(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Set<Student> load(String... studentIds) {
        return studentRepository.findByStudentIds(studentIds);
    }

    public int promoteStudentsToNextClass() {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            student.promote();
            studentRepository.put(student);
        }

        return students.size();
    }

    public Student update(StudentCreateOrUpdateParameter studentParam) {
        Student student = studentRepository.findByStudentId(studentParam.getStudentId());
        if (student == null) { //TODO is this test needed?
            return null;
        }
        student.updateFrom(studentParam, talentRepository.findTalents(studentParam.getTalents()));

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


    public ReferenceData getReferenceData() {
        return referenceDataRepository.getReferenceData();
    }

    public Student create(String studentId, String name, String dateOfBirth) {
        Student student = studentFactory.create(studentId,name,dateOfBirth);
        studentRepository.put(student);
        return student;
    }
}
