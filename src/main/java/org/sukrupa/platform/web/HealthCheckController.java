package org.sukrupa.platform.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/healthCheck")
public class HealthCheckController {

    private String imageDirectory;
    private String dbURL;
    private String dbDriver;
    private String dbUser;
    private String dbPassword;
    private String webRoot;

    @Value("${app.image.dir}")
    public void setImageDirectory(String imageDirectory) {
        this.imageDirectory = imageDirectory;
    }

    @Value("${jdbc.url}")
    public void setDBURL(String dbURL) {
        this.dbURL = dbURL;
    }

    @Value("${jdbc.driver}")
    public void setDBDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    @Value("${jdbc.user}")
    public void setDBUser(String dbUser) {
        this.dbUser = dbUser;
    }

    @Value("${jdbc.password}")
    public void setDBPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    @Value("${web.root.dir}")
    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }



    @RequestMapping
    public String list(Map<String, Object> model) {
        List<HealthCheckItem> healthCheckItems = new ArrayList<HealthCheckItem>();
        healthCheckItems.add(imageDirectory());
        healthCheckItems.add(databaseConnection());

        model.put("healthCheckItems", healthCheckItems);

        ArrayList<String> buildInfo = new ArrayList<String>();
    try
    {

      BufferedReader br = new BufferedReader(new FileReader(webRoot + "/build-number.txt"));
      String line = null;
      while ( (line = br.readLine()) != null )
      {
        buildInfo.add(line);
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }

        model.put("buildInfo", buildInfo);
        return "healthCheck";

    }

    private HealthCheckItem databaseConnection() {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return dbURL;
            }

            @Override
            public String getDescription() {
                return "Database Connection";
            }

            @Override
            public boolean isHealthy() {
                try {
                    Class.forName(dbDriver);
                } catch (Exception e) {
                    return false;
                }

                try {
                    DriverManager.getConnection(dbURL, dbUser, dbPassword);
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        };
    }

    private HealthCheckItem imageDirectory() {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return imageDirectory;
            }

            @Override
            public String getDescription() {
                return "Image Directory";
            }

            @Override
            public boolean isHealthy() {
                return new File(imageDirectory).exists();
            }
        };
    }



    private interface HealthCheckItem {
        String getSymptom();
        String getDescription();
        boolean isHealthy();
    }
}