
import net.sf.sahi.client.Browser;

public class LogOutAfterwards {

	private Browser browser;

	public LogOutAfterwards(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		//This method is executed before the scenario execution starts.
	}

	public void tearDown() throws Exception {
		browser.navigateTo("http://localhost:8080/authentication/logout");
	}

}
