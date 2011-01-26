package org.sukrupa.app;

import org.apache.log4j.Logger;
import org.hsqldb.DatabaseManager;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import java.util.Properties;

public class DbServer {

    private Logger LOG = Logger.getLogger(DbServer.class);

    private Server server;

    public DbServer() {
        try {
            server = new Server();
            server.setRestartOnShutdown(false);
            server.setNoSystemExit(true);
            server.setProperties(hsqlProperties());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        LOG.info("Starting HSQL Server...");
        server.start();
        LOG.info("HSQL Server listening on port " + server.getPort());
    }

    public void shutDown() {
        LOG.info("Shutting down HSQL Server...");
        server.signalCloseAllServerConnections();
        server.stop();
        DatabaseManager.closeDatabases(0);
        LOG.info("HSQL Server shutdown completed");
        server = null;

    }

    private HsqlProperties hsqlProperties() {
        return new HsqlProperties(properties());
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("server.database.0", dataDir());
        properties.put("server.dbname.0", "sukrupa");
        return properties;
    }

    private String dataDir() {
        return userHome() + "/.sukrupa/db/sukrupa";
    }

    private String userHome() {
        return System.getProperty("user.home");
    }
}
