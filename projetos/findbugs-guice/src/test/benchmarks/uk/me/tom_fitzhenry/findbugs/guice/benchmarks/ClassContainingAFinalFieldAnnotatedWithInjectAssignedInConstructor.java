package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.Inject;

public class ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor {
	@Inject private final String foo;
	
	public ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor() {
		this.foo = "non injected string";
	}
	
	public String getFoo() {
		return this.foo;
	}
}
