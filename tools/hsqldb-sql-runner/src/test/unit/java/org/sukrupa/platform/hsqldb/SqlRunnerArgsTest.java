package org.sukrupa.platform.hsqldb;

import org.junit.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SqlRunnerArgsTest {


    @Test
    public void describes_the_required_arguments() throws FileNotFoundException {
        String description = new SqlRunnerArgs(null,new StringReader("")).describeArguments();
        assertThat(description, is("<database properties file> <file containing sql statement>"));
    }


}