package org.sukrupa.app;

import org.sukrupa.platform.server.DbServer;
import org.sukrupa.platform.server.WebServer;

import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class SchoolAdminApp {

    private static final String DB_ROOT = userHome() + "/.sukrupa/db";
    private static final String DB_NAME = "sukrupa";

    private static final int HTTP_PORT = 8080;
    private static final String WEB_APP_CONTEXT = "/sukrupa";
    private static final String DEFAULT_WAR_DIRECTORY = "./web";

    private DbServer dbServer;
    private WebServer webServer;

    public SchoolAdminApp(String war) {
        dbServer = new DbServer(DB_ROOT, DB_NAME);
        webServer = new WebServer(war, HTTP_PORT, WEB_APP_CONTEXT);
    }

    public void start() {
        configureLogging();
        dbServer.start();
        webServer.start();
    }

    public void shutdown() {
        dbServer.shutDown();
    }

    public static void main(String[] args) throws Exception {
        SchoolAdminApp schoolAdminApp = new SchoolAdminApp(warDirectoryNameFrom(args));
        try {
            schoolAdminApp.start();
        } finally {
            schoolAdminApp.shutdown();
        }
    }

    private static String warDirectoryNameFrom(String[] args) {
        return args.length > 0 ? args[0] : DEFAULT_WAR_DIRECTORY;
    }

    private static String userHome() {
        return System.getProperty("user.home");
    }
}
