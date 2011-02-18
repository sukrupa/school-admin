package org.sukrupa.platform.config;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextLoader;
import org.sukrupa.app.SchoolAdminApp;

import static org.sukrupa.platform.config.SpringConfiguration.configureApp;
import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class SpringContextLoaderForTesting implements ContextLoader {

    public ApplicationContext loadContext(String... locations) throws Exception {
        configureLogging();
        return configureApp(SchoolAdminApp.BASE_PACKAGE);
    }

    public String[] processLocations(Class<?> clazz, String... locations) {
        return new String[]{getClass().getName()};
    }
}
