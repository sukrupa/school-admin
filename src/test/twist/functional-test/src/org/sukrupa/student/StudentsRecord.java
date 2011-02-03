package org.sukrupa.student;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class StudentsRecord {

	private Browser browser;

	public StudentsRecord(Browser browser) {
		this.browser = browser;
	}

	public void verifyShowsUpInTheList(String string1) throws Exception {
		assertTrue(browser.cell(string1).exists());			
	}

}
