package com.redhat.qe.repository.rest;

import org.apache.http.client.methods.HttpGet;
import org.calgb.test.performance.HttpSession;

import com.redhat.qe.model.DatacenterList;

public class DatacenterRepository extends Repository {

	public DatacenterRepository(HttpSession session) {
		super(session);
	}
	
	public DatacenterList list() {
		ResponseWrapper response = sendTransaction(new HttpGet("/api/datacenters")); 
		response.expectCode(200);
		return (DatacenterList) unmarshal(response.getBody());
	}


}
