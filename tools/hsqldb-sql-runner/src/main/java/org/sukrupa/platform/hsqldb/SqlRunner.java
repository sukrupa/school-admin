package org.sukrupa.platform.hsqldb;

import org.apache.log4j.*;

public class SqlRunner {

    private static final Logger log = Logger.getLogger(SqlRunner.class);

    public static void main(String[] args) {
        log.info("Welcome to the hsqldb.SqlRunner!");
        log.info("You want me to execut the following SQL: ");
        log.info(args[0]);
    }
}