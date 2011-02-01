package net.sf.sahi.test;

import java.io.IOException;

import net.sf.sahi.ant.Report;
import junit.framework.TestCase;

public class TestRunnerTest extends TestCase {
	private static final long serialVersionUID = 3104595408470646058L;

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	/*
	 * java -cp %SAHI_HOME%\lib\ant-sahi.jar net.sf.sahi.test.TestRunner scripts/demo/sahi_demo.sah 
	 * "C:\Program Files\Mozilla Firefox\firefox.exe" http://sahi.co.in/demo/ logs/playback/aaa 
	 * localhost 9999 3 
	 * firefox.exe "-profile %SAHI_USERDATA_DIR%/browser/ff/profiles/sahi$threadNo -no-remote"
	 * 
	 */
	
	public void testExecute() throws Exception {
        String suiteName = "scripts/demo/sahi_demo.sah";
		runSingleTest(suiteName);
		runSingleTest("scripts/demo/link_test.sah");
	}

	private void runSingleTest(String suiteName) throws IOException, InterruptedException {
		String browser = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
		String base = "http://sahi.co.in/demo/";
		String sahiHost = "localhost";
		String port = "9999";
		String threads = "1";
		String browserOption = "-profile D:/sahi/sf/sahi_993/userdata/browser/ff/profiles/sahi$threadNo -no-remote";
		String browserProcessName = "firefox.exe";
		String logDir = "D:/temp/logs/"; // relative paths will be resolved relative to userdata dir.
		
		TestRunner testRunner = 
			new TestRunner(suiteName, browser, base, sahiHost, 
					port, threads, browserOption, browserProcessName);
		testRunner.addReport(new Report("html", logDir));
        String status = testRunner.execute();
        System.out.println(status);
	}

}
