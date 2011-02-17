package org.sukrupa.app.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class SpringConfiguration {

	public static ApplicationContext configureApp(String basePackage) {
        ConfigurableWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(basePackage);
        context.refresh();
        return context;
    }
}
