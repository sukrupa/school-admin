package org.sukrupa.cucumber;

import cuke4duke.annotation.After;
import net.sf.sahi.client.Browser;
import net.sf.sahi.config.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SahiFacade {
    private static Properties props;
    private static Browser browser;

    //private String browserName;

    public static Browser browser() {
        if (browser == null) {
            // Sets up configuration for proxy. Sets Controller to java mode.
            Configuration.initJava(getProperty("sahi-base"), getProperty("sahi-userdata"));

            // Create a browser and open it
            browser = new Browser(getProperty("browser-path"), getProperty("browser-process"),
                    getProperty("browser-options"));

            browser.open();
        }
        return browser;
    }
    
    private static String getProperty(String propertyName) {
        if (props == null) {
            try {
                props = new Properties();
                props.load(new FileInputStream("src/test/resources/sahi.properties"));
            } catch (FileNotFoundException fnfE) {
                fnfE.printStackTrace();
            } catch (IOException ioE) {
                ioE.printStackTrace();
            }
        }

        // Try to see if there is a property of the format osName-propertyName
        String propertyValue = props.getProperty(propertyName, "NULL");

        return propertyValue;
    }

    public static void closeBrowser() {
        if (browser != null) {
            browser.close();
            browser.kill();
            browser = null;
        }
    }
}
