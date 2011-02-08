package org.sukrupa.platform.hsqldb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.naming.spi.DirectoryManager;
import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.parseArgs;

public class SqlRunnerArgsIntegrationTest {

    private final String FILE_NAME = "Test.sql";
    private final String FILE_CONTENT = "Hello";
    private final String[] SIMPLE_ARGS = new String[]{FILE_NAME};
    private final String[] EMPTY_ARGS = new String[]{};

    @Before
    public void setUp() throws IOException {
        FileWriter file = new FileWriter(FILE_NAME);
        file.write(FILE_CONTENT);
        file.close();
    }

    @After
    public void tearDown() {
        new File(FILE_NAME).delete();
    }

    @Test
    public void should_create_reader_for_sql_file() throws IOException {
        SqlRunnerArgs sqlRunnerArgs = SqlRunnerArgs.parseArgs(new String[]{FILE_NAME});
        Reader reader = sqlRunnerArgs.getSqlReader();
        String firstLine = new BufferedReader(reader).readLine();
        assertThat(firstLine, is(FILE_CONTENT));
    }


    @Test
    public void empty_args_is_invalid() throws FileNotFoundException {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(EMPTY_ARGS);
        assertThat("Args should be invalid", sqlRunnerArgs.isInvalid(), is(true));
    }

    @Test
    public void non_empty_args_are_not_invalid() throws FileNotFoundException {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(SIMPLE_ARGS);
        assertThat("Args should not be invalid", sqlRunnerArgs.isInvalid(), is(not(true)));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void fails_if_try_to_access_sql_with_empty_args() throws FileNotFoundException {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(EMPTY_ARGS);
        sqlRunnerArgs.getSqlReader();
    }


    private static final boolean not(boolean input) {
        return !input;
    }


}
