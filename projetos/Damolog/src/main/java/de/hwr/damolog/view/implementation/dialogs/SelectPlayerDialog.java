/**
 * 
 */
package de.hwr.damolog.view.implementation.dialogs;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.hwr.damolog.constants.Strings;
import de.hwr.damolog.model.Player;
import de.hwr.damolog.view.implementation.PlayerTable;

/**
 * @author nsmeenk
 * 
 */
public class SelectPlayerDialog extends Dialog {

	private PlayerTable _player1Table;
	private PlayerTable _player2Table;
	private Player _player1;
	private Player _player2;
	private int _returnValue;

	/**
	 * @param pParent
	 * @param pStyle
	 */
	public SelectPlayerDialog(Shell pParent, int pStyle) {
		super(pParent, pStyle);
	}

	/**
	 * @param pParent
	 */
	public SelectPlayerDialog(Shell pParent) {
		super(pParent);
	}

	public int open(final List<Player> pPlayer) {
		_returnValue = SWT.CANCEL;
		Shell parent = getParent();
		Display display = parent.getDisplay();

		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CLOSE);
		shell.setText(Strings.GAMER_DIALOG_TITLE);
		shell.setImage(de.hwr.damolog.constants.Images.GAMER_ICON);
		shell.setLayout(new GridLayout(2, true));

		Label player1Label = new Label(shell, SWT.NONE);
		player1Label.setText("Spieler 1 Auswählen:");

		Label player2Label = new Label(shell, SWT.NONE);
		player2Label.setText("Spieler 2 Auswählen:");

		_player1Table = new PlayerTable(shell, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		_player1Table.getTable().setHeaderVisible(true);
		_player1Table.getTable().setLinesVisible(true);
		_player1Table.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_player1Table.setContentProvider(ArrayContentProvider.getInstance());

		_player1Table.setInput(pPlayer);

		_player2Table = new PlayerTable(shell, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		_player2Table.getTable().setHeaderVisible(true);
		_player2Table.getTable().setLinesVisible(true);
		_player2Table.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_player2Table.setContentProvider(ArrayContentProvider.getInstance());

		_player2Table.setInput(pPlayer);

		Button okButton = new Button(shell, SWT.PUSH);
		okButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		okButton.setText("OK");
		okButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				StructuredSelection selection1 = (StructuredSelection) _player1Table.getSelection();
				StructuredSelection selection2 = (StructuredSelection) _player2Table.getSelection();
				_player1 = (Player) selection1.getFirstElement();
				_player2 = (Player) selection2.getFirstElement();
				if (_player1 == null || _player2 == null) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
					messageBox.setText("Falsche angaben!");
					messageBox.setMessage("Bitte wählen sie beide Spieler aus!");
					messageBox.open();
				} else if (_player1 == _player2) {
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
					messageBox.setText("Falsche angaben!");
					messageBox.setMessage("Bitte wählen sie verschiedene Spieler aus!");
					messageBox.open();
				} else {
					_returnValue = SWT.OK;
					shell.close();
				}
			}
		});

		Button cancelButton = new Button(shell, SWT.PUSH);
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		cancelButton.setText("Abbrechen");
		cancelButton.addSelectionListener(new SelectionAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.close();
			}
		});

		shell.pack();
		shell.setSize(shell.getSize().x, 400);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return _returnValue;

	}

	/**
	 * @return the player1
	 */
	public Player getPlayer1() {
		return _player1;
	}

	/**
	 * @return the player2
	 */
	public Player getPlayer2() {
		return _player2;
	}

}
