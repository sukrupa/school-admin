package org.sukrupa.student;

import org.joda.time.LocalDate;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class NoteTest {

    @Test
    public void testEqualityOfTwoNotes() {
       assertThat(new Note(1,"He is an Intelligent Person",new LocalDate(2005, 6, 10)),is(new Note(1,"He is an Intelligent Person",new LocalDate(2005, 6, 10))));
    }

    @Test
    public void testInequalityOfTwoNotes() {
       assertThat(new Note(1,"He is an Intelligent Person",new LocalDate(2005, 6, 10)),not(new Note(2, "He is an Intelligent Person", new LocalDate(2005, 6, 10))));
    }
}