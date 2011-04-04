
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.*;
import net.sf.sahi.client.Browser;


public class NavigateToEditEventPage {

	private Browser browser;

	public NavigateToEditEventPage(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmViewingSpiceGirlsEvent() throws Exception {
		browser.navigateTo("http://localhost:8080/events/4");
		assertEquals("Event: Spice Girls", browser.title());
	}

	public void whenIClick(String string1) throws Exception {
		browser.submit(string1).click();
	}

	public void thenISeeThePage(String string1) throws Exception {
		assertEquals(string1,browser.title());
	}

}
