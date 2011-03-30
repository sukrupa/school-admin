
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class InvalidLoginWorkflow {

	private Browser browser;

	public InvalidLoginWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void whenIEnterInvalidCredentials() throws Exception {
		browser.textbox("j_username").setValue("admin");
		browser.password("j_password").setValue("wrongPassword");
		browser.byId("loginButton").click();
		
	}

	public void thenIShouldBePromptedForLoginAgain() throws Exception {
		assertEquals("Login",browser.title());
	}

	public void thenIShouldSeeErrorMessage(String errorMessage) throws Exception {
		assertTrue(browser.byId("errorMessages").containsText(errorMessage));
	}

}


