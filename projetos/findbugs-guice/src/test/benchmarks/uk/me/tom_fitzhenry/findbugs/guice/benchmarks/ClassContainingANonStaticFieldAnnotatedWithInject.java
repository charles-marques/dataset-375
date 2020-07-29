package uk.me.tom_fitzhenry.findbugs.guice.benchmarks;

import com.google.inject.Inject;


public class ClassContainingANonStaticFieldAnnotatedWithInject {
	@Inject private String foo;
}
