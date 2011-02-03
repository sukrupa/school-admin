package org.sukrupa.event;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.student.Student;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public EventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Event> getAll() {
        return sessionFactory.getCurrentSession().createCriteria(Event.class).list();
    }

    public void save(Event event) {
        sessionFactory.getCurrentSession().save(event);
    }

    public List<Student> retrieveStudent(String... studentIDs) {
        List <String> studentIDList = new ArrayList<String>();
        for(String studentID : studentIDs)
            studentIDList.add(studentID);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Student.class);
        Criterion attendee = Restrictions.in("studentId",studentIDList);
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(attendee);
        criteria.add(disjunction);
        return criteria.list();
    }
}
