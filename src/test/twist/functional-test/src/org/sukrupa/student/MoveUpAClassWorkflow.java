package org.sukrupa.student;

// JUnit Assert framework can be used for verification

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import net.sf.sahi.client.Browser;

public class MoveUpAClassWorkflow {

	private Browser browser;

	public MoveUpAClassWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void givenIAmOnTheMoveUpAClassPage() throws Exception {
		assertThat(browser.title(), is("Move all students up a class"));
	}

}
