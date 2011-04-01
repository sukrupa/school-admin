
// JUnit Assert framework can be used for verification


import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddStudentRecord {

	private Browser browser;

	public AddStudentRecord(Browser browser) {
		this.browser = browser;
	}

	public void whenIClickOnTheLink(String addStudentRecord) throws Exception {
		browser.link(addStudentRecord).click();	
	}

	public void andIEnterTheAs(String fieldName, String fieldValue) throws Exception {
		browser.textbox(fieldName).setValue(fieldValue);
	}

	public void andIClick(String label) throws Exception {
		browser.submit(label).click();
	}

	public void thenIShouldSeeThePage(String expectedTitle) throws Exception {
		assertThat(browser.title(), is(expectedTitle));
	}

	public void andISelectTheAs(String selectBox, String option) throws Exception {
		browser.select(selectBox).choose(option);
	}
	
	public void andISelectTheTalent(String option) throws Exception {
		browser.checkbox(option).click();
	}
	
	public void andShowsInThePage(String selected) throws Exception {
		assertEquals(selected, browser.cell(selected).text());
	
	}

	public void whenIClickSubmit() throws Exception {
		browser.submit("Submit").click();
	}

	public void fofo() throws Exception {
		browser.checkbox("talents").click();
	
	}
	

}
