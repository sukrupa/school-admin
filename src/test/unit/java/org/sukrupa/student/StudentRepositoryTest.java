package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.student.StudentRepository.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class StudentRepositoryTest {
    @Mock
    private SessionFactory sessionFactory;
    @Mock
    private Session session;
    @Mock
    private Criteria criteria;

    private StudentRepository repository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        repository = new StudentRepository(sessionFactory);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.createCriteria(Student.class)).thenReturn(criteria);
        when(criteria.addOrder(Matchers.<Order>anyObject())).thenReturn(criteria);
    }

    @Test
    public void shouldRetrieveFirstPageOfStudentsFromDatabase() {
        repository.parametricSearch(new StudentSearchParameterBuilder().page(1).build());
        Mockito.verify(criteria).setFirstResult(0);
        Mockito.verify(criteria).setMaxResults(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrieveSecondPageOfStudentsFromDatabase() {
        repository.parametricSearch(new StudentSearchParameterBuilder().page(2).build());
        Mockito.verify(criteria).setFirstResult(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        Mockito.verify(criteria).setMaxResults(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

}
