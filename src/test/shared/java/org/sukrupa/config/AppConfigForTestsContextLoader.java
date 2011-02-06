package org.sukrupa.config;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;

import static org.sukrupa.app.config.ApplicationContextLoader.configureApp;
import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class AppConfigForTestsContextLoader implements ContextLoader {

    public ApplicationContext loadContext(String... locations) throws Exception {
        configureLogging();
        return configureApp();
    }

    public String[] processLocations(Class<?> clazz, String... locations) {
        return new String[]{getClass().getName()};
    }
}
