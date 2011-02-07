package org.sukrupa.platform.hsqldb;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SqlRunnerArgsTest {

    @Test
    public void describes_the_required_arguments() {
        String description = new SqlRunnerArgs().describeArguments();

        assertThat(description, is("<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">"));
    }

}