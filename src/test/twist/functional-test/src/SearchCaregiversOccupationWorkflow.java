
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class SearchCaregiversOccupationWorkflow {

	private Browser browser;

	public SearchCaregiversOccupationWorkflow(Browser browser) {
		this.browser = browser;
		this.browser.navigateTo("http://localhost:8080/students/search");
	}

	public void andISelectCaregiversOccupationAs(String caregiversOccupation) throws Exception {
		if (!caregiversOccupation.isEmpty()) 
		{
			browser.select("caregiversOccupation").choose(caregiversOccupation);
		}
	}

}
