package org.sukrupa.platform.server;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.DefaultHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class RedirectRootHandler extends DefaultHandler {
    private String redirectTo;

    public RedirectRootHandler(String redirectTo) {
        this.redirectTo = redirectTo;
    }

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (response.isCommitted() || baseRequest.isHandled())
            return;

        if (request.getRequestURI().equals("/")) {
            response.sendRedirect(redirectTo);
            baseRequest.setHandled(true);
        }
    }
}
