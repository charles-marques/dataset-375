package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.TwitterRoute2;

public class DemoCamel2 extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamel2();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRoute2();
	}
}
