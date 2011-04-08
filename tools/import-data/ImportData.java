import java.awt.datatransfer.ClipboardOwner;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.*;
import java.util.Scanner;

public class ImportData {

    /**
     * Read the contents of the given file.
     */
    static void execute(String filename, Connection connection) throws java.sql.SQLException, IOException {
        Scanner scanner = new Scanner(new java.io.FileInputStream(filename), "UTF-8");

        try {
            while (scanner.hasNextLine()) {
                PreparedStatement preparedStatement = connection.prepareStatement(scanner.nextLine());
                preparedStatement.execute();
            }
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] sqlFiles) throws java.sql.SQLException, IOException {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }

        Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/sukrupa", "sa", "");

        for (String fileName : sqlFiles) {
            execute(fileName, connection);
        }
    }
}