package org.sukrupa.event;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sukrupa.student.Student;

import java.util.List;
import java.util.Set;

@Repository
public class EventRepository {

    private static final String STUDENT_ID = "studentId";
    static final String ATTENDEES_SEPARATOR = ",";

    private SessionFactory sessionFactory;
    private Set<Student> studentListFromDB;

    @Autowired
    public EventRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Event event) {
        session().save(event);
    }

    public List<Event> getAll() {
        return session().createCriteria(Event.class).list();
    }

    public Event getEvent(int eventId) {
        return (Event) session().createCriteria(Event.class).add(Restrictions.eq("id", eventId)).uniqueResult();
    }

    @Deprecated
    public boolean save(EventRecord eventRecord) {
        if (attendiesAreValid(eventRecord)) {
            session().save(Event.from(eventRecord));
            return true;
        } else
            eventRecord.setError(findNonExisting(eventRecord.getAttendeesForDisplay()));
        return false;
    }

    @Deprecated
    private boolean attendiesAreValid(EventRecord eventRecord) {
        return findNonExisting(eventRecord.getAttendeesForDisplay()).size() == 0;
    }

    @Deprecated
    public Set<String> findNonExisting(String studentIds) {
        Set<String> studentIdsFromForm = parseIdsFromForm(studentIds);
        studentListFromDB = retrieveStudent(studentIdsFromForm);

        for (Student each : studentListFromDB) {
            studentIdsFromForm.remove(each.getStudentId());
        }

        return (studentIdsFromForm);
    }

    @Deprecated
    private Set<Student> retrieveStudent(Set<String> studentIdsFromForm) {
        Criteria criteria = session().createCriteria(Student.class);
        criteria.add(Restrictions.disjunction().add(Restrictions.in(STUDENT_ID, studentIdsFromForm)));
        return Sets.newHashSet(criteria.list());
    }

    @Deprecated
    private Set<String> parseIdsFromForm(String studentIds) {
        return Sets.newLinkedHashSet(Splitter.on(ATTENDEES_SEPARATOR).omitEmptyStrings().trimResults().split(studentIds));
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
