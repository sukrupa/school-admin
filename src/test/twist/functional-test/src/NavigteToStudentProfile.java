
import net.sf.sahi.client.Browser;

public class NavigteToStudentProfile {

	private Browser browser;

	public NavigteToStudentProfile(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
   		browser.navigateTo("http://localhost:8080/students");

	}

	public void tearDown() throws Exception {
	}

}
