package org.sukrupa.student;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import net.sf.sahi.client.Browser;

public class MoveUpAClassWorkflow {

	private Browser browser;

	public MoveUpAClassWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheMoveUpAClassPage() throws Exception {
		assertThat(browser.title(), is("Move all students up a class"));
	}

	public void thenThereShouldBeAnInformationMessage() throws Exception {
		assertThat(browser.div("informationMessage").exists(), is(true));
	}

	public void whenIClickTheMoveStudentsButton() throws Exception {
		browser.submit("Move all students...").click();
	}

	public void thenIShouldBeTakenToTheSuccessPage() throws Exception {
		assertThat(browser.title(), is("Moved all students successfully"));
	}

}
