
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.*;
import net.sf.sahi.client.Browser;

public class NavigateBackToEvent {

	private Browser browser;

	public NavigateBackToEvent(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheEventPage(String string1) throws Exception {
		browser.navigateTo("http://localhost:8080/events/4/edit");
		assertEquals("Edit Event: Spice Girls",browser.title());	
	}

	public void andIChangeTheDescriptionTo(String newDescription) throws Exception {
		 browser.textarea("description").setValue(newDescription);
	}

	public void whenINavigateToViewStudents() throws Exception {
		browser.navigateTo("http://localhost:8080/students");
	}

	public void andINavigateToSpiceGirlsEvent() throws Exception {
		browser.navigateTo("http://localhost:8080/events/4/");	
	}

	public void thenTheDescriptionShouldNotContain(String string1) throws Exception {
		assertFalse(browser.div("eventDescription").text().contains(string1));
	}

}
