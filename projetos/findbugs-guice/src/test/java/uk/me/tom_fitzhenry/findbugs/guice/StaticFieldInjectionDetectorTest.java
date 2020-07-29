package uk.me.tom_fitzhenry.findbugs.guice;

import static com.youdevise.fbplugins.tdd4fb.DetectorAssert.ofType;

import org.junit.Before;
import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingANonStaticFieldAnnotatedWithInject;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAStaticFieldAnnotatedWithInject;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAStaticFieldNotAnnotatedWithInject;

import com.youdevise.fbplugins.tdd4fb.DetectorAssert;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;

public class StaticFieldInjectionDetectorTest {
	private BugReporter bugReporter;
    private Detector detector;
    
    @Before public void setUp() {
        bugReporter = DetectorAssert.bugReporterForTesting();
        detector = new StaticFieldInjectionDetector(bugReporter);
    }
    
    @Test
    public void staticFieldsAnnotatedWithInjectAreReported() throws Exception {
    	assertBugReportedAgainstClass(ClassContainingAStaticFieldAnnotatedWithInject.class);
    }
    
    @Test
    public void staticFieldsNotAnnotatedWithInjectAreNotReported() throws Exception {
    	assertNoBugsReportedForClass(ClassContainingAStaticFieldNotAnnotatedWithInject.class);
    }
    
    @Test
    public void nonStaticFieldsAnnotatedWithInjectAreNotReported() throws Exception {
    	assertNoBugsReportedForClass(ClassContainingANonStaticFieldAnnotatedWithInject.class);
    }
    
    private void assertBugReportedAgainstClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertBugReported(classToTest, detector, bugReporter, ofType("GUICE_STATIC_FIELD_INJECTION"));
    }
    
    private void assertNoBugsReportedForClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertNoBugsReported(classToTest, detector, bugReporter);
    }
}
