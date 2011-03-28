
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;


public class ClearWorkflow {

	private Browser browser;

	public ClearWorkflow(Browser browser) {
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

	public void whenIHitTheClearButton() throws Exception {
		browser.click(browser.byId("clearTalents"));
	}

	public void thenTheShouldAppearInTheSelectedTalentBox(String valueInBox)
	throws Exception {
		
		assertTrue(browser.byId("chosenTalents").containsText(valueInBox));
	
	}

}
