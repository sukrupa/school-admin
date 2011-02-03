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

	public List<Student> parametricSearch(String studentClass, String gender,
	                                      String caste, String area, String ageFrom, String ageTo, String talent) {

		Conjunction conjunction = createConjunction(studentClass, gender, caste, area);
		if (!ageFrom.isEmpty()) {
			LocalDate birthDateFrom = computeBirthDateFromAge(Integer.parseInt(ageFrom));
			LocalDate birthDateTo = computeBirthDateFromAge(Integer.parseInt(ageTo));
			conjunction.add(Restrictions.between(DATE_OF_BIRTH, birthDateTo, birthDateFrom));
		}

		Criteria criteria = addOrderCriteria(sessionFactory.getCurrentSession().createCriteria(Student.class));
		criteria.add(conjunction);
		addTalentsSearchCriteria(criteria, talent);

		return criteria.list();
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
}
