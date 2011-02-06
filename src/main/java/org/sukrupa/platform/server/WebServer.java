package org.sukrupa.platform.server;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;

public class WebServer {

    private static final String URL_PATTERN = "/app/*";
    private static final String BASE_PACKAGE = "org.sukrupa";

    private Server server;
    private final String webRoot;
    private final String contextPath;

    public WebServer(String webRoot, int httpPort, String contextPath) {
        this.webRoot = webRoot;
        this.contextPath = contextPath;

        server = new Server(httpPort);
        server.setHandler(handlers());
    }

    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new WebServerStartupException(e);
        }
    }

    private HandlerList handlers() {
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resourceHandler(), servletHandler()});
        return handlers;
    }

    private ResourceHandler resourceHandler() {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(webRoot);
        return resourceHandler;
    }

    private ServletContextHandler servletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.setContextPath(contextPath);
        servletHandler.setResourceBase(webRoot);
        servletHandler.addServlet(new ServletHolder(dispatcherServlet()), URL_PATTERN);
        return servletHandler;
    }

    private Servlet dispatcherServlet() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        dispatcherServlet.setContextClass(AnnotationConfigWebApplicationContext.class);
        dispatcherServlet.setContextConfigLocation(BASE_PACKAGE);
        return dispatcherServlet;
    }
}