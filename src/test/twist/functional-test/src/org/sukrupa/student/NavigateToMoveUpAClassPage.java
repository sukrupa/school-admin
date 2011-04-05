package org.sukrupa.student;

import net.sf.sahi.client.Browser;

public class NavigateToMoveUpAClassPage {

	private Browser browser;

	public NavigateToMoveUpAClassPage(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/students/moveupaclass");
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
