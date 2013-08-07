package com.srini.flex;

import org.springframework.flex.core.MessageInterceptor;
import org.springframework.flex.core.MessageProcessingContext;

import flex.messaging.messages.Message;

public class MyMessageInterceptor implements MessageInterceptor{

	@Override
	public Message postProcess(MessageProcessingContext arg0, Message ipMsg,
			Message opMsg) {
		//System.out.println("postprocess header: "+ opMsg.getHeaders().keySet());
		System.out.println("postprocess destination: "+ipMsg.getDestination());
		Object result =  opMsg.getBody();
        
        //...Do some sort of processing on result...
        
		opMsg.setBody(result);
        
		return opMsg;
	}

	@Override
	public Message preProcess(MessageProcessingContext arg0, Message inMsg) {
		//System.out.println("preprocess headers: "+ inMsg.getHeaders().keySet());
		System.out.println("preprocess destination: "+inMsg.getDestination());
		return inMsg;
	}

}
