
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.sf.sahi.client.Browser;

public class ExplodingStudent {

	private static final String ADD_EXPLODING_STUDENT = "INSERT INTO PUBLIC.STUDENT (ID, NAME, STUDENT_CLASS) VALUES (99, 'EXPLODER', 'Highschool') ";
	private static final String REMOVE_EXPLODING_STUDENT = "DELETE FROM PUBLIC.STUDENT WHERE ID = 99";	
	private Browser browser;

	public ExplodingStudent(Browser browser) {
		this.browser = browser;
	}

	
	public void setUp() throws Exception {
		 runOnDatabase(ADD_EXPLODING_STUDENT);
	}
	public void tearDown() throws Exception {
		 runOnDatabase(REMOVE_EXPLODING_STUDENT);
	}

	private void runOnDatabase(String query) throws SQLException {
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

      
