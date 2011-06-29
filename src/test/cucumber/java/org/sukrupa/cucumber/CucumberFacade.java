package org.sukrupa.cucumber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CucumberFacade {

    private static Properties props;

        public static String getConfigProperty(String propertyName) {
        if (props == null) {
            try {
                props = new Properties();
                props.load(new FileInputStream("src/test/resources/cucumber.properties"));
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

}
