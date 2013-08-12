package com.redhat.qe.repository.rest;

import org.junit.Assert;

import org.calgb.test.performance.SimplifiedResponse;

public class ResponseWrapper {
	
	private SimplifiedResponse response;

	public ResponseWrapper(SimplifiedResponse response ){
		this.response = response;
	}
	
	public String getMessage() {
		return response.getMessage();
	}


	public String getBody() {
		return response.getBody();
	}


	public int getCode() {
		return response.getCode();
	}
	
	public void expectCode(int code){
		Assert.assertEquals("expected http request code",code, response.getCode());
	}

	
	


}
