
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
		browser.submit("loginButton");
		
	}

	public void thenIShouldBePromptedForLoginAgain() throws Exception {
		assertEquals("Login",browser.title());
	}

}


