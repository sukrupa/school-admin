package org.sukrupa.platform.hsqldb;

import org.junit.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.*;

public class SqlRunnerArgsTest {

    private static final String[] EMPTY_ARGS = new String[]{};

    private static final String SELECT_FROM_TABLE_A = "select * from a_table;";
    private static final String[] SIMPLE_ARGS = new String[]{SELECT_FROM_TABLE_A};

    @Test
    public void describes_the_required_arguments() {
        String description = parseArgs(SIMPLE_ARGS).describeArguments();

        assertThat(description, is("<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">"));
    }

    @Test
    public void extracts_the_sql_query_to_execute() {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(SIMPLE_ARGS);

        String sqlToExecute = sqlRunnerArgs.getSqlToExecute();

        assertThat(sqlToExecute, is(SELECT_FROM_TABLE_A));
    }

    @Test
    public void empty_args_is_invalid() {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(EMPTY_ARGS);

        assertThat("Args should be invalid", sqlRunnerArgs.invalid(), is(true));
    }

    @Test
    public void non_empty_args_are_not_invalid() {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(SIMPLE_ARGS);

        assertThat("Args should not be invalid", sqlRunnerArgs.invalid(), is(not(true)));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void fails_if_try_to_access_sql_with_empty_args() {
        SqlRunnerArgs sqlRunnerArgs = parseArgs(EMPTY_ARGS);

        sqlRunnerArgs.getSqlToExecute();
    }


    private static final boolean not(boolean input) {
        return !input;
    }

}