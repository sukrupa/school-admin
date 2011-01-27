package org.sukrupa.platform.server;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.webapp.*;

public class WebServer {

    private Server server;

    public WebServer(String war, int httpPort, String contextPath)  {
        server = new Server(httpPort);

        WebAppContext webApp = new WebAppContext();
        webApp.setContextPath(contextPath);
        webApp.setWar(war);
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