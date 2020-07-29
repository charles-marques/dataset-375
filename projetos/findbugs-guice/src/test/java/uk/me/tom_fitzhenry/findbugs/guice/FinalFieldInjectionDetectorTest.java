package uk.me.tom_fitzhenry.findbugs.guice;

import static com.youdevise.fbplugins.tdd4fb.DetectorAssert.ofType;

import org.junit.Before;
import org.junit.Test;

import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration;
import uk.me.tom_fitzhenry.findbugs.guice.benchmarks.ClassContainingANonFinalFieldAnnotatedWithInject;

import com.youdevise.fbplugins.tdd4fb.DetectorAssert;

import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;

public class FinalFieldInjectionDetectorTest {

    private BugReporter bugReporter;
    private Detector detector;
    
    @Before public void setUp() {
        bugReporter = DetectorAssert.bugReporterForTesting();
        detector = new FinalFieldInjectionDetector(bugReporter);
    }
    
    @Test
    public void finalFieldInjectionIsReported() throws Exception {
    	assertBugReportedAgainstClass(ClassContainingAFinalFieldAnnotatedWithInjectAssignedInConstructor.class);
    	assertBugReportedAgainstClass(ClassContainingAFinalFieldAnnotatedWithInjectAssignedWithDeclaration.class);
    }
    
    @Test
    public void nonFinalFieldInjectionIsNotReported() throws Exception {
    	assertNoBugsReportedForClass(ClassContainingANonFinalFieldAnnotatedWithInject.class);
    }
    
    private void assertBugReportedAgainstClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertBugReported(classToTest, detector, bugReporter, ofType("GUICE_FINAL_FIELD_INJECTION"));
    }
    
    private void assertNoBugsReportedForClass(Class<?> classToTest) throws Exception {
        DetectorAssert.assertNoBugsReported(classToTest, detector, bugReporter);
    }
	
}
