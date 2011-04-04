
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertEquals;
import net.sf.sahi.client.Browser;

public class EditStatusFromNotSetToExistingStudent {

	private Browser browser;

	public EditStatusFromNotSetToExistingStudent(Browser browser) {
		this.browser = browser;
	}

	public void whenISelectStudentStatusAs(String status) throws Exception {
		if (!status.isEmpty()) 
		{
			browser.select("status").choose(status);
		}
	}

	public void andIClickSave() throws Exception {
		browser.button("save").click();
	}

	public void thenTheStudentStatusShouldBe(String expectedStatus) throws Exception {
		assertEquals(expectedStatus,browser.div("value existing").near(browser.div("Student Status")).text());
	}

}
