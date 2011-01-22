package org.sukrupa.config;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Logging {

    private static final Level LOG_LEVEL = Level.INFO;

    private static final String LOG_PATTERN = "[%t] %-5p %c %x - %m%n";

    public static void configureLogging() {
        configureRootLogger();
    }

    private static void configureRootLogger() {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(LOG_LEVEL);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout(LOG_PATTERN)));
    }
}