package org.sukrupa.student;

import org.joda.time.DateMidnight;
import org.joda.time.DateTimeUtils;
import org.joda.time.LocalDate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class StudentTest {

    @BeforeClass
    public static void classSetUp() {
        DateTimeUtils.setCurrentMillisFixed(new DateMidnight(2010, 3, 02).getMillis());
    }

    @AfterClass
    public static void classTearDown() {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void shouldBeEqual() {
        assertThat(student("pat", new LocalDate(2005, 3, 01), new Talent("music"), new Talent("sport")),
                is(student("pat", new LocalDate(2005, 3, 01), new Talent("sport"), new Talent("music"))));
    }

    @Test
    public void shouldHaveSameHashCode() {
        assertThat(student("pat", null).hashCode(), is(student("pat", null).hashCode()));
    }

    @Test
    public void shouldNotBeEqualIfDifferentName() {
        assertThat(student("pat", null).equals(student("mr. jones", null)), is(false));
    }

    @Test
    public void shouldBe5YearsOld() {
        assertThat(student("pat", new LocalDate(2005, 01, 22)).getAge(), is(5));
    }

    @Test
    public void shouldBeOfSameAge() {
        assertThat(student("pat", new LocalDate(2005, 4, 12)).getAge(), is(student("pat", new LocalDate(2005, 6, 10)).getAge()));
    }

    @Test
    public void shouldBe5YearOldCurrentDateMonthBeforeDOBMonth() {
        assertThat(student("pat", new LocalDate(2005, 4, 22)).getAge(), is(4));
    }

    @Test
    public void shouldBe5YearOldCurrentDateDayBeforeDOBDay() {
        assertThat(student("pat", new LocalDate(2005, 3, 3)).getAge(), is(4));
    }

    @Test
    public void shouldNBe5YearOldCurrentDateMonthAfterDOBMonth() {
        assertThat(student("pat", new LocalDate(2005, 2, 01)).getAge(), is(5));
    }

    @Test
    public void shouldDisplayTalentsAsCommaSeperated() {
        Student ron = new StudentBuilder().talents("running", "flying").build();
        assertThat(ron.getTalentsForDisplay(), is("running, flying"));
    }

    @Test
    public void shouldCaptureNotesAboutAStudent() {
        Note firstNote = new Note("note1", new LocalDate());
        Note secondNote = new Note("note2", new LocalDate());
        Student suhas = new StudentBuilder().notes(firstNote, secondNote).build();
        
        assertThat(suhas.getNotes(), hasItems(firstNote, secondNote));
    }

    private Student student(String name, LocalDate dateOfBirth) {
        return new StudentBuilder().name(name).dateOfBirth(dateOfBirth).build();
    }

    private Student student(String name, LocalDate dateOfBirth, Talent... talents) {
        return new StudentBuilder().name(name).dateOfBirth(dateOfBirth).talents(new HashSet(Arrays.asList(talents))).build();
    }

}
