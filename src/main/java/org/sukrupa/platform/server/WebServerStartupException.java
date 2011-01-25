package org.sukrupa.platform.server;

public class WebServerStartupException extends RuntimeException {
    public WebServerStartupException(Exception e) {
        super("Could not start web server (See Cause)", e);
    }
}