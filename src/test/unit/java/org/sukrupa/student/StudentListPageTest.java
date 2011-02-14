package org.sukrupa.student;

import com.google.common.collect.Lists;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class StudentListPageTest {

    private final StudentListPage pageOne = new StudentListPage(Lists.<Student>newArrayList(), 1, 2);
    private final StudentListPage pageTwo = new StudentListPage(Lists.<Student>newArrayList(), 2, 2);

    @Test
    public void shouldHaveNextPage() {
        assertThat(pageOne.isNextEnabled(), is(true));
    }

    @Test
    public void shouldNotHaveNextPage() {
        assertThat(pageTwo.isNextEnabled(), is(false));
    }

    @Test
    public void shouldHavePreviousPage() {
        assertThat(pageTwo.isPreviousEnabled(), is(true));
    }

    @Test
    public void shouldNotHavePreviousPage() {
        assertThat(pageOne.isPreviousEnabled(), is(false));
    }

    @Test
    public void shouldKnowPageAfterOneIsTwo() {
        assertThat(pageOne.getNextPage(), is(2));
    }

    @Test
    public void shouldKnowPageBeforeTwoIsOne() {
        assertThat(pageTwo.getPreviousPage(), is(1));
    }
}
