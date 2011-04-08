
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class StudentProfileTwo {

	private Browser browser;

	public StudentProfileTwo(Browser browser) {
		this.browser = browser;
	}

	public void verifyThatGenderIs(String string1) throws Exception {
		assertEquals(string1, browser.div(string1).text());
		browser.div("student").click();
	
	}

	public void verifyThatDOBIs(String string1) throws Exception {
		assertEquals(string1, browser.div(string1).text());
	
	}

	public void verifyThatBackgroundIs(String string1) throws Exception {
		assertEquals(string1, browser.div("student-background").text());

	}

	public void goBackTo(String linkText) throws Exception {
		browser.link(linkText).click();
	}

}
