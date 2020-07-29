package com.riverdev.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

public class DemoCamelHome extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelHome();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteHome();
	}
}
