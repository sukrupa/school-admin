package org.sukrupa.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sukrupa.platform.server.WebServer;

import java.io.IOException;

import static org.sukrupa.platform.config.SpringConfiguration.configureApp;
import static org.sukrupa.platform.logging.ConsoleLog4jLogging.configureLogging;

@Component
public class SchoolAdminApp {

	public static final String BASE_PACKAGE = "org.sukrupa";

	public static void main(String[] args) throws IOException {
        configureLogging();
        configureApp(BASE_PACKAGE).getBean(SchoolAdminApp.class).start();
    }

    private final WebServer webServer;

    @Autowired
    public SchoolAdminApp(WebServer webServer) {
        this.webServer = webServer;
    }

    public void start() {
        webServer.start();
    }
}
