package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;

import br.com.jawsys.camel.twitter.demo.rotas.IdentificaIdiomaRoute;

public class DemoCamelIdentificaIdiomas extends CamelStartup {

	public static void main(String args[]) {
		new DemoCamelIdentificaIdiomas();
	}

	@Override
	protected RoutesBuilder getRoute() {
		return new IdentificaIdiomaRoute();
	}
}
