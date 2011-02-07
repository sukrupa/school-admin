package org.sukrupa.platform.hsqldb;

public class SqlRunnerArgs {
    public static SqlRunnerArgs parseArgs(String[] args) {
        return null;
    }

    public String describeArguments() {
        return "<database properties file> <sql to execute, e.g. \"SELECT * FROM TABLE_1\">";
    }

    public boolean invalid() {
        return true;
    }
}