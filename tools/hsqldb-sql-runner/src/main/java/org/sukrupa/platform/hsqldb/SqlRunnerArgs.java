package org.sukrupa.platform.hsqldb;

import java.io.*;

public class SqlRunnerArgs {
    private String databasePropertiesFilename;
    private final Reader sqlReader;

    public static SqlRunnerArgs parseArgs(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            return new EmptySqlRunnerArgs();
        }
        String filename = args[0];
        Reader reader = new BufferedReader(new FileReader(filename));
        return new SqlRunnerArgs(null, reader);
    }

    public SqlRunnerArgs(String databasePropertiesFilename, Reader sqlReader) {
        this.databasePropertiesFilename = databasePropertiesFilename;
        this.sqlReader = sqlReader;
    }

    public String describeArguments() {
        return "<database properties file> <file containing sql statement>";
    }

    public boolean isInvalid() {
        return false;
    }

    public Reader getSqlReader() {
        return sqlReader;
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

        @Override public Reader getSqlReader() {
            throw new UnsupportedOperationException("There are no arguments here, boyo.");
        }
    }
}