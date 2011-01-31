package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Student> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(Student.class).list();
    }

	public List<Student> singleParametricSearch(String studentClass) {
		return sessionFactory.getCurrentSession().createCriteria(Student.class).add(
				Restrictions.eq("studentClass", studentClass)
		).list();
	}
}
