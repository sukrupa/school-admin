package org.sukrupa.platform.web;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static java.lang.String.format;

@Component
public class FrontController extends DispatcherServlet  {

    private static final Logger log = Logger.getLogger(FrontController.class);
    private static final long serialVersionUID = 1;


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
