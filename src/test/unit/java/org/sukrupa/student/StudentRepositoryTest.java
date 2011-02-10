package org.sukrupa.student;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isNull;
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
        when(criteria.setProjection(Matchers.<Projection>anyObject())).thenReturn(criteria);
    }

    @Test
    public void shouldRetrievePageOneOfOne() {
        when(criteria.uniqueResult()).thenReturn(new Integer(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE));
        StudentListPage page = repository.parametricSearch(new StudentSearchParameterBuilder().page(1).build());
        Mockito.verify(criteria).setFirstResult(0);
        Mockito.verify(criteria).setMaxResults(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        assertThat(page.isNextEnabled(), is(false));
    }

    @Test
    public void shouldRetrievePageOneOfMultiple() {
        when(criteria.uniqueResult()).thenReturn(new Integer(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE+1));
        StudentListPage page = repository.parametricSearch(new StudentSearchParameterBuilder().page(1).build());
        Mockito.verify(criteria).setFirstResult(0);
        Mockito.verify(criteria).setMaxResults(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        assertThat(page.isNextEnabled(), is(true));
    }

    @Test
    public void shouldRetrieveSecondPageOfStudentsFromDatabase() {
        when(criteria.uniqueResult()).thenReturn(new Integer(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE));
        repository.parametricSearch(new StudentSearchParameterBuilder().page(2).build());
        Mockito.verify(criteria).setFirstResult(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        Mockito.verify(criteria).setMaxResults(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

}
