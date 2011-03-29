
import net.sf.sahi.client.Browser;

public class LogIn {

	private Browser browser;

	public LogIn(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		if (browser.textbox("j_username").exists()) {
			browser.textbox("j_username").setValue("admin");
			browser.password("j_password").setValue("password");
			browser.submit("loginButton").click();	
		}
	}

	public void tearDown() throws Exception {
		//This method is executed after the scenario execution finishes.
	}

}
