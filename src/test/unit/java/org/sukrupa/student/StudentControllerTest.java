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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class StudentControllerTest {

    @Mock
    private StudentRepository repository;

    private StudentController controller;

    private Map<String, List<Student>> studentsListModel = new HashMap<String, List<Student>>();
    private HashMap<String,Student> studentModel = new HashMap<String,Student>();
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
        controller.all(studentsListModel);
        assertThat(studentsListModel.get("students"), is(asList(sahil, pat)));
    }

    @Test
    public void shouldPickStudentsViewForDisplayingAllStudents() {
        assertThat(controller.all(studentsListModel), is("students"));
    }

    @Test
    public void shouldPopulateModelWithAStudent() {
        when(repository.find("123")).thenReturn(pat);
        controller.find("123", studentModel);
        assertThat(studentModel.get("student"),is(pat));
    }

    @Test
    public void shouldPickStudentViewForDisplayingSingleStudent()
    {
        assertThat(controller.find("123",studentModel),is("student"));
    }

	@Test
	public void shouldListStudentsFromClassNursery() {
		when(repository.parametricSearch("Nursery", "", "", "", "", "", "")).thenReturn(asList(sahil, renaud));
        controller.parametricSearchResult("Nursery", "", "", "", "", "", "", studentsListModel);
		assertThat(studentsListModel.get("students"), is(asList(sahil, renaud)));
	}

}
