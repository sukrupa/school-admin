package org.sukrupa.platform.server;

public class WebServerStartupException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WebServerStartupException(Exception e) {
        super("Could not start web server (See Cause)", e);
    }
}