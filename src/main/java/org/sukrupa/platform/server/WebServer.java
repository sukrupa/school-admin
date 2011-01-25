package org.sukrupa.platform.server;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

public class WebServer {

    private Server server;

    public WebServer(String warFilename, int httpPort, String contextPath)  {
        server = new Server(httpPort);

        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(contextPath);
        webApp.setWar(warFilename);
        server.setHandler(webApp);

    }

    public void start()  {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new WebServerStartupException(e);
        }
    }
}