package net.sf.sahi.rhino;

import java.util.logging.Logger;

import net.sf.sahi.config.Configuration;
import net.sf.sahi.playback.SahiScript;
import net.sf.sahi.report.HtmlReporter;
import net.sf.sahi.report.Report;
import net.sf.sahi.report.ResultType;
import net.sf.sahi.session.Status;
import net.sf.sahi.test.SahiTestSuite;
import net.sf.sahi.test.TestLauncher;
import net.sf.sahi.util.Utils;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

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


public class RhinoScriptRunner extends ScriptRunner implements Runnable {

	private SahiScript script;

	Report report;

	TestLauncher launcher;

	private String recoveryScript;
	
	private static final Logger logger = Logger.getLogger("net.sf.sahi.rhino.ScriptRunner");

	private Scriptable scope;

	RhinoScriptRunner(String js) {
		setJS(js);	}

	public RhinoScriptRunner(SahiScript script) {
		this(script, null, null);
	}

	public RhinoScriptRunner(SahiScript script, SahiTestSuite suite, TestLauncher launcher) {
		this.script = script;
		this.suite = suite;
		this.launcher = launcher;
		setReporter();
		String jsString = script.jsString();
		setJS(jsString);
	}

	private void setJS(String jsString) {
		this.js = "_sahi.start();\n" + jsString;
		this.js += "\n_sahi.end();\n";
	}

	public void execute() {
		new Thread(this).start();
	}
	
	public void setRecoveryScript(String recoveryScript){
		this.recoveryScript = recoveryScript;
	}

	public void run() {
//		long initialFreeMemory = Runtime.getRuntime().freeMemory();
		report.startTimer();
		String lib = Configuration.getRhinoLibJS();
		Context cx = Context.enter();
		cx.setOptimizationLevel(Configuration.getRhinoOptimizationLevel());
			
		scope = cx.initStandardObjects();
		try {
			Object wrappedOut = Context.javaToJS(this, scope);
			ScriptableObject.putProperty(scope, "ScriptRunner", wrappedOut);

			cx.evaluateString(scope, lib, "RhinoScriptRunner.run", 1, null);
			cx.evaluateString(scope, js, "RhinoScriptRunner.run", 1, null);

			// System.out.println("End of script: " + script.getScriptName());
		} catch (RhinoException ee) {
			if (browserException != null){
				report.addResult(browserException, ResultType.ERROR, debugInfo, null);
				browserException= null;
			}else{
				report.addResult("JSERROR ", ResultType.ERROR, script.getDebugInfo(ee.lineNumber()-1), ee.details());
			}
			setHasError();

			if(recoveryScript != null){
				try{
					report.addResult("--- Recovery Start ---", ResultType.INFO, null, null);
					cx.evaluateString(scope, recoveryScript, "RhinoScriptRunner.run", 1, null);
				} catch (RhinoException ee2) {
					if (browserException != null){
						report.addResult(browserException, ResultType.ERROR, debugInfo, null);
					}else{
						report.addResult("JSERROR ", ResultType.ERROR, script.getDebugInfo(ee2.lineNumber()-1), ee2.details());
					}
				} finally {
					report.addResult("--- Recovery End ---", ResultType.INFO, null, null);
				}
			}
		} catch (Exception e) {
			logger.warning(Utils.getStackTraceString(e, false));
			report.addResult("JSERROR ", ResultType.ERROR, e.getMessage(), e.getMessage());
			setHasError();
		} finally {
			// Exit from the context.
//			report.addResult("Total Memory in JVM is: " + Runtime.getRuntime().totalMemory()/(1024*1024) + " MB;<br/>" +
//					"Free Memory in JVM is: " + Runtime.getRuntime().freeMemory()/(1024*1024) + " MB;<br/>" +
//							"Memory used during this test is: " + ((initialFreeMemory - Runtime.getRuntime().freeMemory())/(1024*1024)) + " MB", 
//							ResultType.CUSTOM2, "", null);
			Context.exit();
			stop();
			cx = null;
		}
	}
	
	public void markStepDoneFromLib(String stepId, String typeName, String failureMessage) {
		markStepDone(stepId, ResultType.getType(typeName), failureMessage);
	}

	public void markStepDone(String stepId, ResultType type, String failureMessage) {
		super.markStepDone(stepId, type, failureMessage);
		if (stepId.equals("" + this.counter)) {
			if (type == ResultType.ERROR) {
				if (!this.stopOnError){
					report.addResult(SahiScript.stripSahiFromFunctionNames(step), type, debugInfo, failureMessage);
				}
			} else {
				report.addResult(SahiScript.stripSahiFromFunctionNames(step), type, debugInfo, failureMessage);
			} 
				
		}
	}	

	public String getScriptFilePath() {
		return getScript().getFilePath();
	}	

	public void setReporter() {
		if (suite != null) {
			report = new Report(getScriptName(), suite.getListReporter());
		} else {
			report = new Report(getScriptName(), new HtmlReporter());
		}
	}

	public String getScriptName() {
		return script.getScriptName();
	}

	public SahiScript getScript() {
		return script;
	}

	public int getThreadNo(){
		if (launcher == null) return 0; 
		return launcher.getThreadNo();
	}
	
	public Report getReport() {
		return report;
	}

	public void log(String message, String debugInfo, String resultType){
		report.addResult(message, ResultType.getType(resultType), debugInfo, "");
	}

	public void logException(String message, String debugInfo, boolean isError){
		if (isError) setHasError();
		report.addResult("Logging exception: ", isError ? ResultType.ERROR : ResultType.CUSTOM, debugInfo, message);
	}

	public void setHasError(){
		hasErrors = true;
		setStatus(Status.ERROR);
	}	
	
	public void logExceptionWithLineNumber(String message, int lineNumber, boolean isFailure){
		logException(message, script.getDebugInfo(lineNumber), isFailure);
	}
	
	public void stop() {
		super.stop();
		try{
			report.stopTimer();
			report.generateTestReport();
		}catch(Exception e){
			e.printStackTrace();
		}
		if (suite != null){
			suite.notifyComplete(launcher);
		}
	}

	public String eval(String js) {
		Context cx = Context.enter();
		Object result;
		try{
			result = cx.evaluateString(scope, "_sahi.toJSON(" + js + ")", "RhinoScriptRunner.eval", 1, null);
		}catch(Exception e){
			result = e.getLocalizedMessage();
		}
//		System.out.println("<<< >>> " + result.toString());
		Context.exit();
		return result.toString();
	}

}
