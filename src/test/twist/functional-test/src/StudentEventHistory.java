
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class StudentEventHistory {

	private Browser browser;

	public StudentEventHistory(Browser browser) {
		this.browser = browser;
		this.browser.navigateTo("http://localhost:8080/students/34545");
	}

	public void verifyThatEventHistoryContains(String eventHistory) throws Exception {
		assertTrue(browser.div("student-event").text().contains(eventHistory));
	}
}
