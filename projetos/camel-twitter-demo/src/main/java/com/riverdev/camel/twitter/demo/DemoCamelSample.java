package com.riverdev.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

public class DemoCamelSample extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelSample();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteSample();
	}
}
