package com.srini.flex;

import org.springframework.flex.core.MessageInterceptor;
import org.springframework.flex.core.MessageProcessingContext;
import flex.messaging.messages.Message;

public class LoggingInterceptor implements MessageInterceptor {

	@Override
	public Message postProcess(MessageProcessingContext ctx, Message inputMsg,
			Message outputMsg) {
		System.out.println("post class " + outputMsg.getClass().getName());
		if (inputMsg instanceof flex.messaging.messages.RPCMessage) {
			flex.messaging.messages.RPCMessage info = (flex.messaging.messages.RPCMessage) inputMsg;
			System.out.println("user Id:"+info.getRemoteUsername());
		}

		if (outputMsg instanceof flex.messaging.messages.AcknowledgeMessage) {
			flex.messaging.messages.AcknowledgeMessage info = (flex.messaging.messages.AcknowledgeMessage) outputMsg;
		}
		return outputMsg;
	}

	@Override
	public Message preProcess(MessageProcessingContext ctx, Message msg) {
		System.out.println("pre class " + msg.getClass().getName());
		if (msg instanceof flex.messaging.messages.CommandMessage) {
			flex.messaging.messages.CommandMessage cmdMsg = (flex.messaging.messages.CommandMessage) msg;
			cmdMsg.getHeader(flex.messaging.messages.CommandMessage.REMOTE_CREDENTIALS_HEADER);
		}
		
		if (msg instanceof flex.messaging.messages.RPCMessage) {
			flex.messaging.messages.RPCMessage info = (flex.messaging.messages.RPCMessage) msg;
			System.out.println("user Id:"+info.getRemoteUsername());
		}

		return msg;
	}

}