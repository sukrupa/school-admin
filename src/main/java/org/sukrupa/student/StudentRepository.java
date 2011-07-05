package org.sukrupa.student;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.lang.String.format;

@Repository
public class StudentRepository {

    private static final Logger LOG = Logger.getLogger(StudentService.class);


    private SessionFactory sessionFactory;
    private StudentsSearchCriteriaGenerator studentsSearchCriteriaGenerator;

    @Autowired
    public StudentRepository(SessionFactory sessionFactory, StudentsSearchCriteriaGenerator studentsSearchCriteriaGenerator) {
        this.sessionFactory = sessionFactory;
        this.studentsSearchCriteriaGenerator = studentsSearchCriteriaGenerator;
    }

    public void put(Student student) {
        session().saveOrUpdate(student);
    }

    public Student update(Student student) {
        session().save(student);
        session().flush();
        return student;
    }

    public List<Student> findAll() {
        return query("from Student").list();
    }

    public Student findByStudentId(String studentId) {
        return (Student) query("from Student where studentId = ?").setParameter(0, StringUtils.upperCase(studentId)).uniqueResult();
    }

    public Set<Student> findByStudentIds(String... studentIds) {
        return newHashSet(query("from Student where studentId in (:ids)").setParameterList("ids", studentIds).list());
    }

      private Query query(String hibernateQueryLanguage) {
          return session().createQuery(hibernateQueryLanguage);
      }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    public List<Student> findBySearchParameter(StudentSearchParameter searchParam, int firstIndex, int maxResults) {
        Criteria getPageCriteria = studentsSearchCriteriaGenerator.createOrderedCriteriaFrom(searchParam);
        getPageCriteria.setFirstResult(firstIndex);
        getPageCriteria.setMaxResults(maxResults);
        return getPageCriteria.list();
    }

    int getCountBasedOn(StudentSearchParameter searchParam) {
        Criteria countCriteria = studentsSearchCriteriaGenerator.createCountCriteriaBasedOn(searchParam);
        return Integer.parseInt(countCriteria.uniqueResult().toString());
    }
}
