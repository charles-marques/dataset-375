package com.riverdev.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

public class DemoCamelFilter extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelFilter();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteFilter();
	}
}
