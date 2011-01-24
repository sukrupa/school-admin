package org.sukrupa.student;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.sukrupa.config.AppConfigContextLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AppConfigContextLoader.class)
@Transactional
@Ignore("pat, sahil - 24/01/11 @ 17:18 - Work in Progress")
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
        save(sahil);
        save(pat);

        assertThat(repository.findAll(), hasItems(sahil, pat));
    }

    private void save(Student sahil) {
        sessionFactory.getCurrentSession().save(sahil);
    }

}
