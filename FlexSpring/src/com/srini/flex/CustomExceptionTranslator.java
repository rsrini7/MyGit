package com.srini.flex;

import java.util.HashMap;
import java.util.Map;

import org.springframework.flex.core.ExceptionTranslator;

import flex.messaging.MessageException;

public class CustomExceptionTranslator  implements ExceptionTranslator{
	
	@Override
	public boolean handles(Class<?> clazz) {
		return Boolean.TRUE;
		//return (ClassUtils.isAssignable(AccessDeniedException.class,clazz));
	}

	@Override
	public MessageException translate(final Throwable throwable) {
		//throwable.printStackTrace();
		MessageException msgexcp = new MessageException();
		msgexcp.setCode("Custom Exception");
		if (throwable instanceof CustomServiceException) {
			CustomServiceException excp = (CustomServiceException)throwable;
			Map<String, String> map= new HashMap<String, String>();
			map.put("errormessage", excp.getMessage());
			map.put("errorcode", excp.getCode());
			msgexcp.setRootCause(throwable);
			msgexcp.setExtendedData(map);
			throw msgexcp;
		}else{
			msgexcp.setDetails(throwable.getMessage());
			msgexcp.setRootCause(throwable);
			throw msgexcp;
		}
		//return null;
	}
}
