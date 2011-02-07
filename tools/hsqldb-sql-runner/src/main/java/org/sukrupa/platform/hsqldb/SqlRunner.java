package org.sukrupa.platform.hsqldb;

import org.sukrupa.platform.hsqldb.io.*;

import static java.lang.String.format;
import static java.lang.System.exit;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.parseArgs;

public class SqlRunner {

    private final SqlRunnerArgs sqlRunnerArgs;
    private final Console console;

    private final HsqlDatabase hsqlDatabase;

    public static void main(String[] args) {
        int status = new SqlRunner(parseArgs(args), new HsqlDatabase(), new Console()).run();
        exit(status);
    }

    public SqlRunner(SqlRunnerArgs sqlRunnerArgs, HsqlDatabase hsqlDatabase, Console console) {
        this.hsqlDatabase = hsqlDatabase;
        this.console = console;
        this.sqlRunnerArgs = sqlRunnerArgs;
    }

    public int run() {
        if (sqlRunnerArgs.isInvalid()) {
            console.println("Sorry, I didn't understand the arguments you passed me.");
            console.println(format("Usage: hsqldb-exec.sh %s", sqlRunnerArgs.describeArguments()));
            return -1;
        }

        hsqlDatabase.connectUsingPropertiesFrom(sqlRunnerArgs.getDatabasePropertiesFilename());
        hsqlDatabase.execute(sqlRunnerArgs.getSql(), console);
        return 0;
    }

}