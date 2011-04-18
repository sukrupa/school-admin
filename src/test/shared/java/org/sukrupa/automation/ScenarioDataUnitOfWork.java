package org.sukrupa.automation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentRepository;
import org.sukrupa.student.StudentsSearchCriteriaGenerator;

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
				.addPackage("org.sukrupa.event")
				.addPackage("org.sukrupa.student")
				.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver")
				.setProperty("hibernate.connection.url", "jdbc:hsqldb:hsql://localhost/sukrupa")
				.setProperty("hibernate.connection.username", "sa")
				.setProperty("hibernate.connection.password", "")
				.setProperty("hibernate.current_session_context_class", "thread") // required so you can do getCurrentSession()
				.configure()
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
		session.beginTransaction();

		Collection<Student> createdStudents = studentRepository
				.findByStudentIds(createdStudentIds.toArray(new String[] {}));

		for (Student student : createdStudents) {
			session.delete(student);
		}

		session.flush();
		session.close();

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
