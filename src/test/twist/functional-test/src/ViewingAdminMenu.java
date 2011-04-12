
// JUnit Assert framework can be used for verification

import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import net.sf.sahi.client.Browser;

public class ViewingAdminMenu {

	private Browser browser;

	public ViewingAdminMenu(Browser browser) {
		this.browser = browser;
	}

	public void givenThatIAmOnTheHomePage() throws Exception {
		assertThat(browser.title(), is("List of Students"));
	}
	
	public void thenIShouldSeeTheMenuOnTheSidebar(String menuHeader) throws Exception {
		assertTrue(browser.div(menuHeader).in(browser.div("sidebar")).exists());
	}

	public void whenIClickTheLinkInTheAdminMenu(String linkText) throws Exception {	
		browser.link(linkText).in(browser.div("sidebar")).click();
	}

	public void thenIGetDirectedToThePage(String pageTitle) throws Exception {
		assertThat(browser.title(), is(pageTitle));
	}

	public void andISeeALinkFor(String linkText) throws Exception {
		assertTrue(browser.link(linkText).exists());
	}	
}
