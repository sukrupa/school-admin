package org.sukrupa.student;

// JUnit Assert framework can be used for verification

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import net.sf.sahi.client.Browser;

public class AdminPageWorkflow {

	private Browser browser;

	public AdminPageWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheAdminPage() throws Exception {
		assertThat(browser.title(), is("Admin"));

	}

	public void whenIClickThePerformAnnualClassUpdateLink() throws Exception {
		browser.link("Perform Annual Class Update").click();
	}

	public void iShouldBeTakenToTheAnnualClassUpdatePage() throws Exception {
		assertThat(browser.title(), is("Annual Class Update"));
	}

}
