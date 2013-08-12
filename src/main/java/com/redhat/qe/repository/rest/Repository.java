package com.redhat.qe.repository.rest;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.client.methods.HttpRequestBase;
import org.calgb.test.performance.HttpSession;
import org.calgb.test.performance.ProcessResponseBodyException;
import org.calgb.test.performance.RequestException;

import com.redhat.qe.utils.MyMarshaller;

public class Repository {
	public HttpSession session;
	private Unmarshaller unmarshaller;

	public Repository(HttpSession session) {
		createUnmarshaller();
		this.session = session;
	}

	/**
	 * 
	 */
	private void createUnmarshaller() {
		try {
			this.unmarshaller = JaxbContext.getContext().createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException("unable to create unmarshaller");
		}
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session
	 *            the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the unmarshaller
	 */
	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public ResponseWrapper sendTransaction(HttpRequestBase request) {
		try {
			return new ResponseWrapper(getSession().sendTransaction(request));
		} catch (ProcessResponseBodyException e) {
			throw new RuntimeException(e);
		} catch (RequestException e) {
			throw new RuntimeException(e);
		}
	}

	public Object unmarshal(String raw) {
		try {
			return getUnmarshaller().unmarshal(new StringReader(raw));
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	public String marshall(Object object) {
		try {
			return MyMarshaller.marshall(JaxbContext.getContext(), object);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

}
