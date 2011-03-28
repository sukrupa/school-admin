
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;
import net.sf.sahi.client.Browser;

public class RemoveWorkflow {

	private Browser browser;

	public RemoveWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenISelectTheTalent(String talent) throws Exception {
		selectTalent(talent);
	}

	private void selectTalent(String talent) {
		if (!talent.isEmpty())	{
			browser.select("availableTalents").choose(talent);
			browser.click(browser.byId("addTalent"));
		}	
	}

	public void andISelectTheTalent(String talent) throws Exception {
		selectTalent(talent);
	}

	public void whenIRemoveFromTheSelectedTalentsList(String talent)
			throws Exception {
		browser.select("chosenTalents").choose(talent);
		browser.click(browser.byId("removeTalent"));
	}

	public void thenShouldNotAppearInTheSelectedTalentsList(String valueInBox)
			throws Exception {
		assertTrue(!(browser.byId("chosenTalents").containsText(valueInBox)));
	}
	
	
	
	
	
	

}
