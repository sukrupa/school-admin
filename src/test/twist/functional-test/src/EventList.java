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
	
		assertThat(browser.title(), is("List of Events"));
	}

	public void checkThatThereAreEventsInTheList(Integer integer1) throws Exception {
	
	}

	public void checkThatEventIsTitledAndIsOn(Integer integer1, String string2,
			String string3) throws Exception {
		assertEquals("Fake event_title 2", browser.link("Fake event_title 2")
				.text());
	
	}

	public void checkThatEventIsOn(String string1) throws Exception {
		assertEquals("25-03-2011", browser.cell("25-03-2011").text());
	
	}

	public void verifyThatYouAreOnRecord(String string1) throws Exception {
		assertEquals("Sports Day",
				browser.div("Sports Day").text());
	
	}

	public void checkThatEventIsTitled(Integer integer1, String string2)
			throws Exception {
		assertEquals(string2, browser.cell(string2).text());
	
	}

	public void clickOn(String string1) throws Exception {
		browser.link(string1).click();
	
	}

}
