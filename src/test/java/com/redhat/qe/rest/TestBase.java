package com.redhat.qe.rest;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.calgb.test.performance.HttpSession;
import org.calgb.test.performance.HttpSession.HttpProtocol;
import org.calgb.test.performance.UseSslException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.model.DatacenterList;

public class TestBase {
	private static JAXBContext context;
	private HttpSession session;

	@BeforeClass
	public static void setupClass() throws JAXBException{
		context = JAXBContext.newInstance(Cluster.class,Datacenter.class,ArrayList.class,DatacenterList.class);
	}
	@Before
	public void setup() throws UseSslException{ 
		session = new HttpSession("rhsc-qa9", 443, HttpProtocol.HTTPS);
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
