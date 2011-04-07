package org.sukrupa.student;


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

}
