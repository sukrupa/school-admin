package org.sukrupa.app.students;

// JUnit Assert framework can be used for verification

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import net.sf.sahi.client.Browser;

public class AddNoteToStudent {

	private Browser browser;

	public AddNoteToStudent(Browser browser) {
		this.browser = browser;
	}

	public void whenIAddTheNote(String noteText) throws Exception {
		browser.textarea("new-note").setValue(noteText);
		browser.submit("Add a Note").click();				
	}
	
	public void thenASuccessMessageShouldAppear() {
		assertThat(browser.div("successDiv").isVisible(), is(true));
	}
	
	public void andTheNoteShouldBeInTheListOfNotes(String noteText) {
		assertThat(browser.div("box").containsText(noteText), is(true));
	}

	
}
