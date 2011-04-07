
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.*;
import net.sf.sahi.client.Browser;

public class EditParentsDetail {

	private Browser browser;

	public EditParentsDetail(Browser browser) {
		this.browser = browser;
	}

	public void verifyThatFieldForTheIsOnThePage(String fieldName, String fatherField) throws Exception {
		assertTrue(browser.textbox(fieldName).near(browser.div(fatherField)).exists());
	}

	public void enterInTheFieldForThe(String caregiverName, String fieldName, String caregiver) throws Exception {
		browser.textbox(fieldName).setValue(caregiverName);	
	}

	public void verifyThatFieldIs(String fieldName, String fieldValue) throws Exception {
		assertEquals(fieldValue, browser.textbox(fieldName).getText());
	}

}
