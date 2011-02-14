
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class StudentProfile {

	private Browser browser;

	public StudentProfile(Browser browser) {
		this.browser = browser;
	}

	public void verifyStudentRecord(String string1) throws Exception {
		browser.div(string1).mouseOver();
	
	}

	public void navigateToStudentRecord(String string1) throws Exception {
		browser.link(string1).click();
	
	}

	public void navigateToProfile(String string1) throws Exception {
	
	}

	public void verifyThatTalentIs(String string1) throws Exception {
		assertEquals(string1, browser.div("talent").text());
	
	}

	public void verifyThatNameIs(String string1) throws Exception {
		assertEquals(string1, browser.div(string1).text());
	
	}

	public void clickOnStudentRecord(String string1) throws Exception {
		browser.link(string1).click();
	}

}
