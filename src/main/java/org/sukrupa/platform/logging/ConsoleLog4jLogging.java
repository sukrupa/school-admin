package org.sukrupa.platform.logging;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import static org.apache.log4j.Logger.getRootLogger;

public class ConsoleLog4jLogging {

    private static final Level DEFAULT_LOG_LEVEL = Level.INFO;

    private static final String LOG_PATTERN = "[%t] %-5p %c %x - %m%n";

    public static void configureLogging() {
        configure(getRootLogger(), DEFAULT_LOG_LEVEL);
    }

    private static void configure(Logger logger, Level level) {
        logger.setLevel(level);
        logger.addAppender(new ConsoleAppender(new PatternLayout(LOG_PATTERN)));
    }
}