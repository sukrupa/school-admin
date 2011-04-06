
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import net.sf.sahi.client.Browser;

public class EventEditMandatoryFields {

	private Browser browser;

	public EventEditMandatoryFields(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheEventPage(String string1) throws Exception {
		browser.navigateTo("http://localhost:8080/events/4/edit");
		assertEquals("Edit Event: Spice Girls",browser.title());	
	}

	public void whenIClear(String fieldId) throws Exception {
		 browser.textbox(fieldId).setValue("");
	}

	public void andIClick(String buttonId) throws Exception {
		 browser.byId(buttonId).click();
	}

	public void thenIShouldGetAnError(String errorMessage) throws Exception {
		browser.byId("errorMessages").text().contains(errorMessage);
	}

}
