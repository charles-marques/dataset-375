package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

public class ClassContainingAFieldAnnotatedWithJSR330Inject {
	@javax.inject.Inject private final String foo = "non-injected";
	
	String getFoo() {
		return this.foo;
	}
}