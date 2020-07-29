package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.Inject;

public class ClassContainingAStaticFieldAnnotatedWithInject {
	@Inject private static String foo = "nonInjectedString";

	public String getFoo() {
		return foo;
	}

}
