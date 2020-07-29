package com.riverdev.camel.twitter.demo;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRouteProduceStatus extends RouteBuilder {

	public void configure() throws Exception {
		from("timer://foo?repeatCount=1").setBody(constant("this is a test")).
		to("twitter://timeline/user?"
				+ "consumerKey=xHW7Fhx2wzu33LMs68C6g"
				+ "&consumerSecret=bvPpSVCK3dJCGAmmo90qqFFRdlvcyQ4w7oBu5pp4WY"
				+ "&accessToken=107890695-tH3LCcWuGO8XnUIItJXBW9V2uY3avCcjgxHg1BSc"
				+ "&accessTokenSecret=c5hbrPwMyVWISIllw4SaSDGxywYBk7sxFXoAaosE");
	}
}
