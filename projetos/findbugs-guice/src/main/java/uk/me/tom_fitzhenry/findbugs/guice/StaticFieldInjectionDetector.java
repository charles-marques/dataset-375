package uk.me.tom_fitzhenry.findbugs.guice;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.Detector;
import edu.umd.cs.findbugs.ba.ClassContext;
import edu.umd.cs.findbugs.ba.XClass;
import edu.umd.cs.findbugs.ba.XField;
import edu.umd.cs.findbugs.classfile.ClassDescriptor;

public class StaticFieldInjectionDetector implements Detector {
	
	private final BugReporter bugReporter;

	public StaticFieldInjectionDetector(BugReporter bugReporter) {
		this.bugReporter = bugReporter;
	}

	@Override
	public void report() {
	}

	@Override
	public void visitClassContext(ClassContext classContext) {
		XClass xclass = classContext.getXClass();
		for (XField field : xclass.getXFields()) {
			if (field.isStatic()) {
				for (ClassDescriptor annotation : field.getAnnotationDescriptors()) {
					if (annotation.getSimpleName().equals("Inject")) {
						bugReporter.reportBug(new BugInstance("GUICE_STATIC_FIELD_INJECTION", NORMAL_PRIORITY).addClass(classContext.getClassDescriptor()).addField(field));
					}
				}
			}
		}
	}
}
