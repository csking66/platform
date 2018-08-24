package com.core.config.session;

import org.apache.shiro.util.StringUtils;
import org.springframework.session.Session;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.CookieSerializer.CookieValue;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.session.web.http.MultiHttpSessionStrategy;
import org.springframework.util.Assert;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 同一个浏览器配置多个session
 * spring session 配置多个session  
 * MultiHttpSessionStrategy 定义session 的基本用户。和重构HttpServletRequest、HttpServletResponse 包装方法
 * HttpSessionManager 维护着一组 session Id 别名映射，以支持多个同时会话管理
 *
 *和CookieHttpSessionStrategy 类功能一样的。
 *
 */
public final class CookieHeaderHttpSessionStrategy implements MultiHttpSessionStrategy, HttpSessionManager {

	private static final String SESSION_IDS_WRITTEN_ATTR = CookieHeaderHttpSessionStrategy.class.getName().concat(
			".SESSIONS_WRITTEN_ATTR");

	static final String DEFAULT_ALIAS = "0";

	static final String DEFAULT_SESSION_ALIAS_PARAM_NAME = "_s";

	// session alias 的正则表达式
	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[\\w-]{1,50}$");

	private String sessionParam = DEFAULT_SESSION_ALIAS_PARAM_NAME;

	private CookieSerializer cookieSerializer = new DefaultCookieSerializer();

	private String headerName = "x-auth-token";

	public String getRequestedSessionId(HttpServletRequest request) {

		return getSessionIdThenToken(request);
	}

	/**
	 * 先取sessionId，如果没有，再取header中的token
	 * 
	 * @param request
	 * @return
	 */
	private String getSessionIdThenToken(HttpServletRequest request) {

		String sessionId = null;
		Map<String, String> sessionIds = getSessionIds(request);
		String sessionAlias = getCurrentSessionAlias(request);
		sessionId = sessionIds.get(sessionAlias);
		if (!StringUtils.hasText(sessionId)) {
			sessionId = request.getHeader(this.headerName);
		}
		return sessionId;
	}

	/**
	 * 先取token，如果没有token，再取request中的session id
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getTokenThenSessionId(HttpServletRequest request) {

		String sessionId = null;
		if (StringUtils.hasText(request.getHeader(this.headerName))) {
			sessionId = request.getHeader(this.headerName);
		}
		else {
			Map<String, String> sessionIds = getSessionIds(request);
			String sessionAlias = getCurrentSessionAlias(request);
			sessionId = sessionIds.get(sessionAlias);
		}
		return sessionId;
	}

	public String getCurrentSessionAlias(HttpServletRequest request) {

		if (this.sessionParam == null) {
			return DEFAULT_ALIAS;
		}
		String u = request.getParameter(this.sessionParam);
		if (u == null) {
			return DEFAULT_ALIAS;
		}
		if (!ALIAS_PATTERN.matcher(u).matches()) {
			return DEFAULT_ALIAS;
		}
		return u;
	}

	public String getNewSessionAlias(HttpServletRequest request) {

		Set<String> sessionAliases = getSessionIds(request).keySet();
		if (sessionAliases.isEmpty()) {
			return DEFAULT_ALIAS;
		}
		long lastAlias = Long.decode(DEFAULT_ALIAS);
		for (String alias : sessionAliases) {
			long selectedAlias = safeParse(alias);
			if (selectedAlias > lastAlias) {
				lastAlias = selectedAlias;
			}
		}
		return Long.toHexString(lastAlias + 1);
	}

	private long safeParse(String hex) {

		try {
			return Long.decode("0x" + hex);
		}
		catch (NumberFormatException notNumber) {
			return 0;
		}
	}

	public void onNewSession(Session session, HttpServletRequest request, HttpServletResponse response) {

		Set<String> sessionIdsWritten = getSessionIdsWritten(request);
		if (sessionIdsWritten.contains(session.getId())) {
			return;
		}
		sessionIdsWritten.add(session.getId());

		Map<String, String> sessionIds = getSessionIds(request);
		String sessionAlias = getCurrentSessionAlias(request);
		sessionIds.put(sessionAlias, session.getId());

		String cookieValue = createSessionCookieValue(sessionIds);
		this.cookieSerializer.writeCookieValue(new CookieValue(request, response, cookieValue));
		response.setHeader(this.headerName, session.getId());
	}

	@SuppressWarnings("unchecked")
	private Set<String> getSessionIdsWritten(HttpServletRequest request) {

		Set<String> sessionsWritten = (Set<String>) request.getAttribute(SESSION_IDS_WRITTEN_ATTR);
		if (sessionsWritten == null) {
			sessionsWritten = new HashSet<String>();
			request.setAttribute(SESSION_IDS_WRITTEN_ATTR, sessionsWritten);
		}
		return sessionsWritten;
	}

	private String createSessionCookieValue(Map<String, String> sessionIds) {

		if (sessionIds.isEmpty()) {
			return "";
		}
		if (sessionIds.size() == 1 && sessionIds.keySet().contains(DEFAULT_ALIAS)) {
			return sessionIds.values().iterator().next();
		}

		StringBuffer buffer = new StringBuffer();
		for (Map.Entry<String, String> entry : sessionIds.entrySet()) {
			String alias = entry.getKey();
			String id = entry.getValue();

			buffer.append(alias);
			buffer.append(" ");
			buffer.append(id);
			buffer.append(" ");
		}
		buffer.deleteCharAt(buffer.length() - 1);
		return buffer.toString();
	}

	public void onInvalidateSession(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> sessionIds = getSessionIds(request);
		String requestedAlias = getCurrentSessionAlias(request);
		sessionIds.remove(requestedAlias);

		String cookieValue = createSessionCookieValue(sessionIds);
		this.cookieSerializer.writeCookieValue(new CookieValue(request, response, cookieValue));
		response.setHeader(this.headerName, "");
	}

	/**
	 * Sets the name of the HTTP parameter that is used to specify the session alias. If the value is null, then only a
	 * single session is supported per browser.
	 *
	 * @param sessionAliasParamName the name of the HTTP parameter used to specify the session alias. If null, then ony
	 *            a single session is supported per browser.
	 */
	public void setSessionAliasParamName(String sessionAliasParamName) {

		this.sessionParam = sessionAliasParamName;
	}

	/**
	 * Sets the {@link CookieSerializer} to be used.
	 *
	 * @param cookieSerializer the cookieSerializer to set. Cannot be null.
	 */
	public void setCookieSerializer(CookieSerializer cookieSerializer) {

		Assert.notNull(cookieSerializer, "cookieSerializer cannot be null");
		this.cookieSerializer = cookieSerializer;
	}

	public CookieSerializer getCookieSerializer() {

		return cookieSerializer;
	}

	/**
	 * Sets the name of the cookie to be used.
	 * 
	 * @param cookieName the name of the cookie to be used
	 * @deprecated use {@link #setCookieSerializer(CookieSerializer)}
	 */
	@Deprecated
	public void setCookieName(String cookieName) {

		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName(cookieName);
		this.cookieSerializer = serializer;
	}

	public Map<String, String> getSessionIds(HttpServletRequest request) {

		List<String> cookieValues = this.cookieSerializer.readCookieValues(request);
		String sessionCookieValue = cookieValues.isEmpty() ? "" : cookieValues.iterator().next();
		Map<String, String> result = new LinkedHashMap<String, String>();
		StringTokenizer tokens = new StringTokenizer(sessionCookieValue, " ");
		if (tokens.countTokens() == 1) {
			result.put(DEFAULT_ALIAS, tokens.nextToken());
			return result;
		}
		while (tokens.hasMoreTokens()) {
			String alias = tokens.nextToken();
			if (!tokens.hasMoreTokens()) {
				break;
			}
			String id = tokens.nextToken();
			result.put(alias, id);
		}
		return result;
	}

	public HttpServletRequest wrapRequest(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute(HttpSessionManager.class.getName(), this);
		return request;
	}

	public HttpServletResponse wrapResponse(HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> sessionIds = getSessionIds(request);
		String sessionAlias = getCurrentSessionAlias(request);
		if (StringUtils.hasText(sessionIds.get(sessionAlias))
				&& !StringUtils.hasText(request.getHeader(this.headerName))) {
			// 如果请求只有session id，而没有token则依然返回token。
			if (response.getHeader(this.headerName) == null) {
				response.setHeader(this.headerName, sessionIds.get(sessionAlias));
			}
		}
		return new MultiSessionHttpServletResponse(response, request);
	}

	public String encodeURL(String url, String sessionAlias) {

		String encodedSessionAlias = urlEncode(sessionAlias);
		int queryStart = url.indexOf("?");
		boolean isDefaultAlias = DEFAULT_ALIAS.equals(encodedSessionAlias);
		if (queryStart < 0) {
			return isDefaultAlias ? url : url + "?" + this.sessionParam + "=" + encodedSessionAlias;
		}
		String path = url.substring(0, queryStart);
		String query = url.substring(queryStart + 1, url.length());
		String replacement = isDefaultAlias ? "" : "$1" + encodedSessionAlias;
		query = query.replaceFirst("((^|&)" + this.sessionParam + "=)([^&]+)?", replacement);
		if (!isDefaultAlias && url.endsWith(query)) {
			// no existing alias
			if (!( query.endsWith("&") || query.length() == 0 )) {
				query += "&";
			}
			query += this.sessionParam + "=" + encodedSessionAlias;
		}

		return path + "?" + query;
	}

	private String urlEncode(String value) {

		try {
			return URLEncoder.encode(value, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * The name of the header to obtain the session id from. Default is "x-auth-token".
	 *
	 * @param headerName the name of the header to obtain the session id from.
	 */
	public void setHeaderName(String headerName) {

		Assert.notNull(headerName, "headerName cannot be null");
		this.headerName = headerName;
	}

	/**
	 * A {@link CookieHttpSessionStrategy} aware {@link HttpServletResponseWrapper}.
	 */
	class MultiSessionHttpServletResponse extends HttpServletResponseWrapper {

		private final HttpServletRequest request;

		MultiSessionHttpServletResponse(HttpServletResponse response, HttpServletRequest request) {

			super(response);
			this.request = request;
		}

		@Override
		public String encodeRedirectURL(String url) {

			url = super.encodeRedirectURL(url);
			return CookieHeaderHttpSessionStrategy.this.encodeURL(url, getCurrentSessionAlias(this.request));
		}

		@Override
		public String encodeURL(String url) {

			url = super.encodeURL(url);

			String alias = getCurrentSessionAlias(this.request);
			return CookieHeaderHttpSessionStrategy.this.encodeURL(url, alias);
		}
	}
}
