package org.sukrupa.student;

import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Repository
public class StudentRepository {

    private static final String STUDENT_ID = "studentId";

    private final SessionFactory sessionFactory;
    private final StudentCriteriaBuilder studentCriteriaBuilder;

    @Autowired
    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        studentCriteriaBuilder = new StudentCriteriaBuilder(this.sessionFactory);
    }

//    @SuppressWarnings("unchecked")
//    public List<Student> findAll() {
//        Criteria criteria = session().createCriteria(Student.class);
//        return addOrderCriteria(criteria).list();
//    }

    public Student load(String studentId) {
        Criteria criteria = session()
                .createCriteria(Student.class)
                .add(Restrictions.eq(STUDENT_ID, studentId));
        return (Student) criteria.uniqueResult();
    }

    public Set<Student> load(String... studentIds) {
        Query query = session().createQuery("from Student where studentId in (:studentIds)");
        query.setParameterList("studentIds", studentIds);
        return newHashSet(query.list());
    }

    public List<Student> parametricSearch(StudentSearchParameter searchParam, int firstIndex, int maxResults) {
        Criteria getPageCriteria = studentCriteriaBuilder.orderedSearchCriteria(searchParam);

        getPageCriteria.setFirstResult(firstIndex);
        getPageCriteria.setMaxResults(maxResults);

        return getPageCriteria.list();
    }

    public int countResults(StudentSearchParameter searchParam) {
        Criteria countCriteria = studentCriteriaBuilder.countMatchingResultsCriteria(searchParam);
        return ((Number) countCriteria.uniqueResult()).intValue();
    }

    public Student update(UpdateStudentParameter studentParam) {
        Student student = load(studentParam.getStudentId());
        if (student == null) {
            return null;
        }
        student.updateFrom(studentParam, findTalents(studentParam.getTalents()));

        session().save(student);
        session().flush();
        return student;
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

    public void saveOrUpdate(Student student) {
        session().saveOrUpdate(student);
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
