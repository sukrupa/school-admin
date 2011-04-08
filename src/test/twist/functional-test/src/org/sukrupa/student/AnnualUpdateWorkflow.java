package org.sukrupa.student;


import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import net.sf.sahi.client.Browser;


public class AnnualUpdateWorkflow {

	private Browser browser;

	public AnnualUpdateWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheAnnualClassUpdatePage() throws Exception {
		assertThat(browser.title(), is("Annual Class Update"));
	}

	public void thenThereShouldBeAnInformationMessage() throws Exception {
		assertThat(browser.div("informationMessage").exists(), is(true));
	}

	public void whenIClickTheUpdateStudentsButton() throws Exception {
		browser.submit("Update students").click();
	}

	public void thenIShouldBeTakenToTheSuccessPage() throws Exception {
		assertThat(browser.title(), is("Updated all students successfully"));
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

}
