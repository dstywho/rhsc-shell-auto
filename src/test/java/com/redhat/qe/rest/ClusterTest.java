package com.redhat.qe.rest;

import javax.xml.bind.JAXBException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.calgb.test.performance.BuildPostException;
import org.calgb.test.performance.PostRequestFactory;
import org.calgb.test.performance.ProcessResponseBodyException;
import org.calgb.test.performance.RequestException;
import org.calgb.test.performance.UseSslException;
import org.junit.Test;

import com.redhat.qe.config.RhscConfiguration;
import com.redhat.qe.model.Cluster;
import com.redhat.qe.utils.MyMarshaller;

public class ClusterTest extends TestBase{
	
	@Test
	public void listTest() throws UseSslException, ProcessResponseBodyException, RequestException{
		getSession().sendTransaction(new HttpGet("/api/clusters"));
	}
	
	@Test 
	public void createTest() throws JAXBException, ProcessResponseBodyException, RequestException, BuildPostException{
		Cluster cluster = RhscConfiguration.getConfiguration().getCluster();
		String xml = MyMarshaller.marshall(getContext(), cluster);
		HttpPost post = new HttpPost();
		new PostRequestFactory().buildPost("/api/clusters",xml );
		post.addHeader("Content-Type", "application/xml");
		post.addHeader("Accept", "application/xml");
		getSession().sendTransaction(post);
	}

}
