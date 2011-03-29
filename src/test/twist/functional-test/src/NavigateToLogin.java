
import net.sf.sahi.client.Browser;

public class NavigateToLogin {

	private Browser browser;

	public NavigateToLogin(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/authentication/login");
		//This method is executed before the scenario execution starts.
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
