package br.com.jawsys.camel.twitter.demo.rotas;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRoute1 extends RouteBuilder {

	public void configure() throws Exception {
		from("file:outbox") // MessageTranslator
				.transform(body().convertToString()) // Tweet it!
				.to("twitter:tweetit?user=cameltweet&pass=ctapache!");
	}

}
