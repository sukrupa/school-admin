package org.sukrupa.student;

import net.sf.sahi.client.Browser;

public class NavigateToAnnualUpdatePage {

	private Browser browser;

	public NavigateToAnnualUpdatePage(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		browser.navigateTo("http://localhost:8080/admin/annualupdate");
	}

	public void tearDown() throws Exception {
		
	}

}
