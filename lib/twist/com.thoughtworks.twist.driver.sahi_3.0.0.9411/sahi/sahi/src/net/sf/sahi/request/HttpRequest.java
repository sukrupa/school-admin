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
package net.sf.sahi.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.sahi.StreamHandler;
import net.sf.sahi.config.Configuration;
import net.sf.sahi.session.Session;
import net.sf.sahi.util.Utils;

/**
 * User: nraman Date: May 13, 2005 Time: 10:01:13 PM
 */
public class HttpRequest extends StreamHandler {

	private String host;

	private int port;

	private String uri;

	private String queryString = "";

	private Map<String, String> params = new HashMap<String, String>();

	private Map<String, String> cookies = null;

	private static final Logger logger = Logger.getLogger("net.sf.sahi.request.HttpRequest");

	private boolean isSSLSocket;

	private boolean isAjax;

	private String fileExtension;

	private String hostWithPort;

	private String fileName;

	private Session session;

	private String sahiCookie;

	HttpRequest() {
	}

	public HttpRequest(final InputStream in) throws IOException {
		this(in, false);
	}

	public HttpRequest(final InputStream in, final boolean isSSLSocket) 
			throws IOException {
		this.isSSLSocket = isSSLSocket;
		populateHeaders(in, true);
		isAjax = hasHeader("sahi-isxhr") || isAjaxHeaderPresent();
		if (isPost()) {
			populateData(in);
		}
		if (isPost() || isGet() || isConnect()) {
			setHostAndPort();
			setUri();
			setQueryString();
		}
		handleSahiCookie();
	}

	private boolean isAjaxHeaderPresent() {
		String xReqWith = getLastSetValueOfHeader("X-Requested-With");
		return "XMLHttpRequest".equals(xReqWith);
	}

	public String host() {
		return host;
	}

	public int port() {
		return port;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public boolean isPost() {
		return "post".equalsIgnoreCase(method());
	}

	public boolean isGet() {
		return "get".equalsIgnoreCase(method());
	}

	public boolean isConnect() {
		return "connect".equalsIgnoreCase(method());
	}

	public boolean isSSL() {
		return isSSLSocket || isConnect();
	}

	public String method() {
		if (firstLine() == null) {
			return null;
		}
		return firstLine().substring(0, firstLine().indexOf(" "));
	}

	void setUri() {
		String withHost = firstLine().substring(firstLine().indexOf(" "),
				firstLine().lastIndexOf(" ")).trim();
		uri = stripHostName(withHost, host, isSSL());
	}

	String stripHostName(final String withHost, final String hostName,
			final boolean ssl) {
		String stripped = withHost;
		if (withHost.startsWith("http://") || withHost.startsWith("https://")) {
			int indexOfSlash = withHost
					.indexOf("/", withHost.indexOf(hostName));
			stripped = withHost.substring(indexOfSlash);
		}
		return stripped;
	}

	public String uri() {
		return uri;
	}

	public String protocol() {
		return firstLine().substring(firstLine().lastIndexOf(" "));
	}

	private void setHostAndPort() {
		hostWithPort = getLastSetValueOfHeader("Host");
		host = hostWithPort;
		port = 80;
		if (isSSL()) {
			port = 443;
		}
		int indexOfColon = hostWithPort.indexOf(":");
		if (indexOfColon != -1) {
			host = hostWithPort.substring(0, indexOfColon);
			try {
				port = Integer.parseInt(hostWithPort
						.substring(indexOfColon + 1).trim());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		host = Utils.stripChildSessionId(host);
	}

	private void setQueryString() {
		if (uri == null) {
			return;
		}
		int qIx = uri.indexOf("?");
		String uriWithoutQueryString = uri;
		if (qIx != -1 && qIx + 1 < uri.length()) {
			uriWithoutQueryString = uri.substring(0, qIx);
			queryString = uri.substring(qIx + 1);
		}

		fileExtension = "";
		int dotIx = uriWithoutQueryString.indexOf(".");
		if (dotIx != -1) {
			fileExtension = uriWithoutQueryString.substring(dotIx + 1);
		}
		int lastSlashIx = uriWithoutQueryString.lastIndexOf("/");
		if (lastSlashIx != -1) {
			if (lastSlashIx + 1 < uriWithoutQueryString.length()) {
				fileName = uriWithoutQueryString.substring(lastSlashIx + 1);
			} else {
				fileName = "no_filename";
			}
		}
	}

	private void setGetParameters() {
		try{
		String str = isGet() ? queryString() : new String(data(), "UTF-8");
		str = str.replaceAll("__SahiAmpersandSahi__", "&");
		StringTokenizer tokenizer = new StringTokenizer(str, "&");
		while (tokenizer.hasMoreTokens()) {
			String keyVal = tokenizer.nextToken();
			int eqIx = keyVal.indexOf('=');
			if (eqIx != -1) {
				String key = keyVal.substring(0, eqIx);
				String value = "";
				if (eqIx + 1 <= keyVal.length()) {
					value = keyVal.substring(eqIx + 1);
				}
				try {
					params.put(key, URLDecoder.decode(value, "UTF8"));
				} catch (Exception e) {
					params.put(key, value);
				}
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String queryString() {
		return queryString;
	}

	public String getParameter(String key) {
		if (params.size() == 0) {
			setGetParameters();
		}
		return (String) params.get(key);
	}

	private void setCookies() {
		cookies = new LinkedHashMap<String, String>();
		String cookieString = getLastSetValueOfHeader("Cookie");
		if (cookieString == null) {
			return;
		}
		StringTokenizer tokenizer = new StringTokenizer(cookieString, ";");
		while (tokenizer.hasMoreTokens()) {
			String keyVal = tokenizer.nextToken();
			int eqIx = keyVal.indexOf('=');
			if (eqIx != -1) {
				String key = keyVal.substring(0, eqIx).trim();
				String value = "";
				if (eqIx + 1 <= keyVal.length()) {
					value = keyVal.substring(eqIx + 1).trim();
				}
				cookies.put(key, value);
			}
		}
	}

	public String getCookie(String key) {
		if (cookies == null) {
			setCookies();
		}
		return (String) cookies.get(key);
	}

	String rebuildCookies() {
		return rebuildCookies(cookies);
	}

	static String rebuildCookies(final Map<String, String> cookies2) {
		StringBuilder sb = new StringBuilder();
		if (cookies2.size() == 0) {
			return "";
		}
		Iterator<String> keys = cookies2.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String value = (String) cookies2.get(key);
			sb.append(" ").append(key).append("=").append(value).append(";");
		}
		String cookieStr = sb.toString().trim();
		if (cookieStr.endsWith(";")) {
			cookieStr = cookieStr.substring(0, cookieStr.length() - 1);
		}
		return cookieStr;
	}

	public Map<String, String> cookies() {
		if (cookies == null) {
			setCookies();
		}
		return cookies;
	}

	public HttpRequest modifyForFetch() {
		if (logger.isLoggable(Level.FINEST)){
			logger.finest("REQUEST HEADERS BEFORE MODIFICATION");
			logger.finest(new String(rawHeaders()));
		}
		removeHeader("Proxy-Connection");
		removeHeader("Accept-Encoding");
		addHeader("Accept-Encoding", "gzip");
		removeHeader("Keep-Alive");
		removeHeader("sahi-isxhr");
//		Not sure whether Authorization and Proxy-Authorization should be removed.
		removeHeader("Authorization");
		removeHeader("Proxy-Authorization");
//		cookies().remove("sahisid");
//		setHeader("Cookie", rebuildCookies());
		resetRawHeaders();
		if (logger.isLoggable(Level.FINEST)){
			logger.finest("REQUEST HEADERS AFTER MODIFICATION");
			logger.finest(firstLine());
			logger.finest("\n------------\n\nRequest Headers:\n" + headers());
		}
		return this;
	}

	public Session session() {
		boolean finest = logger.isLoggable(Level.FINEST);
		if (this.session == null){
			String sessionId;
			sessionId = getParameter("sahisid");
			if (finest){
				logger.finest("SessionId from parameter:" + sessionId);
			}
			if (Utils.isBlankOrNull(sessionId)) {
				sessionId = sahiCookie;
				if (finest){
					logger.finest("SessionId from cookie:" + sessionId);
				}
			}
			if (Utils.isBlankOrNull(sessionId)) {
				sessionId = Utils.generateId();
				if (finest){
					logger.finest("SessionId generated:" + sessionId);
				}
			}
			this.session = Session.getInstance(sessionId);
		}
		return this.session;
	}

	void handleSahiCookie() {
		String cookieString = getLastSetValueOfHeader("Cookie");
		if (Utils.isBlankOrNull(cookieString)) return;
		int ix = cookieString.indexOf("sahisid=");
		if (ix == -1) {
			sahiCookie = "";
		}else{
			int endIx = cookieString.indexOf(";", ix + 1);
			int length = cookieString.length();
			String stripped;
			if (endIx == -1) {
				sahiCookie = cookieString.substring(ix + "sahisid=".length());
				stripped = cookieString.substring(0, ix);
				
			}else{
				sahiCookie = cookieString.substring(ix + "sahisid=".length(), endIx);
				stripped = cookieString.substring(0, ix) + cookieString.substring(endIx + 1, length).trim();
			}
			setHeader("Cookie", stripped.trim());
		}
	}

	public String sahiCookie(){
		return sahiCookie;
	}
	
	public String fileExtension() {
		return fileExtension;
	}

	public String fileName() {
		return fileName;
	}

	public String url() {
		return (isSSL() ? "https" : "http") + "://" + hostWithPort
				+ (uri == null ? "" : uri);
	}

	public boolean isMultipart() {
		String contentType = getLastSetValueOfHeader("Content-Type");
		return contentType != null
				&& contentType.startsWith("multipart/form-data");
	}

	public void setSSL(boolean isSSL) {
		this.isSSLSocket = isSSL;
	}

	public boolean isIE() {
		String agent = getLastSetValueOfHeader("User-Agent");
		return (agent == null || agent.indexOf("MSIE") != -1);
	}

	public boolean isAjax(){
		return isAjax;
	}

	public String referer(){
		String header = headers().getLastHeader("referer");
		return header;
	}
	
	public boolean isExcluded() {
		if (logger.isLoggable(Level.FINER)){
			logger.finer("isAjax="+isAjax+" for "+url());
		}
		if (isAjax) {
			return true;
		}
		String url = url();
		String[] exclusionList = Configuration.getExclusionList();
		for (int i = 0; i < exclusionList.length; i++) {
			String pattern = exclusionList[i];
			if (url.matches(pattern.trim())) {
				return true;
			}
		}
		return false;
	}
}
