
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class InvalidLoginWorkflow {

	private Browser browser;

	public InvalidLoginWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void whenIEnterInvalidCredentials() throws Exception {
		browser.textbox("authUser").setValue("admin123");
		browser.password("authPassword").setValue("wrongPassword");
		browser.submit("Authenticate");
		
	}

	public void thenIShouldBePromptedForLoginAgain() throws Exception {
		assertTrue(browser.table("sahiAuth").exists());
	}

}


