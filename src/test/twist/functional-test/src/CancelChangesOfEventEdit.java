
// JUnit Assert framework can be used for verification

import static org.junit.Assert.*;
import net.sf.sahi.client.Browser;

public class CancelChangesOfEventEdit {

	private Browser browser;

	public CancelChangesOfEventEdit(Browser browser) {
		this.browser = browser;
	}

	public void andIChangeTheEventTitleTo(String string1) throws Exception {
		browser.byId("title").setValue(string1);
	}

	public void whenIClick(String string1) throws Exception {
		browser.button("cancel").click();
	}

	public void givenIAmOnTheSpiceGirlsEditEventPage() throws Exception {
		browser.navigateTo("http://localhost:8080/events/4/edit");
		assertEquals("Edit Event: Spice Girls",browser.title());
	}

	public void thenTheEventTitleShouldBe(String eventTitle) throws Exception {
		assertEquals("Event: "+eventTitle,browser.title());
	}

}
