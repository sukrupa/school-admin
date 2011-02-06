package org.sukrupa.app.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ApplicationContextLoader {

    private static final String BASE_PACKAGE = "org.sukrupa";

    public static ApplicationContext configureApp() {
        ConfigurableWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(BASE_PACKAGE);
        context.refresh();
        return context;
    }
}
