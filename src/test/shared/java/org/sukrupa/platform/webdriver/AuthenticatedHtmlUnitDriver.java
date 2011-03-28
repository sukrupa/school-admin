package org.sukrupa.platform.webdriver;

import com.gargoylesoftware.htmlunit.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

public class AuthenticatedHtmlUnitDriver extends HtmlUnitDriver {
    private static final Logger LOG = Logger.getLogger(AuthenticatedHtmlUnitDriver.class);
    private static String USERNAME;
    private static String PASSWORD;

    public AuthenticatedHtmlUnitDriver() {
    }

    @Override
    protected WebClient newWebClient(BrowserVersion browserVersion) {
        LOG.debug("Logging in with: [username:" + USERNAME + "]");
        WebClient client = super.newWebClient(browserVersion);
        DefaultCredentialsProvider provider = new DefaultCredentialsProvider();
        provider.addCredentials(USERNAME, PASSWORD);
        client.setCredentialsProvider(provider);
        return client;
    }

    public static WebDriver authenticatedDriver(String username, String password) {
        USERNAME = username;
        PASSWORD = password;
        return new AuthenticatedHtmlUnitDriver();
    }
}