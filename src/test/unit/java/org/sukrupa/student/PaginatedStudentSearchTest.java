package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.student.PaginatedStudentSearch.NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE;

public class PaginatedStudentSearchTest {
    @Mock
    private StudentRepository repository;

    private PaginatedStudentSearch paginatedStudentSearch;

    private final StudentSearchParameter getPageOne = new StudentSearchParameterBuilder().page(1).build();
    private final StudentSearchParameter getPageTwo = new StudentSearchParameterBuilder().page(2).build();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldRetrievePageOneOfOne() {
        when(repository.countResults(Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
        paginatedStudentSearch = new PaginatedStudentSearch(repository, getPageOne);
        assertThat(paginatedStudentSearch.getPage().isNextEnabled(), is(false));
        Mockito.verify(repository).parametricSearch(getPageOne, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageOneOfMultiple() {
        when(repository.countResults(Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        paginatedStudentSearch = new PaginatedStudentSearch(repository, getPageOne);
        assertThat(paginatedStudentSearch.getPage().isNextEnabled(), is(true));
        Mockito.verify(repository).parametricSearch(getPageOne, 0, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

    @Test
    public void shouldRetrievePageTwoOfTwo() {
        when(repository.countResults(Matchers.<StudentSearchParameter>anyObject())).thenReturn(NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE + 1);
        paginatedStudentSearch = new PaginatedStudentSearch(repository, getPageTwo);
        assertThat(paginatedStudentSearch.getPage().isNextEnabled(), is(false));
        Mockito.verify(repository).parametricSearch(getPageTwo, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE, NUMBER_OF_STUDENTS_TO_LIST_PER_PAGE);
    }

}
