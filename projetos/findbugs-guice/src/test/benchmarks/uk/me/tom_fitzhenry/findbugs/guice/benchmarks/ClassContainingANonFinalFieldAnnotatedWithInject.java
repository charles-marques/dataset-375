package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.Inject;

public class ClassContainingANonFinalFieldAnnotatedWithInject {
	@Inject private String foo; 
}
