package org.sukrupa.twist.example;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class SearchForSukrupaWithSAHI {

	private Browser browser;

	public SearchForSukrupaWithSAHI(Browser browser) {
		this.browser = browser;
	}

	public void makeSureWeCanFindTheSukrupaWebsiteUsingGoogle() throws Exception {
		browser.navigateTo("http://www.google.com");
		browser.textbox("q").setValue("sukrupa");
		browser.submit("Google Search").click();
		browser.link("Sukrupa Home Page").click();
	
		assertTrue(browser.link("Click here to donate online safely and securely").exists());	
	}

}
