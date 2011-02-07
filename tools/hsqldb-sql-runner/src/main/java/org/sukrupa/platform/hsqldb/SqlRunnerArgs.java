package org.sukrupa.platform.hsqldb;

public class SqlRunnerArgs {
    private final String sqlToExecute;

    public static SqlRunnerArgs parseArgs(String[] args) {
        if (args.length != 1) {
            return new EmptySqlRunnerArgs();
        }
        String sqlToExecute = args[0];
        return new SqlRunnerArgs(sqlToExecute);
    }

    public SqlRunnerArgs(String sqlToExecute) {
        this.sqlToExecute = sqlToExecute;
    }

    public String describeArguments() {
        return "<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">";
    }

    public boolean invalid() {
        return false;
    }

    public String getSqlToExecute() {
        return sqlToExecute;
    }

    private static class EmptySqlRunnerArgs extends SqlRunnerArgs {
        public EmptySqlRunnerArgs() {
            super(null);
        }

        @Override
        public boolean invalid() {
            return true;
        }

    }
}