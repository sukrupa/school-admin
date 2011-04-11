

import net.sf.sahi.client.Browser;

public class ExplodingStudent extends DatabaseTalker {

	private static final String ADD_EXPLODING_STUDENT = "INSERT INTO PUBLIC.STUDENT (ID, NAME, DATE_OF_BIRTH, STUDENT_CLASS) VALUES (99, 'EXPLODER','2001-11-11', 'Highschool') ";
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


}

      
