import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DatabaseTalker {

	protected void runOnDatabase(String query) throws SQLException {
		try {
	           Class.forName("org.hsqldb.jdbc.JDBCDriver");
	       } catch (Exception e) {
	           System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
	           e.printStackTrace();
	           return;
	       }
	
	       Connection connection =
	DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/sukrupa",
	"sa", "");
	
	
	    PreparedStatement preparedStatement =
		connection.prepareStatement(query);
		preparedStatement.execute();
	}

}
