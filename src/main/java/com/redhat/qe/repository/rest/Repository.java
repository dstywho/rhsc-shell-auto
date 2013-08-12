package com.redhat.qe.repository.rest;

import org.calgb.test.performance.HttpSession;

public class Repository {
	public HttpSession session;

	public Repository( HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	

}
