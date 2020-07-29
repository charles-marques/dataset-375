package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.Inject;

// This is a disgustingly large name. Can I reduce it?
// If not, should I sacrifice expressiveness for brevity?
public class ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration {
	@Inject private final String foo = "non injected string";
	
	public String getFoo() {
		return this.foo;
	}
}
