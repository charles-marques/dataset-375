package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.TwitterRoute4;

public class DemoCamel3 extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamel3();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRoute4();
	}
}
