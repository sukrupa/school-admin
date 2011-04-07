
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.*;
import net.sf.sahi.client.Browser;

public class StudentRecordWithNoPhoto {

	private Browser browser;

	public StudentRecordWithNoPhoto(Browser browser) {
		this.browser = browser;
	}
	
	public void clickOnStudentRecord(String string1) throws Exception {
		browser.link(string1).click();
	}


	public void verifyThatAChangePhotoLinkIsPresent() throws Exception {
		assertTrue(browser.link("changeImage").exists());
	}

	public void verifyThatAImageIsShown(Integer integer1) throws Exception {
		assertTrue(browser.byId(integer1).exists());
	}

}
