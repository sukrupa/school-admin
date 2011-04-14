
// JUnit Assert framework can be used for verification

import org.apache.commons.lang.StringUtils;

import net.sf.sahi.client.Browser;

public class CreateAnEventWithTime {

	private Browser browser;

	public CreateAnEventWithTime(Browser browser) {
		this.browser = browser;
	}

	public void andIFillInWithTheTime(String fieldLabel, String time) {
		String[] splitTime = time.split(" ");
		
		browser.textbox(fieldLabel).setValue(splitTime[0]);
		browser.radio(fieldLabel + StringUtils.capitalize(splitTime[1]));
	}

	public void givenIFollowTheLink(String linkText) throws Exception {
		browser.link(linkText).click();
	}
}
