
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class EditParentsDetail {

	private Browser browser;

	public EditParentsDetail(Browser browser) {
		this.browser = browser;
	}

	public void verifyThatFieldForTheIsOnThePage(String fieldName, String fatherField) throws Exception {
		assertTrue(browser.textbox(fieldName).near(browser.div(fatherField)).exists());
	}

	public void veriftThatWeAreInTheViewStudentPage() throws Exception {
		assertEquals("Student:", browser.title() );
	}

	public void enterInThe(String value, String fieldName) throws Exception {
		browser.textbox(fieldName).setValue(value);
	}

	public void verifyThatTheFieldNameIs(String id, String value) throws Exception {
		assertEquals(value, browser.byId(id).getText());
	}

	public void chooseInTheListOf(String occupation, String fieldName) throws Exception {
		browser.select(fieldName).choose(occupation);
	}

}
