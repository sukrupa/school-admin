
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

	public void thenIShouldBeRedirectedToA401Unauthorised() throws Exception {
		assertEquals("Error 401 Unauthorized",browser.title());
	}

	public void thenIShouldSeeTheLogoutPage() throws Exception {
		assertEquals("Logout",browser.title());
	}

	public void andWhenIVisitTheStudentPage() throws Exception {
		browser.navigateTo("http://localhost:8080/students");
	}

	public void iShouldBeAskedToLogin() throws Exception {
		assertTrue(browser.textbox("j_username").exists());
		assertTrue(browser.password("j_password").exists());
	}

}
