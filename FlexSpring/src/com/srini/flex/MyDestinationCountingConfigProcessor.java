package com.srini.flex;

import org.springframework.flex.config.MessageBrokerConfigProcessor;
import flex.messaging.MessageBroker;
import flex.messaging.services.RemotingService;

public class MyDestinationCountingConfigProcessor implements
		MessageBrokerConfigProcessor {
	public MessageBroker processAfterStartup(MessageBroker broker) {
		RemotingService remotingService = (RemotingService) broker
				.getServiceByType(RemotingService.class.getName());
		if (remotingService.isStarted()) {
			System.out.println("The Remoting Service has been started with "
					+ remotingService.getDestinations().size()
					+ " Destinations.");
			System.out.println("Destinations: "+remotingService.getDestinations().keySet());
		}
		return broker;
	}

	public MessageBroker processBeforeStartup(MessageBroker broker) {
		return broker;
	}
}