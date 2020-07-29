package br.com.jawsys.camel.twitter.demo.rotas;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.codehaus.jettison.json.JSONObject;

public class TwitterRoute4 extends RouteBuilder {

	public void configure() throws Exception {
		from("twitter:tweets?delay=1")
			.transform(body().convertToString())
			.enrich("direct:identifica-idioma", new PrefixoIdioma())
			.to("bean:tap");

		// identifica o idioma do texto do objeto da mensagem
		// e grava no header LANGUAGE o idioma
		from("direct:identifica-idioma")
			.transform(body().convertToString())
			.process(new ParametroHttp())
			.to("http://api.langid.net/identify.json")
			.transform(body().convertToString())
			.process(new JSONIdentificaLinguaProcessor());
	}

	public class PrefixoIdioma implements AggregationStrategy {
		public Exchange aggregate(Exchange original, Exchange resource) {
			String language = resource.getIn().getHeader("LANGUAGE").toString();
			original.getIn().setBody(
					"[" + language + "] " + original.getIn().getBody());
			return original;
		}
	}

	public class ParametroHttp implements Processor {
		public void process(Exchange arg0) throws Exception {
			String body = arg0.getIn().getBody().toString();
			body = URLEncoder.encode(body, "UTF-8");
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put(Exchange.HTTP_QUERY, "string=" + body);
			arg0.getIn().setHeaders(headers);
		}
	}

	public class JSONIdentificaLinguaProcessor implements Processor {
		public void process(Exchange arg0) throws Exception {
			String json = arg0.getIn().getBody().toString();
			JSONObject jsonObject = new JSONObject(json);
			jsonObject = jsonObject.getJSONObject("response");
			arg0.getIn().setHeader("LANGUAGE", jsonObject.get("full-name"));
		}
	}
}
