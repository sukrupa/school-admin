package org.sukrupa.platform.web;

import org.apache.log4j.*;
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

import static java.lang.String.format;

@Component
public class FrontController extends DispatcherServlet  {

    private static final Logger log = Logger.getLogger(FrontController.class);

    private ConfigurableWebApplicationContext context;

    @Autowired
    public FrontController(ConfigurableWebApplicationContext context) {
        this.context = context;
    }

    protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException {
        log.info(format("Creating Web Application Context with root of [%s]", getServletContext().getRealPath("/")));
        context.setServletContext(getServletContext());
        context.setServletConfig(getServletConfig());
        context.setNamespace(getNamespace());
        return context;
    }
}
