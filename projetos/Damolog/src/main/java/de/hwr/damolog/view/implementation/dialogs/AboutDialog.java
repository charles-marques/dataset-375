/**
 * 
 */
package de.hwr.damolog.view.implementation.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import de.hwr.damolog.constants.Images;
import de.hwr.damolog.constants.Strings;

/**
 * @author nsmeenk
 * 
 */
public class AboutDialog extends Dialog {

	/**
	 * @param pParent
	 * @param pStyle
	 */
	public AboutDialog(Shell pParent, int pStyle) {
		super(pParent, pStyle);
	}

	/**
	 * @param pParent
	 */
	public AboutDialog(Shell pParent) {
		super(pParent);
	}

	public int open() {

		Shell parent = getParent();
		Display display = parent.getDisplay();

		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CLOSE);
		shell.setText(Strings.ABOUT_DIALOG_TITLE);
		shell.setImage(de.hwr.damolog.constants.Images.ABOUT_ICON);
		shell.setLayout(new GridLayout(2, true));

		Label title = new Label(shell, SWT.NONE);
		title.setText("Damolog");
		title.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false, 2, 1));
		title.setFont(new Font(display, new FontData("arial", 20, SWT.BOLD)));

		new Label(shell, SWT.NONE).setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false, 2, 1));

		Label image = new Label(shell, SWT.NONE);
		image.setImage(Images.TITLE_FULL);
		image.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 2));

		Label name1 = new Label(shell, SWT.NONE);
		name1.setText("Jakob Painter");
		name1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

		Label name2 = new Label(shell, SWT.NONE);
		name2.setText("Nico Smeenk");
		name2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true));

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
