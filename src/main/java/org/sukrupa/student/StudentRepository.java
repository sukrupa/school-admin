package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.student.db.FindStudentsHibernateCriteria;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Repository
public class StudentRepository {

	private SessionFactory sessionFactory;
	private FindStudentsHibernateCriteria findStudentsHibernateCriteria;

	@Autowired
	public StudentRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		findStudentsHibernateCriteria = new FindStudentsHibernateCriteria(this.sessionFactory);
	}

	public void put(Student student) {
		session().saveOrUpdate(student);
		session().flush();
	}

	public Student update(UpdateStudentParameter studentParam) {
		Student student = findByStudentId(studentParam.getStudentId());
		if (student == null) {
			return null;
		}
		student.updateFrom(studentParam, findTalents(studentParam.getTalents()));

		session().save(student);
		session().flush();
		return student;
	}

	public int count(StudentSearchParameter searchParam) {
		Criteria countCriteria = findStudentsHibernateCriteria.countMatchingResultsCriteria(searchParam);
		return ((Number) countCriteria.uniqueResult()).intValue();
	}

	public List<Student> findAll() {
		return query("from Student").list();
	}

	public List<Student> findBy(StudentSearchParameter searchParam, int firstIndex, int maxResults) {
		Criteria getPageCriteria = findStudentsHibernateCriteria.orderedSearchCriteria(searchParam);

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

	public Set<Talent> findTalents(Set<String> talentsDecriptions) {
		if (talentsDecriptions == null) {
			return Sets.newHashSet();
		}

		Disjunction disjunction = Restrictions.disjunction();
		for (String description : talentsDecriptions) {
			disjunction.add(Restrictions.eq("description", description));
		}
		Criteria criteria = session().createCriteria(Talent.class).add(disjunction);
		return new HashSet<Talent>(criteria.list());
	}

	private Query query(String hql) {
		return session().createQuery(hql);
	}

	private Session session() {
		return sessionFactory.getCurrentSession();
	}

}
