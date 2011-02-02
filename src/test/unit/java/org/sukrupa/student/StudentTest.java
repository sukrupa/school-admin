package org.sukrupa.student;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

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
        assertThat(student("pat", null).equals(student("pat", null)), is(true));
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
		assertThat(student("pat", new DateTime(2005, 01, 22, 0, 0, 0, 0)).getAge(), is(5));
	}

	@Test
	public void shouldBeOfSameAge() {
		assertThat(student("pat", new DateTime(2005, 4, 12, 0, 0, 0, 0)).getAge(), is(student("pat", new DateTime(2005, 6, 10, 0, 0, 0, 0)).getAge()));
	}

	@Test
	public void shouldBe5YearOldCurrentDateMonthBeforeDOBMonth() {
		assertThat(student("pat", new DateTime(2005, 4, 22, 0, 0, 0, 0)).getAge(), is(4));
	}

	@Test
	public void shouldBe5YearOldCurrentDateDayBeforeDOBDay() {
		assertThat(student("pat", new DateTime(2005, 3, 3, 0, 0, 0, 0)).getAge(), is(4));
	}

	@Test
	public void shouldNBe5YearOldCurrentDateMonthAfterDOBMonth() {
		assertThat(student("pat", new DateTime(2005, 2, 01, 0, 0, 0, 0)).getAge(), is(5));
	}

	@Test
	public void shouldNBe5YearOldCurrentDateDayAfterDOBDay() {
		assertThat(student("pat", new DateTime(2005, 3, 01, 0, 0, 0, 0)).getAge(), is(5));
	}

	private Student student(String name) {
        return student(name, null);
    }

	private Student student(String name, DateTime dateOfBirth) {
        return new StudentBuilder().name(name).dateOfBirth(dateOfBirth).build();
    }

}
