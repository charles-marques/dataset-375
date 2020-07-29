package br.com.jawsys.camel.twitter.demo.rotas;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRoute3 extends RouteBuilder {

	public void configure() throws Exception {
		from("twitter:tweets?delay=5").transform(body().convertToString()).to(
				"bean:tap");
	}

}
