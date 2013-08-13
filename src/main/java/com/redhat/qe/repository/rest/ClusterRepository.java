package com.redhat.qe.repository.rest;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.calgb.test.performance.HttpSession;

import com.redhat.qe.model.Cluster;
import com.redhat.qe.model.ClusterList;
import com.redhat.qe.model.Datacenter;
import com.redhat.qe.repository.ClusterSerializer;

public class ClusterRepository extends Repository{

	public ClusterRepository(HttpSession session) {
		super(session);
	}
	
	public  ArrayList<Cluster> list(){
		ResponseWrapper response = sendTransaction(new HttpGet("/api/clusters"));
		ClusterList clusterList = (ClusterList) unmarshal(response.getBody());
		return clusterList.getClusters();
	}

	public ResponseWrapper create(final Cluster cluster){
		String xml = new ClusterSerializer().toXml(cluster);
		HttpPost post = new PostRequestFactory().createPost("/api/clusters", xml);
		ResponseWrapper response = sendTransaction(post); 
		response.expectCode(201);				
		return response;
	}

	public ResponseWrapper delete(Cluster cluster){
		ResponseWrapper response = sendTransaction(new HttpDelete(String.format("/api/clusters/%s", cluster.getId())));
		response.expectCode(200);
		return response;
	}

}
