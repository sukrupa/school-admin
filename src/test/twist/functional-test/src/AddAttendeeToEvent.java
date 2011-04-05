
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class AddAttendeeToEvent {

	private Browser browser;

	public AddAttendeeToEvent(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheSpiceGirlsEditEventPage() throws Exception {
		browser.navigateTo("http://localhost:8080/events/4/edit");
		assertEquals("Edit Event: Spice Girls",browser.title());
	}

	public void whenIAddTo(String item, String list) throws Exception {
		 String newText = browser.textarea(list).value() + ", "+ item;
		 browser.textarea(list).setValue(newText);
	
	}

	public void andClickOn(String buttonId) throws Exception {
		 browser.byId(buttonId)	.click();
	}

	public void thenShouldBeListedAsAnAttendee(String studentName) throws Exception {
		assertTrue(browser.div("attendees").text().contains(studentName));
	}

	public void itShouldDisplayTheAttendeeId(String attendeeId) throws Exception {
//		assertEquals(attendeeId, browser.textarea("attendees").getText());
		assertTrue(browser.textarea("attendees").value().contains(attendeeId));
	}

	public void thenISeeThePage(String title) throws Exception {
		assertEquals("Event: Spice Girls",browser.title());
	}

}
