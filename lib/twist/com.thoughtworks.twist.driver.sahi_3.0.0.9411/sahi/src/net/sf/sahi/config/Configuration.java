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
package net.sf.sahi.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import net.sf.sahi.util.FileUtils;
import net.sf.sahi.util.Utils;

/**
 * Configuration resolves all properties and paths required by Sahi.<br/>
 * Invoking Configuration.init is mandatory before invoking Proxy.start
 * 
 */
public class Configuration {

//	static Properties properties = new Properties();
	
	static Properties userProperties; // = new Properties(properties);

	private static final String HTDOCS_ROOT = "../htdocs/";

	private static final String SAHI_PROPERTIES = "../config/sahi.properties";
	
	private static final String SAHI_USER_PROPERTIES = "config/userdata.properties";

	private static final String LOG_PROPERITES = "../config/log.properties";

	private static final String TMP_DOWNLOAD_DIR = "temp/download";

	public static final String PLAYBACK_LOG_ROOT = "playback";

	private static String pathToBin;
	
	private static String userDataDir = null;

	private static String[] exclusionList;

	/**
	 * Initializes Sahi's properties and relative paths.<br/>
	 * This is required before invoking Proxy.start()<br/>
	 * This assumes the current folder as Sahi's basePath. <br/>
	 * Same as init(".")<br/>
	 * A call to <code>init</code> or <code>initJava</code>  is required before invoking Proxy.start()
	 * 
	 */
	public static void init(){
		init(".", "userdata");
	}
	/**
	 * Initializes Sahi's properties and relative paths.<br/>
	 * A call to <code>init</code> or <code>initJava</code>  is required before invoking Proxy.start()
	 * 
	 * @param basePath String basePath to folder where sahi is located
	 * @param userDataDirectory String path to user data directory
	 */
	public static void init(String basePath, String userDataDirectory){
		try {
			pathToBin = Utils.concatPaths(basePath, "bin");
			userDataDir = userDataDirectory;
			
			String propsPath = Utils.concatPaths(pathToBin, SAHI_PROPERTIES);
			System.out.println("Sahi properties file = " + propsPath);
			
			String userPropsPath = Utils.concatPaths(userDataDir, SAHI_USER_PROPERTIES);
			System.out.println("Sahi user properties file = " + userPropsPath);
			
			Properties properties = new Properties();
			loadProperties(propsPath, properties);
			userProperties = new Properties(properties);
			loadProperties(userPropsPath, userProperties);
			System.setProperty("java.util.logging.config.file", LOG_PROPERITES);
			createFolders(new File(getPlayBackLogsRoot()));
			createFolders(new File(getCertsPath()));
			createFolders(new File(tempDownloadDir()));
			copyProfiles();
			Utils.BUFFER_SIZE = getBufferSize();
			System.setProperty("java.util.logging.config.file", getLogPropertyFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static String getLogPropertyFile() {
		return Utils.concatPaths(userDataDir,  "/config/log.properties");
	}
	
	private static int getBufferSize() {
		try {
			return Integer.parseInt(userProperties.getProperty("io.buffer_size"));
		} catch (Exception e) {
			return 4096;
		}
	}
	public static void loadProperties(String sahiProperties, Properties props) throws FileNotFoundException, IOException {
		FileInputStream inStream = new FileInputStream(sahiProperties);
		props.load(inStream);
		inStream.close();
	}

	/**
	 * Initializes Sahi's properties and relative paths and additionally sets the Controller to Java mode.<br/>
	 * This is required before invoking Proxy.start()<br/>
	 * This assumes the current folder as Sahi's basePath. <br/>
	 * Same as initJava(".")<br/>
	 * A call to <code>init</code> or <code>initJava</code>  is required before invoking Proxy.start()
	 * 
	 */
	public static void initJava(){
		initJava(".", "userdata");
	}
	/**
	 * Initializes Sahi's properties and relative paths and additionally sets the Controller to Java mode.<br/>
	 * A call to <code>init</code> or <code>initJava</code>  is required before invoking Proxy.start()
	 * 
	 * @param basePath String basePath to folder where sahi is located
	 * @param userDataDirectory String path to user data directory
	 */
	public static void initJava(String basePath, String userDataDirectory){
		init(basePath, userDataDirectory);
		setControllerMode("java");		
	}	
	private static void copyProfiles() throws IOException {
		File templateDir = new File(Utils.concatPaths(pathToBin, userProperties.getProperty("ff.profiles.template")));
		File profileDir = new File(Utils.concatPaths(userDataDir, userProperties.getProperty("ff.profiles.dir")));
		profileDir.mkdirs();
		String prefix = userProperties.getProperty("ff.profiles.prefix");
		int maxProfiles = Integer.parseInt(userProperties.getProperty(
				"ff.profiles.max_number", "10"));
		for (int i = 0; i < maxProfiles; i++) {
			File profileN = new File(Utils.concatPaths(profileDir
					.getCanonicalPath(), prefix + i));
			if (profileN.exists()) {
				continue;
			}
			System.out.println("Copying profile to " + profileN);
			FileUtils.copyDir(templateDir, profileN);
		}
	}

	public static void createFolders(final File file) {
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	public static int getPort() {
		try {
			return Integer.parseInt(userProperties.getProperty("proxy.port"));
		} catch (Exception e) {
			return 9999;
		}
	}

	public static Logger getLogger(final String name) {
		return Logger.getLogger(name);
	}

	public static String getLogsRoot() {
		String fileName = Utils.concatPaths(userDataDir, userProperties.getProperty("logs.dir", "logs"));
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdirs();
		}
		return fileName;
	}

	public static String getSSLPassword() {
		return userProperties.getProperty("ssl.password");
	}

	public static String[] getScriptRoots() {
		String[] propertyArray = getPropertyArray("scripts.dir", userProperties, "scripts");
		for (int i = 0; i < propertyArray.length; i++) {
			propertyArray[i] = Utils.getAbsolutePath(Utils.concatPaths(userDataDir, propertyArray[i]))
									+ System.getProperty("file.separator");
		}
		return propertyArray;
	}

	public static String[] getScriptExtensions() {
		return getPropertyArray("script.extension", userProperties, "sah;sahi;inc");
	}

	private static String[] getPropertyArray(final String key, Properties props, String defaultValue) {
		String property = props.getProperty(key, defaultValue);
		String[] tokens = property.split(";");
		for (int i = 0; i < tokens.length; i++) {
			tokens[i] = tokens[i].trim();
		}
		return tokens;
	}

	public static String getPlayBackLogsRoot() {
		String fileName = Utils.concatPaths(getLogsRoot(), PLAYBACK_LOG_ROOT);
		File file = new File(fileName);
		if (!file.exists()) {
			file.mkdirs();
		}
		return fileName;
	}

	public static String getHtdocsRoot() {
		return Utils.concatPaths(pathToBin, HTDOCS_ROOT) + "/";
	}

	public static String getPlaybackLogCSSFileName(final boolean addHtdocsRoot) {
		final String path = "spr/css/playback_log_format.css";
		return addHtdocsRoot ? Utils.concatPaths(getHtdocsRoot(), path) : path;
	}

	public static String getConsolidatedLogCSSFileName(
			final boolean addHtdocsRoot) {
		final String path = "spr/css/consolidated_log_format.css";
		return addHtdocsRoot ? Utils.concatPaths(getHtdocsRoot(), path) : path;
	}

	public static String getRhinoLibJS() {
		return new String(Utils.readFile(Utils.concatPaths(getHtdocsRoot(),
				"spr/lib.js")));
	}

	public static boolean isKeepAliveEnabled() {
		return (enableKeepAlive > 0)
				|| (enableKeepAlive <= 0 && "true".equalsIgnoreCase(userProperties
						.getProperty("http.keep_alive")));
	}

	public static int getTimeBetweenTestsInSuite() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("suite.time_between_tests"));
		} catch (Exception e) {
			return 1000;
		}
	}

	public static int getMaxInactiveTimeForScript() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("suite.max_inactive_time_for_script")) * 1000;
		} catch (Exception e) {
			return 20000;
		}
	}

	public static void createScriptsDirIfNeeded() {
		String[] scriptRoots = Configuration.getScriptRoots();
		for (int i = 0; i < scriptRoots.length; i++) {
			String scriptRoot = scriptRoots[i];
			File file = new File(scriptRoot);
			file.mkdirs();
		}
	}

	public static String getHotKey() {
		String hotkey = userProperties.getProperty("controller.hotkey");
		if ("SHIFT".equals(hotkey) || "ALT".equals(hotkey)
				|| "CTRL".equals(hotkey) || "META".equals(hotkey)) {
			return hotkey;
		}
		return "ALT";
	}

	public static String appendLogsRoot(final String fileName) {
		return Utils.concatPaths(getPlayBackLogsRoot(), fileName);
	}

	public static boolean isDevMode() {
		return "true".equals(System.getProperty("sahi.mode.dev"));
	}

	public static boolean autoCreateSSLCertificates() {
		return "true"
				.equals(userProperties.getProperty("ssl.auto_create_keystore"));
	}
	public static boolean isStrictVisibilityCheckEnabled() {
		return "true".equals(userProperties.getProperty("element.visibility_check.strict"));
	}
	public static String xhrReadyStatesToWaitFor() {
		return userProperties.getProperty("xhr.wait_ready_states", "2");
	}
	
	public static String getCertsPath() {
		return Utils.concatPaths(userDataDir, userProperties.getProperty("certs.dir", "certs"));
	}

	public static String getConfigPath() {
		return Utils.concatPaths(pathToBin, "../config/");
	}

	public static String getKeytoolPath() {
		return userProperties.getProperty("keytool.path", "keytool");
	}

	public static int getTimeBetweenSteps() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("script.time_between_steps"));
		} catch (Exception e) {
			return 100;
		}
	}

	public static void setTimeBetweenSteps(int speed) {
		userProperties.setProperty("script.time_between_steps", ""+speed);
	}
	
	public static int getTimeBetweenStepsOnError() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("script.time_between_steps_on_error"));
		} catch (Exception e) {
			return 1000;
		}
	}

	public static int getMaxReAttemptsOnError() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("script.max_reattempts_on_error"));
		} catch (Exception e) {
			return 10;
		}
	}

	public static int getMaxCyclesForPageLoad() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("script.max_cycles_for_page_load"));
		} catch (Exception e) {
			return 10;
		}
	}

	public static String[] getExclusionList() {
		if (exclusionList == null){
			File file = new File(Utils.concatPaths(userDataDir, "config/exclude_inject.txt"));
			exclusionList = (file.exists()) ? getNonBlankLines(Utils.readCachedFile(file)) : new String[0];
		}
		return exclusionList;
	}

	static int enableKeepAlive = 0;

	private static String overriddenControllerMode;

	private static String[] downloadURLList;

	public static void enableKeepAlive() {
		enableKeepAlive++;
	}

	public static void disableKeepAlive() {
		enableKeepAlive--;
	}

	public static int getRemoteSocketTimeout() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("proxy.remote_socket_timeout"));
		} catch (Exception e) {
			return 120000;
		}
	}

	public static boolean modifyActiveX() {
		return "true".equals(userProperties.getProperty("response.modify_activex"));
	}

	public static boolean spanVariablesAcrossSuite() {
		return "true".equals(userProperties.getProperty("suite.global_variables"));
	}

	public static int getMaxReAttemptsOnNotMyWindowError() {
		try {
			return Integer
					.parseInt(userProperties
							.getProperty("script.max_reattempts_on_window_not_found_error"));
		} catch (Exception e) {
			return 30;
		}
	}

	public static Pattern getDownloadContentTypesRegExp() {
		String[] downloadables = getNonBlankLines(Utils.readCachedFile(Utils.concatPaths(userDataDir, "config/download_contenttypes.txt")));
		if (downloadables.length != 0) {
			try {
				StringBuilder sb = new StringBuilder("(?:.*");
				for (int i = 0; i < downloadables.length; i++) {
					sb.append(downloadables[i]);
					if (i != downloadables.length-1){
						sb.append(".*)|(?:");
					}
				}
				sb.append(".*)");
				return Pattern.compile(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Pattern.compile("");
	}

	public static String[] getDownloadURLList() {
		if (downloadURLList == null) {
			downloadURLList = getNonBlankLines(Utils.readCachedFileIfExists(Utils.concatPaths(userDataDir, "config/download_urls.txt")));
		}
		return downloadURLList;
	}

	protected static String[] getNonBlankLines(byte[] b) {
		return getNonBlankLines(new String(b));
	}

	protected static String[] getNonBlankLines(String s) {
		s = s.trim().replaceAll("\\\r", "");
		String[] tokens = s.split("\n");
		ArrayList<String> l = new ArrayList<String>();
		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i].trim();
			if (!token.equals("")) {
				l.add(token);
			}
		}
		return (String[]) l.toArray(new String[] {});
	}

	public static String tempDownloadDir() {
		return Utils.concatPaths(userDataDir, TMP_DOWNLOAD_DIR);
	}

	public static String getPIDListCommand() {
		return userProperties.getProperty("processhelper.pid_list_cmd", "");
	}

	public static String getPIDKillCommand() {
		return userProperties.getProperty("processhelper.pid_kill_cmd", "");
	}

	public static int getPIDListColumnNo() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("processhelper.pid_list_pid_column_no"));
		} catch (Exception e) {
			return 2;
		}
	}

	public static int getScriptMaxIdleTime() {
		try {
			return Integer.parseInt(userProperties
					.getProperty("script.max_idle_time"));
		} catch (Exception e) {
			return 1000;
		}
	}

	public static void setProxyProperties() {
		Properties systemProperties = System.getProperties();
		if (isHttpProxyEnabled()) {
			systemProperties.setProperty("http.proxyHost", getHttpProxyHost());
			systemProperties.setProperty("http.proxyPort", ""+getHttpProxyPort());
			systemProperties.setProperty("http.nonProxyHosts", ""+getHttpNonProxyHosts());
		}
		if (isHttpsProxyEnabled()) {
			systemProperties.setProperty("https.proxyHost", getHttpsProxyHost());
			systemProperties.setProperty("https.proxyPort", ""+getHttpsProxyPort());
			systemProperties.setProperty("http.nonProxyHosts", ""+getHttpsNonProxyHosts());
			systemProperties.setProperty("https.nonProxyHosts", ""+getHttpsNonProxyHosts());//? Is this used?
		}
	}

	public static boolean isHttpProxyEnabled() {
		return "true".equals(userProperties.get("ext.http.proxy.enable"));
	}

	public static boolean isHttpsProxyEnabled() {
		return "true".equals(userProperties.get("ext.https.proxy.enable"));
	}

	public static String getHttpProxyHost() {
		return (String) userProperties.get("ext.http.proxy.host");
	}

	public static String getHttpProxyPort() {
		return (String)userProperties.get("ext.http.proxy.port");
	}

	public static String getHttpNonProxyHosts() {
		return (String) userProperties.get("ext.http.both.proxy.bypass_hosts");
	}

	public static String getHttpProxyAuthName() {
		return (String) userProperties.get("ext.http.proxy.auth.name");
	}
	
	public static String getHttpProxyAuthPassword() {
		return (String) userProperties.get("ext.http.proxy.auth.password");
	}
	
	

	public static String getHttpsProxyPort() {
		return (String)userProperties.get("ext.https.proxy.port");
	}

	public static String getHttpsProxyHost() {
		return (String) userProperties.get("ext.https.proxy.host");
	}

	static String getHttpsNonProxyHosts() {
		return (String) userProperties.get("ext.http.both.proxy.bypass_hosts");
	}

	public static String getHttpsProxyAuthName() {
		return (String) userProperties.get("ext.https.proxy.auth.name");
	}
	
	public static String getHttpsProxyAuthPassword() {
		return (String) userProperties.get("ext.https.proxy.auth.password");
	}
	
	public static boolean isHttpProxyAuthEnabled() {
		return "true".equals(userProperties.get("ext.http.proxy.auth.enable"));
	}

	public static boolean isHttpsProxyAuthEnabled() {
		return "true".equals(userProperties.get("ext.https.proxy.auth.enable"));
	}

	public static boolean isTrafficLoggingOn() {
		return "true".equals(userProperties.getProperty("debug.traffic.log"));
	}

	public static void main(String args[]){
		String[] scriptRoots = Configuration.getScriptRoots();
		System.out.println(scriptRoots[0]);
	}

	public static boolean downloadIfContentDispositionIsAttachment() {
		return "true".equals(userProperties.getProperty("download.download_if_contentdisposition_is_attachment"));
	}

	public static String getMimeTypesMappingFile() {
		return Utils.concatPaths(pathToBin, "../config/mime-types.mapping");
	}

	public static String getAbsolutePath(String relPath) {
		return Utils.concatPaths(pathToBin, relPath);
	}

	public static String getAbsoluteUserPath(String relPath) {
		return Utils.concatPaths(userDataDir, relPath);
	}	
	
	
	public static int sampleLength() {
		try {
			return Integer.parseInt(userProperties.getProperty("response.sample_length"));
		} catch (Exception e) {
			return 500;
		}
	}

	public static String getSSLClientCertPath() {
		return (String) userProperties.get("ssl.client.cert.path");
	}

	public static String getSSLClientCertPassword() {
		return (String) userProperties.get("ssl.client.cert.password");
	}

	public static String getSSLClientKeyStoreType() {
		return (String) userProperties.getProperty("ssl.client.keystore.type", "JKS");
	}
	public static String getControllerMode() {
		if (overriddenControllerMode == null){
			return (String) userProperties.getProperty("controller.mode", "sahi");
		}
		return overriddenControllerMode;
	}
	/**
	 * Sets the Controller mode. <br/> 
	 * Currently valid values are "sahi" and "java".<br/>
	 * Set this to "java" to use the java Controller instead of Sahi's default Controller. 
	 * 
	 * @param mode "java" or "sahi"
	 */
	public static void setControllerMode(String mode) {
		overriddenControllerMode = mode;
	}
	public static String getSSLAlgorithm() {
		return (String) userProperties.getProperty("ssl.algorithm", "SunX509").trim();
	}
	public static String getInjectTop() {
		return Configuration.getAbsolutePath("../config/inject_top.txt");
	}
	public static String getInjectBottom() {
		return Configuration.getAbsolutePath("../config/inject_bottom.txt");
	}
	public static String getSSLCommandFile() {
		return Utils.concatPaths(getConfigPath(), "ssl.txt");
	}
	public static String getJiraPropertyPath() {
		return Utils.concatPaths(userDataDir, "config/jira.properties");
	}
	public static String getUserDataDir(){
		return Utils.getAbsolutePath(userDataDir);
	}
	
	public static String getOSPropertiesFile() throws Exception {
		return Utils.concatPaths(getConfigPath(), "os.properties");
	}
	public static String getVersion() {
		String path = Utils.concatPaths(getConfigPath(), "version.txt");
		return new String(Utils.readCachedFile(path));
	}
	public static int getRhinoOptimizationLevel() {
		try {
			return Integer.parseInt(userProperties.getProperty("rhino.optimization_level"));
		} catch (Exception e) {
			return 0;
		}
	}
	public static int getStabilityIndex() {
		try {
			int i = Integer.parseInt(userProperties.getProperty("script.stability_index"));
			if (i < 1) i = 1;
			return i;
		} catch (Exception e) {
			return 5;
		}
	}
	public static boolean getEscapeUnicode() {
		return "true".equals(userProperties.getProperty("script.escape_unicode"));
	}
	public static boolean addJSModifierFilter() {
		return !"false".equals(userProperties.getProperty("filters.addJSModifierFilter"));
	}
	public static boolean addHTMLModifierFilter() {
		return !"false".equals(userProperties.getProperty("filters.addHTMLModifierFilter"));
	}
	public static boolean addCharacterFilter() {
		return !"false".equals(userProperties.getProperty("filters.addCharacterFilter"));
	}
		
}