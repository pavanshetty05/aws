package com.amazonaws.lambda.demo;

public class MyOwnExpcetion extends Exception {
	
	String message;

	public MyOwnExpcetion(String message) {
		super();
		this.message = message;
	}
	
	

}
