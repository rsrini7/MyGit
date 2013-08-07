package com.srini.flex;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 4379493419313231442L;
	private String code;
	private String message;
	
	public CustomException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
