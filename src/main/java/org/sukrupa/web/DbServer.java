package org.sukrupa.web;

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

            LOG.info("Starting HSQL Server...");
            server.start();
            LOG.info("HSQL Server listening on port " + server.getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        return new HsqlProperties(serverProperties());
    }

    private Properties serverProperties() {
        Properties properties = new Properties();
        properties.put("server.database.0", "target/db/sukrupa");
        properties.put("server.dbname.0", "sukrupa");
        return properties;
    }
}
