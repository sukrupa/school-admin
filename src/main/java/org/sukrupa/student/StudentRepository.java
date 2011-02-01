package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

	private static final String STUDENT_CLASS = "studentClass";
	private static final String SEX = "sex";
	private static final String CASTE = "caste";
	private static final String AREA = "area";
	private static final String AGE = "age";
	private static final String TALENT = "talent";
	private static final String NAME = "name";
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

	private Criteria addOrderCriteria(Criteria criteria) {
		return criteria.addOrder(Order.asc(SEX).ignoreCase()).addOrder(Order.asc(NAME).ignoreCase());
	}

	public List<Student> singleParametricSearch(String studentClass, String sex,
	                                            String caste, String area, String ageFrom, String ageTo, String talent) {
		Conjunction conjunction = createConjunction(studentClass, sex, caste, area, talent);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
		List<Student> students = addOrderCriteria(criteria).add(conjunction).list();

		return getStudentsWithinAgeRange(Integer.parseInt(ageFrom), Integer.parseInt(ageTo), students);
	}

	private List<Student> getStudentsWithinAgeRange(int ageFrom, int ageTo, List<Student> students) {
		List<Student> toRemove = new ArrayList<Student>();
		for (Student s : students) {
			if (s.getAge() < ageFrom || s.getAge() > ageTo) {
				toRemove.add(s);
			}
		}
		students.removeAll(toRemove);
		return students;
	}

	private Conjunction createConjunction(String studentClass, String sex, String caste, String area, String talent) {
		Conjunction conjunction = Restrictions.conjunction();
		addRestrictionIfNotEmpty(STUDENT_CLASS, studentClass, conjunction);
		addRestrictionIfNotEmpty(SEX, sex, conjunction);
		addRestrictionIfNotEmpty(CASTE, caste, conjunction);
		addRestrictionIfNotEmpty(AREA, area, conjunction);
		addRestrictionIfNotEmpty(TALENT, talent, conjunction);
		return conjunction;
	}

	private void addRestrictionIfNotEmpty(String field, String parameter, Conjunction conjunction) {
		if (!parameter.isEmpty()) {
			conjunction.add(Restrictions.eq(field, parameter));
		}
	}
}
