package org.sukrupa.student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StudentControllerTest {

    @Mock
    private StudentRepository repository;

    private StudentController controller;

    private Map<String, List<Student>> model = new HashMap<String, List<Student>>();
    private Student sahil = new StudentBuilder().name("pat").studentClass("LKG").build();
    private Student pat = new StudentBuilder().name("sahil").studentClass("Nursery").build();
	private Student renaud = new StudentBuilder().name("renaud").studentClass("Nursery").build();

	@Before
    public void setUp() throws Exception {
        initMocks(this);
        controller = new StudentController(repository);
    }

    @Test
    public void shouldPopulateModelWithAllStudents() {
        when(repository.findAll()).thenReturn(asList(sahil, pat));
        controller.all(model);
        assertThat(model.get("students"), is(asList(sahil, pat)));
    }

    @Test
    public void shouldPickStudentViewForDisplayingAllStudents() {
        assertThat(controller.all(model), is("students"));
    }

	@Test
	public void shouldListStudentsFromClassNursery() {
		when(repository.parametricSearch("Nursery", "", "", "", "", "", "")).thenReturn(asList(sahil, renaud));
        controller.parametricSearchResult("Nursery", "", "", "", "", "", "", model);
		assertThat(model.get("students"), is(asList(sahil, renaud)));
	}

	@Test
	public void shouldReturnEmptyList() {
//		when(repository.parametricSearch("UKG", "", "", "", "", "", "")).thenReturn(anyList());
//        controller.parametricSearchResult("UKG", "", "", "", "", "", "", model);
//		assertThat(model.get("students"), is(Matchers.<Student>empty()));
	}

}
