package com.core.config.session;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.session.web.http.MultiHttpSessionStrategy;

/**

 *
 */

public final class CookieHttpSessionStrategy implements MultiHttpSessionStrategy, HttpSessionManager{

	@Override
	public String getRequestedSessionId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onNewSession(Session session, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInvalidateSession(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HttpServletRequest wrapRequest(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HttpServletResponse wrapResponse(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentSessionAlias(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getSessionIds(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String url, String sessionAlias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNewSessionAlias(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
