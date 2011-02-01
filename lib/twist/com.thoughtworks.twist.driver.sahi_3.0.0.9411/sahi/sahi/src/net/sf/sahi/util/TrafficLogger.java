package net.sf.sahi.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import net.sf.sahi.config.Configuration;

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


public class TrafficLogger {
	static String trafficDir;
	static boolean log = false;
	static {
		String logsRoot = Configuration.getLogsRoot();
		trafficDir = logsRoot + "/traffic";
		log = Configuration.isTrafficLoggingOn();
	}
	
	private String reqFileName;
	private File threadDir;

	public TrafficLogger(String reqFileName) {
		if (!log) return;
		this.reqFileName = FileUtils.cleanFileName(reqFileName);
		this.threadDir = getThreadDir();
	}


	protected synchronized String createThreadId() {
		return Utils.getFormattedDateForFile(new Date());
	}


	public void storeRequestHeader(byte[] bytes) {
		store(bytes, "request.header");
	}

	public void storeRequestBody(byte[] bytes) {
		store(bytes, "request.body");
	}

	public void storeResponseHeader(byte[] bytes) {
		store(bytes, "response.header");
	}

	public void storeResponseBody(byte[] bytes) {
		store(bytes, "response.body" + (reqFileName==null?"":"_"+reqFileName));
	}

	private void store(byte[] bytes, String fileName) {
		if (!log) return;
		if (bytes == null) return;
		File file = new File(threadDir, fileName);
		FileOutputStream out = null;
		try {
			if (!file.exists()) file.createNewFile();
			out = new FileOutputStream(file, true);
			out.write(bytes);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File getThreadDir() {
		String threadId = createThreadId();
		File threadDir = new File((trafficDir + "/" + threadId + (reqFileName==null?"":"_"+reqFileName)));
		threadDir.mkdirs();
		return threadDir;
	}

	public static TrafficLogger getLoggerForThread(){
		return (TrafficLogger) ThreadLocalMap.get("logger");
	}


	public static void createLoggerForThread(String fileName) {
		TrafficLogger logger = new TrafficLogger(fileName);
		ThreadLocalMap.put("logger", logger);		
	}
}