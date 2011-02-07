package org.sukrupa.platform.hsqldb;

import org.apache.log4j.*;

import static java.lang.String.format;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.parseArgs;

public class SqlRunner {

    private static final Logger log = Logger.getLogger(SqlRunner.class);

    private final SqlRunnerArgs sqlRunnerArgs;
    private final HsqlDatabase hsqlDatabase;
    private final ConsoleOutput console;

    public static void main(String[] args) {
        new SqlRunner(parseArgs(args), new HsqlDatabase(), new ConsoleOutput()).run();
    }

    public SqlRunner(SqlRunnerArgs sqlRunnerArgs, HsqlDatabase hsqlDatabase, ConsoleOutput console) {
        this.hsqlDatabase = hsqlDatabase;
        this.console = console;
        this.sqlRunnerArgs = sqlRunnerArgs;
    }

    public void run() {
        if (sqlRunnerArgs.invalid()) {
            console.println("Sorry, I didn't understand the arguments you passed me.");
            console.println(format("Usage: hsqldb-exec.sh %s", sqlRunnerArgs.describeArguments()));
        }

        hsqlDatabase.connectUsingPropertiesFrom(sqlRunnerArgs.getDatabasePropertiesFilename());
    }

}