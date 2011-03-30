
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class StudentRecordWithPhoto {

	private Browser browser;

	public StudentRecordWithPhoto(Browser browser) {
		this.browser = browser;
	}

	public void clickOnStudentRecord(String string1) throws Exception {
			browser.link(string1).click();
	}

	public void verifyThatProfileImageIsShown(String string1) throws Exception {
		assertTrue(browser.image(string1).exists());
	
	}

	public void gotoAllStudents() throws Exception {
   		browser.navigateTo("http://localhost:8080/students");
	}
	

}
