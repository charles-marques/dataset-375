package br.com.jawsys.camel.twitter.demo.rotas;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRoute2 extends RouteBuilder {

	public void configure() throws Exception {
		from("twitter:tweets?type=SEARCH&search=#justjava").transform(
				body().convertToString()).to("bean:tap");
	}

}
