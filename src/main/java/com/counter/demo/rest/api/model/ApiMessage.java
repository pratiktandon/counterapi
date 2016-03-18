package com.counter.demo.rest.api.model;


/**
 * A wrapper class for showing a message to the 
 * @author Pratik Tandon
 *
 */
public class ApiMessage {
	
	private String message;
	
	public ApiMessage(String msg){
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}
}
