package org.sukrupa.platform.hsqldb;

import org.junit.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SqlRunnerArgsTest {

    private static final String SELECT_FROM_TABLE_A = "select * from a_table;";
    private static final String[] SIMPLE_ARGS = new String[]{SELECT_FROM_TABLE_A};

    @Test
    public void describes_the_required_arguments() {
        String description = SqlRunnerArgs.parseArgs(SIMPLE_ARGS).describeArguments();

        assertThat(description, is("<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">"));
    }

    @Test
    public void extracts_the_sql_query_to_execute() {
        SqlRunnerArgs sqlRunnerArgs = SqlRunnerArgs.parseArgs(SIMPLE_ARGS);

        String sqlToExecute = sqlRunnerArgs.getSqlToExecute();

        assertThat(sqlToExecute, is(SELECT_FROM_TABLE_A));
    }

}