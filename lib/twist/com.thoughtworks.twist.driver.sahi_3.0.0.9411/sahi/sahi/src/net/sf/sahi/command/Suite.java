/**
 * Sahi - Web Automation and Test Tool
 *
 * Copyright  2006  V Narayan Raman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.sahi.command;

import net.sf.sahi.issue.JiraIssueCreator;
import net.sf.sahi.report.HtmlReporter;
import net.sf.sahi.report.JunitReporter;
import net.sf.sahi.request.HttpRequest;
import net.sf.sahi.response.HttpResponse;
import net.sf.sahi.response.NoCacheHttpResponse;
import net.sf.sahi.session.Session;
import net.sf.sahi.session.Status;
import net.sf.sahi.test.SahiTestSuite;

public class Suite {

    public void start(final HttpRequest request) {
        Session session = request.session();
        String suitePath = request.getParameter("suite");
        String base = request.getParameter("base");
        String browser = request.getParameter("browser");
        String browserOption = request.getParameter("browserOption");
        String browserProcessName = request.getParameter("browserProcessName");
        //System.out.println("### session.id()="+session.id());
        final SahiTestSuite suite = new SahiTestSuite(net.sf.sahi.config.Configuration.getAbsoluteUserPath(suitePath), base, browser,
                session.id(), browserOption, browserProcessName);
        int threads = 1;
        try {
            threads = Integer.parseInt(request.getParameter("threads"));
        } catch (Exception e) {
        }
        suite.setAvailableThreads(threads);
        setReporters(suite, request);
        setIssueCreators(suite, request);
        new Thread(){
        	@Override
        	public void run() {
        		suite.run();
        	}
        }.start();
    }

    private void setIssueCreators(final SahiTestSuite suite, final HttpRequest request) {
        String propFile = request.getParameter("jira");
        if (propFile != null) {
            suite.addIssueCreator(new JiraIssueCreator(propFile));
        }
    }

    public HttpResponse status(final HttpRequest request) {
        Session session = request.session();
        Status status = session.getStatus();
        if (status == null) status = Status.FAILURE;
        return new NoCacheHttpResponse(status.getName());
    }

    private void setReporters(final SahiTestSuite suite, final HttpRequest request) {
        String logDir = request.getParameter("junit");
        if (logDir != null) {
            suite.addReporter(new JunitReporter(logDir));
        }
        logDir = request.getParameter("html");
        if (logDir != null) {
        	if (!logDir.equals("")) logDir = net.sf.sahi.config.Configuration.getAbsoluteUserPath(logDir);
            suite.addReporter(new HtmlReporter(logDir));
        }
    }

    public void kill(final HttpRequest request) {
        Session session = request.session();
        SahiTestSuite suite = SahiTestSuite.getSuite(session.id());
        if (suite != null) suite.kill();
    }
}
