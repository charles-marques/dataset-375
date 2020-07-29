package com.riverdev.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

public class DemoCamelProduceStatus extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelProduceStatus();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteProduceStatus();
	}
}
