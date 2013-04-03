package com.redhat.qe.rest;

import java.io.StringReader;
import java.util.Collection;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.calgb.test.performance.BuildPostException;
import org.calgb.test.performance.PostRequestFactory;
import org.calgb.test.performance.ProcessResponseBodyException;
import org.calgb.test.performance.RequestException;
import org.calgb.test.performance.SimplifiedResponse;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.Cpu;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.model.DatacenterList;
import com.redhat.qe.model.Version;
import com.redhat.qe.utils.MyMarshaller;

public class DatacenteTest extends TestBase{
	@Test
	public void list() throws ProcessResponseBodyException, RequestException, JAXBException, XMLStreamException, FactoryConfigurationError, BuildPostException{
		SimplifiedResponse response = getSession().sendTransaction(new HttpGet("/api/datacenters"));
		Unmarshaller m = getContext().createUnmarshaller();
		DatacenterList list2 =  (DatacenterList) (m.unmarshal(new StringReader(response.getBody())));
		System.out.println(list2.getDatacenters().get(0).getId());
		 Datacenter defaultDatacenter = Collections2.filter(list2.getDatacenters(), new Predicate<Datacenter>() {
			public boolean apply(Datacenter dc){
				return dc.getName().equals("Default");
			}
		}).iterator().next();
		 Cluster c = new Cluster();
			c.setName("sdfhi");
			c.setDatacenter(defaultDatacenter);
			c.setGlusterService(true);
			c.setVirtService(false);
			c.setVersion(new Version(){{ setMajor(3); setMinor(2);}});
			c.setCpu(new Cpu(){{setId("Intel SandyBridge Family");}});
			String xml = MyMarshaller.marshall(getContext(), c);
			System.out.println(xml);
			HttpPost post = new PostRequestFactory().buildPost("/api/clusters", xml);
			post.addHeader("Content-Type", "application/xml");
			post.addHeader("Accept", "application/xml");
			SimplifiedResponse createCluster = getSession().sendTransaction(post);
			Cluster cluster = (Cluster) m.unmarshal(new StringReader(createCluster.getBody()));
			System.out.println(cluster.getId());
			
		
	}

}
