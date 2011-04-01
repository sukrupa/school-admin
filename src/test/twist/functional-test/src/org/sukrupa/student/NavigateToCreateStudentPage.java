package org.sukrupa.student;

import net.sf.sahi.client.Browser;

public class NavigateToCreateStudentPage {

	private Browser browser;

	public NavigateToCreateStudentPage(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/students/create");
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
