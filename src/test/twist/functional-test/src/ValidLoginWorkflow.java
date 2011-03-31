
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class ValidLoginWorkflow {

	private Browser browser;

	public ValidLoginWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void whenIEnterValidCredentialsWithUsernameAndPassword(String username, String password) 
	throws Exception {
		browser.textbox("j_username").setValue(username);
		browser.password("j_password").setValue(password);
		browser.byId("loginButton").click();	
	}

	public void thenIShouldSeeTheStudentsPage() throws Exception {
		assertEquals("List of Students", browser.title());
	}

}
