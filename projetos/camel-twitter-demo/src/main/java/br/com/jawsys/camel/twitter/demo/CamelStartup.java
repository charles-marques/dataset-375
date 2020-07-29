package br.com.jawsys.camel.twitter.demo;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.util.jndi.JndiContext;

abstract class CamelStartup {

	public CamelStartup() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					start();
				} catch (Exception e1) {
				}

				while (true) {
					try {
						Thread.sleep(5 * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		t.start();
	}

	private void addBeans(DefaultCamelContext camel) throws Exception {
		JndiContext jc = new JndiContext();
		jc.bind("tap", new LogMessage());
		camel.setJndiContext(jc);
	}

	public void start() throws Exception {
		DefaultCamelContext context = new DefaultCamelContext();
		context.addRoutes(getRoute());
		addBeans(context);
		context.start();
	}

	protected abstract RoutesBuilder getRoute();

}
