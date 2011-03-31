
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;
public class ValidLogoutWorkflow {

	private Browser browser;

	public ValidLogoutWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void whenIClick(String link) throws Exception {
		browser.link(link).click();
	}

	public void thenIShouldSeeTheLogoutPage() throws Exception {
		assertEquals("Logout",browser.title());
	}

}
