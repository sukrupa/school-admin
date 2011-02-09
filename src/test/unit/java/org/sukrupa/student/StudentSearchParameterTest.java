package org.sukrupa.student;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class StudentSearchParameterTest {

    @Test
    public void shouldDetermineAllFieldsAreNull() throws Exception {
        StudentSearchParameter param = new StudentSearchParameter();
        assertThat(param.isAllBlank(), is(true));
    }

    @Test
    public void shouldDetermineAllFieldsAreNotNull() throws Exception {
        StudentSearchParameter param = new StudentSearchParameter();
        param.setCaste("abc");
        assertThat(param.isAllBlank(), is(false));
    }
}
