package org.sukrupa.twist;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class SchoolAdminNavigation {

	private Browser browser;

	public SchoolAdminNavigation(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheStudentProfileForStudentWithId(String studentId) throws Exception {
		
		String URL = "http://localhost:8080/students/" + studentId;
		
		browser.navigateTo(URL);
	}

}
