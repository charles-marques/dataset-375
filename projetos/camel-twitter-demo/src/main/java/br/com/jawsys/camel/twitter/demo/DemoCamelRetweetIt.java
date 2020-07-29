package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.TwitterRouteRetweetIt;

public class DemoCamelRetweetIt extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelRetweetIt();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRouteRetweetIt();
	}
}
