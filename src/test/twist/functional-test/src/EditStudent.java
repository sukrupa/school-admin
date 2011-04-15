
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class EditStudent {

	private Browser browser;

	public EditStudent(Browser browser) {
		this.browser = browser;
	}

	public void clickOnStudentRecord(String name) throws Exception {
		browser.link(name).click();
	}

	public void clickOnEditButton() throws Exception {
		browser.submit("student-edit").click();
	}

	public void verifyThatBackgroundIs(String string1) throws Exception {
		assertEquals(string1,browser.textarea("edit-background").text());

	}

	public void verifyThatPerformanceIs(String string1) throws Exception {
		assertEquals(string1,browser.textarea("edit-performance").text());
	}

	public void verifyThatDisciplinaryIs(String string1) throws Exception {
		assertEquals(string1,browser.textarea("edit-disciplinary").text());
	}

	public void click(String label) throws Exception {
		browser.button(label).click();
	}

	public void verifyThatStatusIs(String status) throws Exception {
		assertEquals(status,browser.byId("status").selectedText());
	}

	public void verifyThatSponsoredIsChecked() throws Exception {
		assertEquals(true, browser.byId("sponsored").checked());

	}

	public void clickOnSponsoredCheckbox() throws Exception {
		browser.checkbox("sponsored").click();
	
	}

	public void andSaveTheRecord() throws Exception {
		browser.button("save").click();
	}

	public void goToStudentsPage() throws Exception {
		browser.navigateTo("http://localhost:8080/students");
	}
	
	public void chooseInTheListOf(String option, String fieldName) throws Exception {
		browser.select(fieldName).choose(option);
	}

	public void verifyThatIAmInTheStudentViewPage() throws Exception {
			assertEquals("Student:", browser.title() );
	}

	public void verifyThatTheNewTalentIsInTheListOfTalents(String newTalent)
			throws Exception {
			assertTrue(browser.div("talentsCheckBoxes").containsText(newTalent));
	}

	
}
