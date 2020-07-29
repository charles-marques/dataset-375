/**
 * 
 */
package de.hwr.damolog.view.implementation.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import de.hwr.damolog.constants.Strings;

/**
 * @author nsmeenk
 * 
 */
public class HandbookDialog extends Dialog {

	/**
	 * @param pParent
	 * @param pStyle
	 */
	public HandbookDialog(Shell pParent, int pStyle) {
		super(pParent, pStyle);
	}

	/**
	 * @param pParent
	 */
	public HandbookDialog(Shell pParent) {
		super(pParent);
	}

	public int open() {

		Shell parent = getParent();
		Display display = parent.getDisplay();

		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CLOSE  | SWT.RESIZE);
		shell.setText(Strings.HANDBOOK_DIALOG_TITLE);
		shell.setImage(de.hwr.damolog.constants.Images.HANDBOOK_ICON);
		shell.setLayout(new GridLayout(2, true));

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return SWT.OK;
	}

}
