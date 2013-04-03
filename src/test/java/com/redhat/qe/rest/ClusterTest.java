package com.redhat.qe.rest;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.calgb.test.performance.PostRequestFactory;
import org.calgb.test.performance.ProcessResponseBodyException;
import org.calgb.test.performance.RequestException;
import org.calgb.test.performance.UseSslException;
import org.junit.Test;

import com.redhat.qe.utils.MyMarshaller;

public class ClusterTest extends TestBase{
	
	@Test
	public void listTest() throws UseSslException, ProcessResponseBodyException, RequestException{
		getSession().sendTransaction(new HttpGet("/api/clusters"));
	}
	
	@Test 
	public void createTest(){
		MyMarshaller.marshall(getContext(), cluster);
		HttpPost post = new HttpPost();
		new PostRequestFactory().buildPost("/api/clusters",xml );
		post.addHeader("Content-Type", "application/xml");
		post.addHeader("Accept", "application/xml");
		getSession().sendTransaction(post);
	}

}
