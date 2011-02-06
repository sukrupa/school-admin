package org.sukrupa.platform.web;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.sukrupa.platform.server.WebServer;

public class FrontController extends DispatcherServlet {
    protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent) throws BeansException {
        ConfigurableWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setParent(parent);
        context.setServletContext(getServletContext());
        context.setServletConfig(getServletConfig());
        context.setNamespace(getNamespace());
        context.setConfigLocation(WebServer.BASE_PACKAGE);
        context.refresh();
        return context;
    }
}
