package org.sukrupa.automation;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.sukrupa.event.*;
import org.sukrupa.student.*;

import java.util.*;

public class ScenarioDataUnitOfWork {

	private static final SessionFactory HIBERNATE_SESSION_FACTORY = initialiseSessionFactory();

	private final StudentRepository studentRepository;
	private final List<String> createdStudentIds = new ArrayList<String>();
	private final SessionFactory hibernateSessionFactory;

	public ScenarioDataUnitOfWork() {
		this(createStudentRepository(), hibernateSessionFactory());
	}

	private static SessionFactory initialiseSessionFactory() {	
		return new Configuration()
				.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
				.setProperty("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/sukrupa")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.current_session_context_class", "thread") // required so you can do getCurrentSession()
				.addAnnotatedClass(Student.class)
                .addAnnotatedClass(Caregiver.class)
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(Talent.class)
                .addAnnotatedClass(Note.class)
                .addAnnotatedClass(Event.class)
				.buildSessionFactory();

	}

	public ScenarioDataUnitOfWork(StudentRepository studentRepository, SessionFactory hibernateSessionFactory) {
		this.studentRepository = studentRepository;
		this.hibernateSessionFactory = hibernateSessionFactory;
	}

	public void addedStudentWithId(String studentId) {
		createdStudentIds.add(studentId);
	}

	public void removeAllCreatedObjects() {
		Session session = hibernateSessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			Collection<Student> createdStudents = studentRepository.findByStudentIds(createdStudentIds.toArray(new String[] {}));
	
			for (Student student : createdStudents) {
				session.delete(student);
			}
	
			session.flush();
			tx.commit();				
		} catch(Exception e) {
			tx.rollback();
		}

		createdStudentIds.clear();
	}

	private static SessionFactory hibernateSessionFactory() {
		return HIBERNATE_SESSION_FACTORY;
	}

	private static StudentRepository createStudentRepository() {
		return new StudentRepository(hibernateSessionFactory(),
				new StudentsSearchCriteriaGenerator(hibernateSessionFactory()));
	}

}
