package org.sukrupa.platform.server;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.web.FrontController;

import javax.annotation.PostConstruct;

import static java.lang.String.format;

@Component
public class WebServer {

    private static final String URL_PATTERN = "/app/*";

    private Logger LOG = Logger.getLogger(WebServer.class);

    private Server server;
    private final String webRoot;
    private final String contextPath;
    private final FrontController frontController;

    @Autowired
    public WebServer(@Value("${web.root.dir}") String webRoot,
                     @Value("${web.http.port}") int httpPort,
                     @Value("${web.context.path}") String contextPath,
                     FrontController frontController) {


        this.webRoot = webRoot;
        this.contextPath = contextPath;
        this.frontController = frontController;

        server = new Server(httpPort);
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
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setResourceBase(webRoot);
        return resourceHandler;
    }

    private ServletContextHandler servletHandler() {
        ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletHandler.setContextPath(contextPath);
        servletHandler.setResourceBase(webRoot);
        servletHandler.addServlet(new ServletHolder(frontController), URL_PATTERN);
        return servletHandler;
    }
}