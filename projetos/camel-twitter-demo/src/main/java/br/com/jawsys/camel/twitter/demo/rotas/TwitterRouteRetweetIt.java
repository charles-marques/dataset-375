package br.com.jawsys.camel.twitter.demo.rotas;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.twitter.data.Status;

public class TwitterRouteRetweetIt extends RouteBuilder {

	public void configure() throws Exception {
		from("twitter:tweets?type=SEARCH&search=#justjava").transform(
				bean(ConverteStatus.class)).to("direct:filtro");

		from("direct:filtro").filter(
				header("screenname").isNotEqualTo("cameltweet")).transform()
				.body().setBody(body().prepend("RT ")).to(
						"twitter:tweetit?user=cameltweet&pass=ctapache!");
	}

	public static class ConverteStatus {
		public String converte(Status status) {
			return "@" + status.getUser().getScreenName() + ": "
					+ status.getText();
		}
	}
}
