package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.students.ReferenceData;
import org.sukrupa.app.students.ReferenceDataRepository;
import org.sukrupa.platform.DoNotRemove;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
	
    private StudentRepository studentRepository;
	private TalentRepository talentRepository;
    private ReferenceDataRepository referenceDataRepository;
    static final int NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE = 15;

	@DoNotRemove
	StudentService() {}

	@Autowired
	public StudentService(StudentRepository studentRepository, TalentRepository talentRepository, ReferenceDataRepository referenceDataRepository) {
		this.studentRepository = studentRepository;
		this.talentRepository = talentRepository;
		this.referenceDataRepository = referenceDataRepository;
	}

    public Student load(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Set<Student> load(String... studentIds) {
        return studentRepository.findByStudentIds(studentIds);
    }

    public int promoteStudentsToNextClass() {
        List<Student> students = studentRepository.findAll();

        for(Student student : students){
            student.promote();
            studentRepository.put(student);
        }

       return students.size();
    }

    public Student update(StudentUpdateParameter studentParam) {
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

        List<Student> students = studentRepository.findBy(searchParam, firstIndex, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);

        int totalNumberOfResults = studentRepository.count(searchParam);
        int totalNumberOfPages = (totalNumberOfResults - 1) / NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1;

        return new StudentListPage(students, pageNumber, totalNumberOfPages, queryString);
    }

    public ReferenceData getReferenceData() {
        return referenceDataRepository.getReferenceData();
    }
}
