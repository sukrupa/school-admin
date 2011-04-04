
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class EditStatusFailsIfLeftBlank {

	private Browser browser;

	public EditStatusFailsIfLeftBlank(Browser browser) {
		this.browser = browser;
		
		browser.navigateTo("http://localhost:8080/students/SK20090080/edit?");
		
	}

	public void whenISelectStudentStatusAs(String status) throws Exception {
			browser.select("status").choose(status);
	
	}

	public void andIClickSave() throws Exception {
		browser.button("save").click();
	
	}

	public void thenIShouldSee(String message) throws Exception {
		assertTrue(browser.byId("errorMessages").containsText(message));
	
	}

}
