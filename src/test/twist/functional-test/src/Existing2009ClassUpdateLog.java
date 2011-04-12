
import net.sf.sahi.client.Browser;

public class Existing2009ClassUpdateLog extends DatabaseTalker {

	private Browser browser;
	private static final String ADD_EVENT_LOG =	"INSERT INTO PUBLIC.SYSTEM_EVENT_LOG (EVENT,LAST_HAPPENED) VALUES ('annual class update','2009-12-20')";
	private static final String REMOVE_EVENT_LOG =	"DELETE FROM PUBLIC.SYSTEM_EVENT_LOG WHERE EVENT = 'annual class update'";

	public Existing2009ClassUpdateLog(Browser browser) {
		this.browser = browser;
	}

	public void setUp() throws Exception {
		runOnDatabase(ADD_EVENT_LOG);
	}

	public void tearDown() throws Exception {
		runOnDatabase(REMOVE_EVENT_LOG);
	}

}
