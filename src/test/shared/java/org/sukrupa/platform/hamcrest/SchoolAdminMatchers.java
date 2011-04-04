package org.sukrupa.platform.hamcrest;

import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.sukrupa.app.students.StudentRow;
import org.sukrupa.event.Event;
import org.sukrupa.student.Note;
import org.sukrupa.student.Student;

import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.apache.commons.collections.CollectionUtils.isEqualCollection;

public class SchoolAdminMatchers {

    public static Matcher<Event> containsAttendees(final Student... attendees) {
        return new TypeSafeMatcher<Event>() {
            private Event event;

            public boolean matchesSafely(Event event) {
                this.event = event;
                return isEqualCollection(event.getAttendees(), asList(attendees));
            }

            public void describeTo(Description description) {
                description.appendValue(event);
            }
        };
    }

    public static Matcher<StudentRow> matches(final Student student) {
        return new TypeSafeMatcher<StudentRow>() {

            private StudentRow studentRow;

            public boolean matchesSafely(StudentRow studentRow) {
                this.studentRow = studentRow;
                return sameName() && sameStudentId() && sameGender() && sameAge() && sameTalents();
            }

            private boolean sameTalents() {
                return student.getTalentsForDisplay().equals(studentRow.getTalents());
            }

            private boolean sameName() {
                return student.getName().equals(studentRow.getName());
            }

            private boolean sameStudentId() {
                return student.getStudentId().equals(studentRow.getStudentId());
            }

            private boolean sameGender() {
                return student.getGender().equals(studentRow.getGender());
            }

            private boolean sameAge() {
                return student.getAge() == studentRow.getAge();
            }

            public void describeTo(Description description) {
                description.appendValue(student);
            }
        };
    }

    public static Matcher<Student> hasNote(final Note note) {
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
