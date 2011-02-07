package org.sukrupa.platform.hsqldb;

import org.apache.log4j.*;

import static java.lang.String.format;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.parseArgs;

public class SqlRunner {

    private static final Logger log = Logger.getLogger(SqlRunner.class);
    private ConsoleOutput console;
    private SqlRunnerArgs sqlRunnerArgs;

    public static void main(String[] args) {
        new SqlRunner(parseArgs(args), new ConsoleOutput()).run();
    }

    public SqlRunner(SqlRunnerArgs sqlRunnerArgs, ConsoleOutput console) {
        this.console = console;
        this.sqlRunnerArgs = sqlRunnerArgs;
    }

    public void run() {
        if (sqlRunnerArgs.invalid()) {
            console.println("Sorry, I didn't understand the arguments you passed me.");
            console.println(format("Usage: hsqldb-exec.sh ", sqlRunnerArgs.describeArguments()));
        }
    }

}