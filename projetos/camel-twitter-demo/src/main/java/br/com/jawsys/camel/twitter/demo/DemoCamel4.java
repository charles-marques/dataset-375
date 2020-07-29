package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.TwitterRoute4;

public class DemoCamel4 extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamel4();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new TwitterRoute4();
	}
}
