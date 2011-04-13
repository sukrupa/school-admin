package org.sukrupa.platform.web;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.io.File.separator;
import static java.util.Arrays.asList;

@Controller
@RequestMapping("/healthCheck")
public class HealthCheckController {

    private String imageDirectory;
    private String dbUrl;
    private String dbDriver;
    private String dbUser;
    private String dbPassword;
    private String webRoot;

    @Autowired
    public HealthCheckController(@Value("${app.image.dir}") String imageDirectory,
                                 @Value("${jdbc.url}") String dbUrl,
                                 @Value("${jdbc.driver}") String dbDriver,
                                 @Value("${jdbc.user}") String dbUser,
                                 @Value("${jdbc.password}") String dbPassword,
                                 @Value("${web.root.dir}") String webRoot) {

        this.imageDirectory = imageDirectory;
        this.dbUrl = dbUrl;
        this.dbDriver = dbDriver;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.webRoot = webRoot;
    }

    @RequestMapping
    public String list(Map<String, Object> model) {
        List<HealthCheckTest> healthCheckTests = new ArrayList<HealthCheckTest>();
        healthCheckTests.add(imageDirectory());
        healthCheckTests.add(databaseConnection());
        model.put("healthCheckTests", healthCheckTests);

        List<HealthCheckItem> healthCheckItems = new ArrayList<HealthCheckItem>();
        for (final String line : extractBuildInfo()) {
            healthCheckItems.add(buildInfoLine(line));
        }

        extractDbMigrationsInto(healthCheckItems);
        extractRunTimeInfoInto(healthCheckItems);

        model.put("healthCheckItems", healthCheckItems);

        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        model.put("systemProperties", getSystemProperties(runtimeMXBean));
        model.put("classPath", runtimeMXBean.getClassPath().split(":"));

        return "healthCheck";
    }

    private Set<Map.Entry<String, String>> getSystemProperties(RuntimeMXBean runtimeMXBean) {
        Map<String, String> properties = runtimeMXBean.getSystemProperties();
        properties.remove("java.class.path");
        return properties.entrySet();
    }

    private void extractRunTimeInfoInto(List<HealthCheckItem> healthCheckItems) {
        final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        healthCheckItems.add(healthCheckItem(StringUtils.join(runtimeMXBean.getInputArguments(), " "), "Input Arguments"));
        healthCheckItems.add(healthCheckItem(new Date(runtimeMXBean.getStartTime()).toString(), "Start Time"));
    }

    private HealthCheckItem healthCheckItem(final String symptom, final String description) {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return symptom;
            }

            @Override
            public String getDescription() {
                return description;
            }
        };
    }

    private void extractDbMigrationsInto(List<HealthCheckItem> healthCheckItems) {
        try {
            Class.forName(dbDriver);
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            ResultSet result = connection.createStatement().executeQuery("select * from changelog order by change_number desc limit 1");
            result.next();

            healthCheckItems.add(changeSetNumber(result.getInt("CHANGE_NUMBER")));
            healthCheckItems.add(changeSetDescription(result.getString("DESCRIPTION")));
            healthCheckItems.add(changeSetDate(result.getObject("COMPLETE_DT")));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private HealthCheckItem buildInfoLine(final String line) {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return line.split(":")[1];
            }

            @Override
            public String getDescription() {
                return line.split(":")[0];
            }
        };
    }

    private HealthCheckItem changeSetDate(final Object date) {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return date.toString();
            }

            @Override
            public String getDescription() {
                return "Change Set Date";
            }
        };
    }

    private HealthCheckItem changeSetDescription(final String description) {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return description;
            }

            @Override
            public String getDescription() {
                return "Change Set Description";
            }
        };
    }

    private HealthCheckItem changeSetNumber(final int changeSetNumber) {
        return new HealthCheckItem() {
            @Override
            public String getSymptom() {
                return String.valueOf(changeSetNumber);
            }

            @Override
            public String getDescription() {
                return "Change Set Number";
            }
        };
    }

    private ArrayList<String>  extractBuildInfo() {
        ArrayList<String> buildInfo = new ArrayList<String>();
        try {

            BufferedReader br = new BufferedReader(new FileReader(webRoot + "/build-number.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                buildInfo.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buildInfo;
    }

    private HealthCheckTest databaseConnection() {
        return new HealthCheckTest() {
            @Override
            public String getSymptom() {
                return dbUrl;
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
                    DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        };
    }

    private HealthCheckTest imageDirectory() {
        return new HealthCheckTest() {
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
    }

    private interface HealthCheckTest {
        String getSymptom();
        String getDescription();
        boolean isHealthy();
    }
}
