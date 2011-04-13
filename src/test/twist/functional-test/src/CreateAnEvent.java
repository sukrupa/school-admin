
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import net.sf.sahi.client.Browser;

public class CreateAnEvent {

	private Browser browser;

	public CreateAnEvent(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheCreateAnEventPage() throws Exception {
		assertEquals("Create an Event", browser.title());
	}

	public void whenIFillInWith(String fieldName, String fieldValue) throws Exception {
		fillInWith(fieldName, fieldValue);
	}

	public void andIFillInWith(String fieldName, String fieldValue) throws Exception {
		fillInWith(fieldName, fieldValue);
	}

	public void andIClick(String buttonText) throws Exception {
		browser.submit(buttonText.toLowerCase()).click();
	}

	public void thenIShouldSeeThePage(String pageTitle) throws Exception {
		assertEquals(pageTitle, browser.title());
	}
	
	private void fillInWith(String fieldName, String fieldValue) {
		browser.byId(fieldName.toLowerCase()).setValue(fieldValue);
	}
}
