
import net.sf.sahi.client.Browser;

public class NavigteToListOfStudents {

	private Browser browser;

	public NavigteToListOfStudents(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
   		browser.navigateTo("http://localhost:8080/students");

	}

	public void tearDown() throws Exception {
	}

}
