package uk.me.tom_fitzhenry.findbugs.guice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @see http://code.google.com/p/google-guice/wiki/JSR330
 */
public class JSR330IncompatibilitiesTest {
	
	/**
	 * Note that a warning is logged in this case. A detector might be useful,
	 * since logged warnings are easily missed.
	 */
	@Test
	public void overridingAMethodAnnotatedWithJSR330InjectFails() {
		Injector injector = Guice.createInjector(new MyModule());
		
		ClassWithMethodAnnotatedWithJSR330Inject instance = injector.getInstance(ClassWithMethodAnnotatedWithJSR330Inject.class);
		assertEquals(null, instance.getName());
	}
	
	@Test
	public void overridingAMethodAnnotatedWithGuiceInjectSucceeds() {
		Injector injector = Guice.createInjector(new MyModule());
		
		ClassWithMethodAnnotatedWithGuiceInject instance = injector.getInstance(ClassWithMethodAnnotatedWithGuiceInject.class);
		assertEquals("Foo", instance.getName());
	}
	
	static class MyModule extends AbstractModule {
		@Override
		protected void configure() {
			bind(String.class).toInstance("Foo");
			bind(ClassWithMethodAnnotatedWithJSR330Inject.class).to(SubA.class);
			bind(ClassWithMethodAnnotatedWithGuiceInject.class).to(SubB.class);
		}
	}
	
	static class ClassWithMethodAnnotatedWithJSR330Inject {
		String name;
		
		@javax.inject.Inject
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	static class SubA extends ClassWithMethodAnnotatedWithJSR330Inject {
		@Override
		public void setName(String name) {
			this.name = name;
		}
	}
	
	static class ClassWithMethodAnnotatedWithGuiceInject {
		String name;
		
		@com.google.inject.Inject
		public void setName(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	static class SubB extends ClassWithMethodAnnotatedWithGuiceInject {
		@Override
		public void setName(String name) {
			this.name = name;
		}
	}
}
