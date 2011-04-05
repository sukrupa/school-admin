
// JUnit Assert framework can be used for verification


import org.sukrupa.twist.ScenarioDataUnitOfWork;

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddStudentRecord {

	private final Browser browser;
	private final ScenarioDataUnitOfWork scenarioDataUnitOfWork;

	public AddStudentRecord(Browser browser, ScenarioDataUnitOfWork scenarioDataUnitOfWork) {
		this.browser = browser;
		this.scenarioDataUnitOfWork = scenarioDataUnitOfWork;
	}
	
	public void andIEnterTheStudentId(String studentId) throws Exception {
		andIEnterTheAs("studentId", studentId);
		scenarioDataUnitOfWork.addedStudentWithId(studentId);
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
		assertThat(browser.div("twoLinesTitle").getText(), is(expectedTitle));
	}

	public void andISelectTheAs(String selectBox, String option) throws Exception {
		browser.select(selectBox).choose(option);
	}

	
	public void andShowsInThePage(String selected) throws Exception {
		assertEquals(selected, browser.cell(selected).text());
	
	}
	
	public void whenINavigateTo(String string1) throws Exception {
		browser.navigateTo("http://localhost:8080" + string1);
	}

	public void whenIClickOnTheButton(String string1) throws Exception {
		browser.button(string1).click();
	
	}

	public void iShouldSeeTheTitle(String string1) throws Exception {
		assertEquals(string1,browser.title());
	}

	public void thenIShouldSeeTheErrorMessageForTheField(String errorMessage, String fieldLabel) throws Exception {
		assertThat(browser.span("errorMessage").near(browser.textbox(fieldLabel)).getText(), is(errorMessage));	
	}

}
