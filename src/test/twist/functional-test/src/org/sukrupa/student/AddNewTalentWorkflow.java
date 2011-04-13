package org.sukrupa.student;

// JUnit Assert framework can be used for verification

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import net.sf.sahi.client.Browser;

public class AddNewTalentWorkflow {

	private Browser browser;

	public AddNewTalentWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheAdminPage() throws Exception {
		assertThat(browser.title(), is("Admin Tools"));
	}

	public void andIClickTheLink(String linkName) throws Exception {
		browser.link(linkName).click();
	}

	public void thenIShouldBeOnTheAddNewTalentPage() throws Exception {
		assertThat(browser.title(), is("Add New Talent"));

	}

	public void ifIEnterANewTalent(String talentDescription) throws Exception {
		browser.textbox("description").setValue(talentDescription);
	}

	public void andIClickTheAddTalentButton() throws Exception {
		browser.submit("Add Talent").click();
	}

	public void iShouldSeeAConfirmationMessageSayingTalentWasAddedSuccessfully(String talent) throws Exception {
		assertThat(browser.div("confirmMessage").getText().contains(talent + "was added successfully!"), is(true));	
	}

}
