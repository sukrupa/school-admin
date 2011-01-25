package org.sukrupa.app;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class SchoolAdminApp {

    private static final int HTTP_PORT = 8080;

    private static final String WEB_APP_CONTEXT = "/sukrupa";

    private static final String WAR_DIRECTORY = "./web";

    public static void main(String[] args) throws Exception {
        configureLogging();
        new SchoolAdminApp(args.length > 0 ? args[0] : WAR_DIRECTORY);
    }

    public SchoolAdminApp(String war) throws Exception {
        Server server = new Server(HTTP_PORT);

        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(WEB_APP_CONTEXT);
        webApp.setWar(war);
        server.setHandler(webApp);

        server.start();
        server.join();
    }
}
