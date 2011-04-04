
import net.sf.sahi.client.Browser;

public class NavigteToEditBhavanisProfile {

	private Browser browser;

	public NavigteToEditBhavanisProfile(Browser browser) {
		this.browser = browser;
		
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/students/SK20090080/edit?");
	}

	public void tearDown() throws Exception {
	}

}
