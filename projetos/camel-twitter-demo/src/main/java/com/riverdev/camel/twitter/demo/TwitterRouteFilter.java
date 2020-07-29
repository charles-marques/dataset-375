package com.riverdev.camel.twitter.demo;

import org.apache.camel.builder.RouteBuilder;

public class TwitterRouteFilter extends RouteBuilder {
	
	private static final double GRIDSIZE = 10.0;

	public void configure() throws Exception {
		StringBuilder sb = new StringBuilder();
		
		boolean first = true;
		for (double swLat = 24.0; swLat <= (50.0 - GRIDSIZE); swLat = swLat + 10.0) {
			for (double swLon = -125.0; swLon <= (-67.0 - GRIDSIZE); swLon = swLon + 10.0) {
				double neLat = swLat + GRIDSIZE;
				double neLon = swLon + GRIDSIZE;
				if (first) {
					sb.append(swLon + "," + swLat + ";" + neLon + "," + neLat);
					first = false;
				} else {
					sb.append(";" + swLon + "," + swLat + ";" + neLon + "," + neLat);
				}
			}
		}
		
		from("twitter://streaming/filter?type=polling&delay=10"
				+ "&locations=" + sb.toString()
				+ "&consumerKey=xHW7Fhx2wzu33LMs68C6g"
				+ "&consumerSecret=bvPpSVCK3dJCGAmmo90qqFFRdlvcyQ4w7oBu5pp4WY"
				+ "&accessToken=107890695-tH3LCcWuGO8XnUIItJXBW9V2uY3avCcjgxHg1BSc"
				+ "&accessTokenSecret=c5hbrPwMyVWISIllw4SaSDGxywYBk7sxFXoAaosE"
				).transform(body().convertToString()).to(
				"bean:tap");
	}
}
