
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class SearchWorkflow {

	private Browser browser;

	public SearchWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void selectTalentAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("talent").choose(string1);
		}
	}

	public void hitSearchButton() throws Exception {
		browser.submit("Search").click();
	
	}

	public void verifyThatShowsInSearchResults(String string1) throws Exception {
		assertEquals(string1, browser.cell(string1).text());
	
	}

	public void selectMinimumAgeAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("ageFrom").choose(string1);
		}
	}

	public void selectMaximumAgeAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("ageTo").choose(string1);
		}
	
	}

	public void selectReligionAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("religion").choose(string1);
		}
	}

	public void selectClassAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("studentClass").choose(string1);
		}
	}

	public void selectGenderAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("gender").choose(string1);
		}
	
	}

	public void selectCasteAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
		browser.select("caste").choose(string1);
		}
	}

	public void selectCommunityLocationAs(String string1) throws Exception {
		if (!string1.isEmpty()) 
		{
			browser.select("communityLocation").choose(string1);
		}
		
	
	}

}
