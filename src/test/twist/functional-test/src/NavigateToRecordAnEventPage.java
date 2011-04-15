
import net.sf.sahi.client.Browser;

public class NavigateToRecordAnEventPage {

	private Browser browser;

	public NavigateToRecordAnEventPage(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/events/create");
	}

	public void tearDown() throws Exception {
		
	}

}
