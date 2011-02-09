package org.sukrupa.platform.hsqldb;

public class SqlRunnerArgs {
    private String databasePropertiesFilename;
    private final String sqlToExecute;

    public static SqlRunnerArgs parseArgs(String[] args) {
        if (args.length != 1) {
            return new EmptySqlRunnerArgs();
        }
        String sqlToExecute = args[0];
        return new SqlRunnerArgs(null, sqlToExecute);
    }

    public SqlRunnerArgs(String databasePropertiesFilename, String sqlToExecute) {
        this.databasePropertiesFilename = databasePropertiesFilename;
        this.sqlToExecute = sqlToExecute;
    }

    public String describeArguments() {
        return "<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">";
    }

    public boolean isInvalid() {
        return false;
    }

    public String getSql() {
        return sqlToExecute;
    }

    public String getDatabasePropertiesFilename() {
        return databasePropertiesFilename;
    }

    private static class EmptySqlRunnerArgs extends SqlRunnerArgs {
        public EmptySqlRunnerArgs() {
            super(null, null);
        }

        @Override public boolean isInvalid() {
            return true;
        }

        @Override public String getSql() {
            throw new UnsupportedOperationException("There are no arguments here, boyo.");
        }
    }
}