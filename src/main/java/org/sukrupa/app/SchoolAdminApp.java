package org.sukrupa.app;

import org.sukrupa.platform.server.*;

import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

public class SchoolAdminApp {

    private static final int HTTP_PORT = 8080;
    private static final String WEB_APP_CONTEXT = "/sukrupa";
    private static final String DEFAULT_WAR_DIRECTORY = "./web";

    public static void main(String[] args) throws Exception {
        configureLogging();
        new WebServer(warDirectoryNameFrom(args), HTTP_PORT, WEB_APP_CONTEXT).start();
    }

    private static String warDirectoryNameFrom(String[] args) {
        return args.length > 0 ? args[0] : DEFAULT_WAR_DIRECTORY;
    }


}
