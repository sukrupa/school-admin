package net.sf.sahi.util;

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


public class Diagnostics implements Runnable {

	public static boolean KEYTOOL_STATUS;

	public static boolean TASKLIST_STATUS;

	public void run() {
		String keytoolCmd = Configuration.getKeytoolPath();
		String tasklistCmd = OSUtils.getPIDListCommand().replaceAll(
				"$imageName", "dummy");
		try {
			Utils.executeCommand(Utils.getCommandTokens(keytoolCmd));
			KEYTOOL_STATUS = true;
		} catch (Exception e) {
//			System.out.println("keytool command may not be correct: " + e.getMessage());
		}
		try {
			Utils.executeCommand(Utils.getCommandTokens(tasklistCmd));
			TASKLIST_STATUS = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
