package org.sukrupa.platform.web;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.sukrupa.platform.server.WebServer;

@Component
public class FrontController extends DispatcherServlet  {

    private ConfigurableWebApplicationContext context;

    @Autowired
    public FrontController(ConfigurableWebApplicationContext context) {
        this.context = context;
    }

    protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException {
        context.setServletContext(getServletContext());
        context.setServletConfig(getServletConfig());
        context.setNamespace(getNamespace());
        return context;
    }
}
