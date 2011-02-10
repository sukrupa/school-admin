package org.sukrupa.student;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.mockito.Mock;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.sukrupa.platform.date.DateManipulation.freezeTime;
import static org.sukrupa.platform.date.DateManipulation.unfreezeTime;

public class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    private StudentService service;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        service = new StudentService(repository);
        freezeTime();
    }

    @After
    public void tearDown() throws Exception {
        unfreezeTime();
    }

    @Test
    public void shouldAddANoteToStudent() {
        String studentId = "42";
        Note note = new Note("Fish like plankton!");

        when(repository.load(studentId)).thenReturn(new StudentBuilder().build());

        service.addNoteFor(studentId, note.getMessage());

        verify(repository).saveOrUpdate(argThat(hasNote(note)));
    }

    private Matcher<Student> hasNote(final Note note) {
        return new TypeSafeMatcher<Student>() {
            private Student student;

            public boolean matchesSafely(Student student) {
                this.student = student;
                return student.getNotes().contains(note);
            }

            public void describeTo(Description description) {
                description.appendValue(student);
            }
        };
    }

}
