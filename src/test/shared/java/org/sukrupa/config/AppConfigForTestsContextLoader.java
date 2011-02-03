package org.sukrupa.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextLoader;

import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class AppConfigForTestsContextLoader implements ContextLoader {

    private static final String BASE_PACKAGE = "org.sukrupa";

    public ApplicationContext loadContext(String... locations) throws Exception {
        configureLogging();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan(BASE_PACKAGE);
        context.refresh();
        return context;
    }

    public String[] processLocations(Class<?> clazz, String... locations) {
        return new String[]{getClass().getName()};
    }
}
