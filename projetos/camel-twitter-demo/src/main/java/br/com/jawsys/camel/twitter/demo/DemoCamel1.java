package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.TwitterRoute1;

public class DemoCamel1 extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamel1();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRoute1();
	}
}
