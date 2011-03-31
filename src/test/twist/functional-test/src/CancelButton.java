
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class CancelButton {

	private Browser browser;

	public CancelButton(Browser browser) {
		this.browser = browser;
	}

	public void whenINavigateTo(String string1) throws Exception {
		browser.navigateTo("http://localhost:8080" + string1);
	}

	public void whenIClickOnTheButton(String string1) throws Exception {
		browser.button(string1).click();
	
	}

	public void iShouldSeeTheTitle(String string1) throws Exception {
		assertEquals(string1,browser.title());
	}

}
