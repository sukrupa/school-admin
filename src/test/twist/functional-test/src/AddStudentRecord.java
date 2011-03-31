
// JUnit Assert framework can be used for verification


import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

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
		assertEquals(browser.title(), expectedTitle);
	}

	public void andISelectTheAs(String selectBox, String option) throws Exception {
		browser.select(selectBox).choose(option);
	}
	

}
