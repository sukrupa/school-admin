
import net.sf.sahi.client.Browser;

public class NavigateToEvents {

	private Browser browser;

	public NavigateToEvents(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/events/");
		//This method is executed before the scenario execution starts.
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
