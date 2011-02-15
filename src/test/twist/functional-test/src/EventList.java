import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class EventList {

	private Browser browser;

	public EventList(Browser browser) {
		this.browser = browser;
	}

	public void followTheLink(String linkName) throws Exception {
		browser.link(linkName).click();
	
		assertThat(browser.title(), is("View List of Events"));
	}

	public void checkThatThereAreEventsInTheList(Integer integer1) throws Exception {
		assertTrue(browser.link("Sports Day").exists());
		browser.link("Annual Day").click();
		assertTrue(browser.link("Sports Day").exists());
		assertTrue(browser.link("Sports Day").exists());
		assertTrue(browser.link("Sports Day").exists());
	
	}

	public void checkThatEventIsTitledAndIsOn(Integer integer1, String string2,
			String string3) throws Exception {
	
	}

}
