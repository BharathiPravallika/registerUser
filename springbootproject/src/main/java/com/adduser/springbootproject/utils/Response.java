package com.adduser.springbootproject.utils;

public class Response {
	
	private Object responseBody;
	private String errorMessage;
	private String successMessage;
	
	public Response(Object responseBody, String errorMessage, String successMessage) {
		super();
		this.responseBody = responseBody;
		this.errorMessage = errorMessage;
		this.successMessage = successMessage;
	}

	public static Response getResponse(Object responseBody, String errorMessage, String successMessage) {
		// TODO Auto-generated method stub
		return new Response(responseBody, errorMessage, successMessage);
	}
	
	public Object getResponseBody() {
		return responseBody;
	}
	
	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
}