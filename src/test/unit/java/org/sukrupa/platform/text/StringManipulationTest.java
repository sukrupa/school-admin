package org.sukrupa.platform.text;

import org.junit.Test;

import static java.io.File.separator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.text.StringManipulation.join;

public class StringManipulationTest {

    @Test
    public void shouldJoinStringsWithPathSeparator() {
        assertThat(join("fish", "like", "plankton"), is("fish" + separator + "like" + separator + "plankton"));
    }
}
