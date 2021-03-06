package com.redhat.qe.repository.rest.context;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.redhat.qe.helpers.jaxb.MyMarshaller;
import com.redhat.qe.model.jaxb.DeletionBrickWrapper;
import com.redhat.qe.model.jaxb.DeletionBrickWrapperList;
import com.redhat.qe.model.jaxb.MigrateBrickAction;
import com.redhat.qe.model.jaxb.MigrateBrickWrapper;
import com.redhat.qe.model.jaxb.MigrateBrickWrapperList;

public class DeleteBrickJaxbContext {
	//singleton
	private static JAXBContext context;

	public static JAXBContext getContext() throws JAXBException {
		if (context == null) {
			context = JAXBContext.newInstance(DeletionBrickWrapper.class, DeletionBrickWrapperList.class);
		}
		return context;

	}

	public Marshaller getMarshaller(){
		try {
			return getContext().createMarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	public String marshal(Object obj){
		try {
			return MyMarshaller.marshall(getContext(), obj);
		} catch (JAXBException e) {
			throw new RuntimeException("couldn't marshall obj", e);
		}
	}
	
	public Unmarshaller getUnmarshaller(){
		try {
			return getContext().createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	public Object unmarshal(String body) {
		try {
			return getContext().createUnmarshaller().unmarshal(new StringReader(body));
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
