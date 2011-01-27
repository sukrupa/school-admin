package org.sukrupa.platform.server;

import org.apache.log4j.Logger;
import org.hsqldb.DatabaseManager;
import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

import java.io.File;
import java.util.Properties;

import static org.sukrupa.platform.text.StringManipulation.join;

public class DbServer {

    private Logger LOG = Logger.getLogger(DbServer.class);

    private final String rootDir;
    private final String dbName;
    private Server server;

    public DbServer(String rootDir, String dbName) {
        try {
            this.rootDir = rootDir;
            this.dbName = dbName;
            server = new Server();
            server.setRestartOnShutdown(false);
            server.setNoSystemExit(true);
            server.setProperties(serverProperties());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        if (serverStartedAlready()) {
            LOG.info("Looks like some other process has already started the DB Server. Will skip starting a new one.");
            server = null;
            return;
        }

        LOG.info("Starting DB Server...");
        server.start();
        LOG.info("DB Server started on port " + server.getPort());
    }

    public void shutDown() {
        if (didNotStartServerOurselves()) {
            LOG.info("Won't stop DB Server because it had been started by some other process.");
            return;
        }

        LOG.info("Shutting down DB Server...");
        server.signalCloseAllServerConnections();
        server.stop();
        DatabaseManager.closeDatabases(0);
        LOG.info("DB Server shutdown completed");
        server = null;
    }

    private boolean serverStartedAlready() {
        return lockFile().exists();
    }

    private boolean didNotStartServerOurselves() {
        return server == null;
    }

    private HsqlProperties serverProperties() {
        Properties properties = new Properties();
        properties.put("server.database.0", join(rootDir, dbName));
        properties.put("server.dbname.0", dbName);
        return new HsqlProperties(properties);
    }

    private File lockFile() {
        return new File(join(rootDir, dbName + ".lck"));
    }
}
