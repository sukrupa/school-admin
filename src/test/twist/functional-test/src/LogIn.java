
import net.sf.sahi.client.Browser;

public class LogIn {

	private Browser browser;

	public LogIn(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		if (browser.textbox("authUser").exists()) {
			browser.textbox("authUser").setValue("admin");
			browser.password("authPassword").setValue("password");
			browser.submit("Authenticate").click();	
		}
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
