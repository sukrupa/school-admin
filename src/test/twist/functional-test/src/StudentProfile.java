
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

import static junit.framework.Assert.*;

public class StudentProfile {

	private Browser browser;

	public StudentProfile(Browser browser) {
		this.browser = browser;
	}

	public void verifyThatTalentIs(String expectedTalent) throws Exception {
		assertEquals(expectedTalent, browser.div("talent").text());
	}

	public void verifyThatNameIs(String expectedName) throws Exception {
		assertEquals(expectedName, browser.div(expectedName).text());
	
	}

	public void clickOnStudentRecord(String name) throws Exception {
		browser.link(name).click();
	}

}
