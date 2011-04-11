
// JUnit Assert framework can be used for verification

import java.lang.*;
import static junit.framework.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.regex.Pattern;

import net.sf.sahi.client.Browser;

public class AnnualClassUpdateWorkflow {


	private Browser browser;
	private String oldDateString;


	public AnnualClassUpdateWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void thenIShouldBeOnTheAnnualClassUpdatePage() throws Exception {
		assertThat(browser.title(), is("Annual Class Update"));
	}

	public void andThereShouldBeAnInformationMessage() throws Exception {
		assertThat(browser.div("informationMessage").exists(), is(true));
	}

	public void whenIClickTheUpdateStudentsButton() throws Exception {
		browser.submit("Update students").click();
	}

	public void thenIShouldBeTakenToTheSuccessPage() throws Exception {
		assertThat(browser.title(), is("Annual Class Update: Success"));
	}

	public void whenIGoToStudentList() throws Exception {
		browser.navigateTo("http://localhost:8080/students");
	}

	public void andIVisitTheProfileOf(String name) throws Exception {
		browser.link(name).click();
	}

	public void thenItShouldHaveTheStatus(String status) throws Exception {
		assertThat(browser.div("value " + status).exists(), is(true));		

	}

	public void andItShouldHaveTheClass(String studentClass) throws Exception {
		assertEquals(studentClass,browser.div("value").near(browser.div("Class")).text());

	}

	public void whenIGoToAlumniStudentList() throws Exception {
		browser.navigateTo("http://localhost:8080/students/?name=&ageFrom=*&ageTo=*&studentClass=*&gender=*&religion=*&caste=*&communityLocation=*&status=Alumni");
	}

	public void whenIGoToDropoutStudentList() throws Exception {
		browser.navigateTo("http://localhost:8080/students/?name=&ageFrom=*&ageTo=*&studentClass=*&gender=*&religion=*&caste=*&communityLocation=*&status=Dropout");
	}

	public void andIShouldSeeTheDate(String date) throws Exception {
		oldDateString = browser.span("updateDate").text();
		assertTrue(isDate(oldDateString));
		assertThat(oldDateString, is(date));
	}

	private boolean isDate(String oldDateString) {
		Pattern datePattern = Pattern.compile("(0[1-9]|[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012]|[1-9])-(19|20)\\d{2}");
		return datePattern.matcher(oldDateString).matches();
	}

	public void whenIGoToTheAnnualClassUpdatePage() throws Exception {
		browser.navigateTo("http://localhost:8080/admin/annualupdate");
	}

	public void thenIShouldSeeANewDateOfLastUpdate() throws Exception {
		String newDate = browser.span("updateDate").text();
		
		assertFalse(newDate.equals(oldDateString));
	}

	public void thenIShouldNotSeeTheButtonToUpdate() throws Exception {
		assertFalse(browser.button("submit").exists());
	}

	public void thenIShouldBeTakenToTheConfirmationPage() throws Exception {
		assertThat(browser.title(), is("Annual Class Update: Confirmation"));
	}
	


	public void thenIShouldBeRedirectedToTheAdminPage() throws Exception {
		assertThat(browser.title(), is("Admin"));
	}

	public void givenIAmOnTheConfirmationPage() throws Exception {
		browser.navigateTo("http://localhost:8080/admin/annualupdate/confirmation");
		thenIShouldBeTakenToTheConfirmationPage();
	}

	public void whenIClickTheSubmit(String submitName) throws Exception {
		browser.submit(submitName).click();
	}

	public void whenIClickTheButton(String buttonName) throws Exception {
		browser.button(buttonName).click();
	}

	public void givenIAmOnTheAdminPage() throws Exception {
		assertThat(browser.title(), is("Admin"));
	}

	public void whenIClickTheLink(String linkName) throws Exception {
		browser.link(linkName).click();
	}

}

