
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class StudentRecord {

	private Browser browser;

	public StudentRecord(Browser browser) {
		this.browser = browser;
	}

	public void verifyProfileComesUp(String string1) throws Exception {
		browser.div(1).click();
	
	}

}
