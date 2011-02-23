package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.student.db.StudentsSearchCriteriaGenerator;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Repository
public class StudentRepository {

	private SessionFactory sessionFactory;
	private StudentsSearchCriteriaGenerator studentsSearchCriteriaGenerator;

	@Autowired
	public StudentRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		studentsSearchCriteriaGenerator = new StudentsSearchCriteriaGenerator(this.sessionFactory);
	}

	public void put(Student student) {
		session().saveOrUpdate(student);
		session().flush();
	}

	public Student update(Student student) {
		session().save(student);
		session().flush();
		return student;
	}

    public int getCountBasedOn(Criteria countCriteria) {
        return Integer.parseInt(countCriteria.uniqueResult().toString());
    }

    public List<Student> findAll() {
		return query("from Student").list();
	}

    public List<Student> findByCriteria(Criteria getPageCriteria, int firstIndex, int maxResults) {
        getPageCriteria.setFirstResult(firstIndex);
        getPageCriteria.setMaxResults(maxResults);
        return getPageCriteria.list();
    }

    public Student findByStudentId(String studentId) {
		return (Student) query("from Student where studentId = ?").setParameter(0, studentId).uniqueResult();
	}

	public Set<Student> findByStudentIds(String... studentIds) {
		return newHashSet(query("from Student where studentId in (:ids)").setParameterList("ids", studentIds).list());
	}

	private Query query(String hql) {
		return session().createQuery(hql);
	}

	private Session session() {
		return sessionFactory.getCurrentSession();
	}

}
