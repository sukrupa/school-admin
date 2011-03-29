
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class ValidLoginWorkflow {

	private Browser browser;

	public ValidLoginWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void whenIEnterValidCredentials() throws Exception {
		browser.textbox("authUser").setValue("admin");
		browser.password("authPassword").setValue("password");
		browser.submit("Authenticate").click();	
	}

	public void thenIShouldSeeTheStudentsPage() throws Exception {
		assertEquals("List of Students", browser.title());
	}

}
