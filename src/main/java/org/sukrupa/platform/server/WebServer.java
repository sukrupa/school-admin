package org.sukrupa.platform.server;

import org.apache.log4j.Logger;
import org.eclipse.jetty.http.security.Constraint;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.web.FrontController;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

@Component
public class WebServer {

    private Logger LOG = Logger.getLogger(WebServer.class);

    private Server server;
    private final String webRoot;
    private final String contextPath;
    private final FrontController frontController;
    private boolean authenticate;

    @Autowired
    public WebServer(@Value("${web.root.dir}") String webRoot,
                     @Value("${web.http.port}") int httpPort,
                     @Value("${web.context.path}") String contextPath,
                     @Value("${web.server.realm.file}") String webServerRealmFile,
                     @Value("${web.server.authenticate}") boolean authenticate,
                     FrontController frontController) throws IOException {


        this.webRoot = webRoot;
        this.contextPath = contextPath;
        this.frontController = frontController;
        this.authenticate = authenticate;


        server = new Server(httpPort);
        HashLoginService hashLoginService = new HashLoginService("SukrupaSchoolAdmin", webServerRealmFile);
        server.addBean(hashLoginService);
        server.setHandler(handlers());
    }

    public void start() {
        try {
            LOG.info(format("Starting Web Server (web root:%s)...", webRoot));
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
        resourceHandler.setResourceBase(webRoot);
        return resourceHandler;
    }

    private ServletContextHandler servletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        addAuthentication(servletHandler);

        servletHandler.setContextPath(contextPath);
        servletHandler.setResourceBase(webRoot);
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setServer(server);
        servletHandler.setErrorHandler(errorHandler);
        servletHandler.addServlet(new ServletHolder(frontController), "/*");
        return servletHandler;
    }

    private void addAuthentication(ServletContextHandler servletHandler) {
        if(!authenticate) return;

        ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
        securityHandler.setLoginService(server.getBean(HashLoginService.class));

        Constraint constraint = new Constraint();
        constraint.setName(Constraint.__BASIC_AUTH);
        constraint.setRoles(new String[]{"SukrupaSchoolAdmin"});
        constraint.setAuthenticate(true);

        ConstraintMapping cm = new ConstraintMapping();
        cm.setPathSpec("/*");
        cm.setConstraint(constraint);

        securityHandler.addConstraintMapping(cm);

        servletHandler.setSecurityHandler(securityHandler);
    }
}