package com.riverdev.camel.twitter.demo;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRouteSample extends RouteBuilder {

	public void configure() throws Exception {
		from("twitter://streaming/sample?type=polling&delay=10"
				+ "&consumerKey=xHW7Fhx2wzu33LMs68C6g"
				+ "&consumerSecret=bvPpSVCK3dJCGAmmo90qqFFRdlvcyQ4w7oBu5pp4WY"
				+ "&accessToken=107890695-tH3LCcWuGO8XnUIItJXBW9V2uY3avCcjgxHg1BSc"
				+ "&accessTokenSecret=c5hbrPwMyVWISIllw4SaSDGxywYBk7sxFXoAaosE"
				).transform(body().convertToString()).to(
				"bean:tap");
	}
}
