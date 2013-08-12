package com.redhat.qe.repository.rest;

import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;

import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.ClusterList;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.model.DatacenterList;

public class JaxbContext {
	//singleton
	private static JAXBContext context;

	public static JAXBContext getContext() throws JAXBException{
		if(context == null){
			context = JAXBContext.newInstance(Cluster.class,Datacenter.class,ArrayList.class,DatacenterList.class, ClusterList.class);
		}
		return context;

	}

}
