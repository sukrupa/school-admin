package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.app.config.AppConfigForTestsContextLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigForTestsContextLoader.class)
@Transactional
@Ignore("pat, juan: 25/01/2010 10:55 - work in progress")
public class StudentRepositoryTest {

    @Autowired
    private SessionFactory sessionFactory;

    private StudentRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new StudentRepository(sessionFactory);
    }

    @Test
    public void shouldRetrieveAllStudentsFromDatabase() {
        Student sahil = new Student("Sahil");
        Student pat = new Student("Pat");

        save(sahil, pat);
        flushHibernateSessionToForceReload();

        assertThat(repository.findAll(), hasItems(sahil, pat));
    }

    private void save(Student... students) {
        for (Object student : students) {
            session().save(student);
        }
    }

    private void flushHibernateSessionToForceReload() {
        session().flush();
        session().clear();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
}
