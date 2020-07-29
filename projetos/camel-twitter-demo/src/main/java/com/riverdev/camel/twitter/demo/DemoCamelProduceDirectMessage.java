package com.riverdev.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

public class DemoCamelProduceDirectMessage extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelProduceDirectMessage();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteProduceDirectMessage();
	}
}
