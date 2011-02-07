package org.sukrupa.platform.hsqldb;

import static java.lang.String.format;
import static org.sukrupa.platform.hsqldb.SqlRunnerArgs.parseArgs;

public class SqlRunner {

    private final SqlRunnerArgs sqlRunnerArgs;
    private final HsqlDatabase hsqlDatabase;
    private final Console console;

    public static void main(String[] args) {
        int status = new SqlRunner(parseArgs(args), new HsqlDatabase(), new Console()).run();
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

        hsqlDatabase.connectUsingPropertiesFrom(sqlRunnerArgs.databasePropertiesFilename());
        hsqlDatabase.execute(sqlRunnerArgs.sql(), console);
        return 0;
    }

}