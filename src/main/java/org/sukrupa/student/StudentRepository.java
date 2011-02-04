package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private static final String STUDENT_CLASS = "studentClass";
    private static final String GENDER = "gender";
    private static final String CASTE = "caste";
    private static final String AREA = "area";
    private static final String NAME = "name";
	private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String STUDENT_ID = "studentId";
	private static final String TALENTS = "talents";
	private static final String DESCRIPTION = "description";
	private final SessionFactory sessionFactory;

    @Autowired
    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        return addOrderCriteria(criteria).list();
    }

    public Student find(String studentId) {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(Student.class)
                .add(Restrictions.eq(STUDENT_ID, studentId));
        return (Student) criteria.uniqueResult();
    }

	public List<Student> parametricSearch(StudentSearchParameter searchParam) {

		Conjunction conjunction = createConjunction(searchParam.getStudentClass(), searchParam.getGender(),
				searchParam.getCaste(), searchParam.getArea());
		if (!searchParam.getAgeFrom().isEmpty()) {
			LocalDate birthDateFrom = computeBirthDateFromAge(Integer.parseInt(searchParam.getAgeFrom()));
			LocalDate birthDateTo = computeBirthDateFromAge(getInclusiveUpperBoundAge(searchParam.getAgeTo()));
			conjunction.add(Restrictions.between(DATE_OF_BIRTH, birthDateTo, birthDateFrom));
		}

		Criteria criteria = addOrderCriteria(sessionFactory.getCurrentSession().createCriteria(Student.class));
		criteria.add(conjunction);
		addTalentsSearchCriteria(criteria, searchParam.getTalent());

		return criteria.list();
	}

	private int getInclusiveUpperBoundAge(String ageTo) {
		return Integer.parseInt(ageTo)+1;
	}

	private void addTalentsSearchCriteria(Criteria criteria, String talent) {
		if (!talent.isEmpty()) {
			criteria.createCriteria(TALENTS).add(Restrictions.eq(DESCRIPTION, talent));
		}
	}

	private LocalDate computeBirthDateFromAge(int age) {
		return new LocalDate().minusYears(age);
	}

    private Criteria addOrderCriteria(Criteria criteria) {
        return criteria.addOrder(Order.asc(GENDER).ignoreCase()).addOrder(Order.asc(NAME).ignoreCase());
    }

    private Conjunction createConjunction(String studentClass, String gender, String caste, String area) {
        Conjunction conjunction = Restrictions.conjunction();
        addRestrictionIfNotEmpty(STUDENT_CLASS, studentClass, conjunction);
        addRestrictionIfNotEmpty(GENDER, gender, conjunction);
        addRestrictionIfNotEmpty(CASTE, caste, conjunction);
        addRestrictionIfNotEmpty(AREA, area, conjunction);
        return conjunction;
    }

    private void addRestrictionIfNotEmpty(String field, String parameter, Conjunction conjunction) {
        if (!parameter.isEmpty()) {
            conjunction.add(Restrictions.eq(field, parameter));
        }
    }

    public boolean update(UpdateStudentParameter studentParam) {
        Student student = find(studentParam.getStudentId());
        if (student==null){
            System.out.println("Student id " + studentParam.getStudentId() + " not found");
            return false;
        }
        student.setStudentClass(studentParam.getStudentClass());
        student.setGender(studentParam.getGender());
        student.setName(studentParam.getName());
        student.setReligion(studentParam.getReligion());
        student.setCaste(studentParam.getCaste());
        student.setSubCaste(studentParam.getSubCaste());
        student.setArea(studentParam.getArea());
        sessionFactory.getCurrentSession().save(student);
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
