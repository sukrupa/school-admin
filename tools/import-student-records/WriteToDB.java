import java.io.*;
import java.sql.*;
import java.util.Properties;


public class WriteToDB {
	
    public static void main(String[] args) {
        Properties commandLineArgs = parseCommandLine(args);
		
        String sqlFilePath = commandLineArgs.getProperty("sqlFilePath");
        String databasePropertiesPath = commandLineArgs.getProperty("databasePropertiesPath");
		
        Properties databaseProperties = readDatabaseProperties(databasePropertiesPath);
		
        BufferedReader reader = openFile(sqlFilePath);
		
        System.out.println("Writing to database from file: " + sqlFilePath);
		
        Connection connection = createDatabaseConnection(databaseProperties);
		
        runStatements(reader, connection);
		
        closeDatabaseConnection(connection);
		
        System.out.println("Data Insertion Complete.");
    }
	
    private static Properties readDatabaseProperties(String databasePropertiesPath) {
        BufferedReader reader = openFile(databasePropertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
	
    private static void closeDatabaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }
	
    private static BufferedReader openFile(String filename) {
        try {
            return new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
	
    private static Properties parseCommandLine(String[] args) {
        Properties commandLineArgs = new Properties();
        commandLineArgs.setProperty("sqlFilePath", args[0]);
        commandLineArgs.setProperty("databasePropertiesPath", args[1]);
        return commandLineArgs;
    }
	
    private static void runStatements(BufferedReader reader, Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String line;
            while ((line = reader.readLine()) != null) {
				System.out.println(line);
                int result = statement.executeUpdate(line);
                if (result == 0) {
                    System.out.println("Statement not processed: " + line);
                }
            }
			
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
				
            }
        }
    }
	
    private static Connection createDatabaseConnection(Properties databaseProperties) {
        Connection connection = null;
        try {
            String driver = databaseProperties.getProperty("jdbc.driver");
            String url = databaseProperties.getProperty("jdbc.url");
            String username = databaseProperties.getProperty("jdbc.user");
            String password = databaseProperties.getProperty("jdbc.password");
			
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
	
}