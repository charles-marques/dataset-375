package uk.me.tom_fitzhenry.findbugs.guice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAFieldAnnotatedWithJSR330Inject;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration;

import com.google.inject.AbstractModule;
import com.google.inject.ConfigurationException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class FinalFieldInjectionTest {
	
	private Injector injector;
	
	@Before
	public void setup() {
		injector = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				bind(String.class).toInstance("injected string");
			}
			
		});
	}
	
	@Test
	public void guiceDoesNotInjectFinalFieldsIfAssignedWithDeclaration() {
		ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration benchmark = injector.getInstance(ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration.class);
		assertEquals("non injected string", benchmark.getFoo());
	}
	
	@Test
	public void guiceInjectsFinalFieldsIfAssignedInConstructor() {
		ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor benchmark = injector.getInstance(ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor.class);
		assertEquals("injected string", benchmark.getFoo());
	}
	
	@Test(expected=ConfigurationException.class)
	public void jsr330AnnotatedFinalFieldsThrowProvisionException() {
		@SuppressWarnings("unused")
		ClassContainingAFieldAnnotatedWithJSR330Inject instance = injector.getInstance(ClassContainingAFieldAnnotatedWithJSR330Inject.class);
	}
}
