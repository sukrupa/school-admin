
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
		assertEquals(expectedName,browser.div("value").near(browser.div("Name")).text());
	}

	public void clickOnStudentRecord(String name) throws Exception {
		browser.link(name).click();
	}

	public void verifyThatStatusIs(String expectedStatus) throws Exception {
		assertEquals(expectedStatus, browser.div("value existing").near(browser.div("Student Status")).text());
	}

	public void verifyThatDisciplinaryIs(String string1) throws Exception {
		assertEquals(string1,browser.div("student-disciplinary").text());
	}

	public void verifyThatPerformanceIs(String string1) throws Exception {
		assertEquals(string1,browser.div("student-performance").text());

	}

	public void clickOnEditButton() throws Exception {
		browser.button("student-edit").click();
	}

	public void verifyThatBackgroundIs(String string1) throws Exception {
		assertEquals(string1,browser.textarea("student-background").text());

	}





}
