package com.redhat.qe.rest;

import javax.xml.bind.JAXBContext;

import org.calgb.test.performance.HttpSession;
import org.calgb.test.performance.HttpSession.HttpProtocol;
import org.calgb.test.performance.UseSslException;
import org.junit.After;
import org.junit.Before;

import com.redhat.qe.config.RhscConfiguration;

public class TestBase {
	private static JAXBContext context;
	private HttpSession session;

	@Before
	public void setup() throws UseSslException{ 
		session = new HttpSession(RhscConfiguration.getConfiguration().getRestApi().getHostname(),443, HttpProtocol.HTTPS);
		session.useBasicAuthentication("admin@internal", "redhat");
	}
	@After
	public void teardown(){
		session.stop();
	}
	
	public HttpSession getSession(){
		return session;
	}
	public JAXBContext getContext(){
		return context;
	}


}
