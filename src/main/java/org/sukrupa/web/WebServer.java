package org.sukrupa.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import static org.sukrupa.config.Logging.configureLogging;

public class WebServer {

    private static final int HTTP_PORT = 8080;

    private static final String WEB_APP_CONTEXT = "/sukrupa";
    
    private static final String WAR_DIRECTORY = "./web";

    public static void main(String[] args) throws Exception {
        configureLogging();
        new WebServer(args.length > 0 ? args[0] : WAR_DIRECTORY);
    }

    public WebServer(String war) throws Exception {
        Server server = new Server(HTTP_PORT);

        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(WEB_APP_CONTEXT);
        webApp.setWar(war);
        server.setHandler(webApp);

        server.start();
        server.join();
    }
}
