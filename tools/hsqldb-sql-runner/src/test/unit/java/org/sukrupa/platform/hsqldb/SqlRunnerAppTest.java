package org.sukrupa.platform.hsqldb;

import org.junit.*;
import org.mockito.*;
import org.sukrupa.platform.hsqldb.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class SqlRunnerAppTest {

    private ConsoleRecorder consoleRecorder;
    private SystemExitRecorder systemExitRecorder;

    @Mock private HsqlDatabase hsqlDatabase;

    @Before
    public void setUp() {
        initMocks(this);
        consoleRecorder = new ConsoleRecorder();
        systemExitRecorder = new SystemExitRecorder();
        systemExitRecorder.trapSystemExit();
    }

    @After
    public void tearDown() {
        systemExitRecorder.releaseSystemExit();
    }

    @Test(expected = SystemExitRecorder.ExpectedExitStatusException.class)
    public void main_method_exits_with_error_status_for_empty_args() {
        systemExitRecorder.expectExitStatusOf(-1);

        SqlRunnerApp.main(new String[]{});
    }

    @Test
    public void prints_out_help_message_for_invalid_args() {
        SqlRunnerArgs invalidSqlRunnerArgs = new InvalidSqlRunnerArgs("invalid-args");

        SqlRunnerApp runner = new SqlRunnerApp(invalidSqlRunnerArgs, null, consoleRecorder);

        runner.run();

        assertThat(consoleRecorder.output(), containsString("Usage:"));
        assertThat(consoleRecorder.output(), containsString("invalid-args"));
    }

    @Test
    public void executes_some_sql() {
        SqlRunnerArgs sqlRunnerArgs = new SqlRunnerArgsStub("connection.properties", "SELECT SOMETHING HERE!");
        ConsoleRecorder consoleOutputRecorder = new ConsoleRecorder();

        SqlRunnerApp runner = new SqlRunnerApp(sqlRunnerArgs, hsqlDatabase, consoleOutputRecorder);

        runner.run();

        verify(hsqlDatabase).connectUsingPropertiesFrom("connection.properties");
        verify(hsqlDatabase).execute("SELECT SOMETHING HERE!", consoleOutputRecorder);
    }

    private class ConsoleRecorder extends Console {
        private StringBuilder buffer = new StringBuilder();

        @Override public void println(String text) {
            buffer.append(text).append("\n");
        }

        public String output() {
            return buffer.toString();
        }
    }

    private class SqlRunnerArgsStub extends SqlRunnerArgs {
        public SqlRunnerArgsStub(String databasePropertiesName, String sqlToExecute) {
            super(databasePropertiesName, sqlToExecute);
        }
    }

    private class InvalidSqlRunnerArgs extends SqlRunnerArgs {
        private final String description;

        public InvalidSqlRunnerArgs(String description) {
            super(null, null);
            this.description = description;
        }

        @Override public boolean isInvalid() {
            return true;
        }

        @Override public String describeArguments() {
            return description;
        }
    }
}