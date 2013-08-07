package com.srini.flex;

import flex.messaging.services.ServiceException;

public class CustomServiceException  extends ServiceException{

	private static final long serialVersionUID = 4379493419313231442L;
	
	public CustomServiceException(String code, String message) {
		super();
		setCode(code);
		setMessage(message);
	}
	
}
