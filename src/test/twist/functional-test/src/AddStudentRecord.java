
// JUnit Assert framework can be used for verification

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.sahi.client.Browser;

public class AddStudentRecord {

	private Browser browser;

	public AddStudentRecord(Browser browser) {
		this.browser = browser;
	}

	public void whenIClickOnTheLink(String addStudentRecord) throws Exception {
		browser.link(addStudentRecord).click();
		
		
	}

	public void iShouldSeeAPage(String addStudentRecord) throws Exception {
		assertThat(browser.title(), is(addStudentRecord));
	}

}
