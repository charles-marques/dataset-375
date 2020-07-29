/**
 * 
 */
package de.hwr.damolog.view.implementation.dialogs;

import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.hwr.damolog.constants.Strings;
import de.hwr.damolog.model.Player;
import de.hwr.damolog.view.implementation.PlayerTable;

/**
 * @author nsmeenk
 * 
 */
public class GamerDialog extends Dialog {

	private Player _selectedPlayer;
	private Button _addButton;
	private Text _nameText;
	private Combo _aiCombo;
	private Text _winText;
	private Text _remiText;
	private Text _loseText;
	private Button _resetButton;
	private Button _closeButton;
	private Button _deleteButton;
	private PlayerTable _playerTable;

	/**
	 * @param pParent
	 * @param pStyle
	 */
	public GamerDialog(Shell pParent, int pStyle) {
		super(pParent, pStyle);
	}

	/**
	 * @param pParent
	 */
	public GamerDialog(Shell pParent) {
		super(pParent);
	}

	public int open(final List<Player> pPlayer) {
		_selectedPlayer = null;

		Shell parent = getParent();
		Display display = parent.getDisplay();

		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.CLOSE);
		shell.setText(Strings.GAMER_DIALOG_TITLE);
		shell.setImage(de.hwr.damolog.constants.Images.GAMER_ICON);
		shell.setLayout(new GridLayout(2, true));

		_playerTable = new PlayerTable(shell, SWT.BORDER | SWT.SINGLE | SWT.FULL_SELECTION);
		_playerTable.getTable().setHeaderVisible(true);
		_playerTable.getTable().setLinesVisible(true);
		_playerTable.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		_playerTable.setContentProvider(ArrayContentProvider.getInstance());

		_playerTable.setInput(pPlayer);

		Composite selectionComposite = new Composite(shell, SWT.NONE);
		selectionComposite.setLayout(new GridLayout(3, true));

		_addButton = new Button(selectionComposite, SWT.PUSH);
		_addButton.setText("Neuen Spieler erstellen");
		_addButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		_addButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				Player newPlayer = new Player("Neuer Spieler", 0, 0, 0);
				_playerTable.add(newPlayer);
				pPlayer.add(newPlayer);
				_playerTable.setSelection(new StructuredSelection(newPlayer));
			}
		});

		Label nameLabel = new Label(selectionComposite, SWT.NONE);
		nameLabel.setText("Name");
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));

		_nameText = new Text(selectionComposite, SWT.BORDER);
		_nameText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		_nameText.setEnabled(false);
		_nameText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent pArg0) {
				if (_selectedPlayer != null) {
					_selectedPlayer.setName(_nameText.getText());
					update();
				}
			}
		});

		Label aiLabel = new Label(selectionComposite, SWT.NONE);
		aiLabel.setText("Computerspieler?");
		aiLabel.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 2, 1));

		_aiCombo = new Combo(selectionComposite, SWT.BORDER | SWT.READ_ONLY);
		_aiCombo.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 1, 1));
		_aiCombo.setEnabled(false);
		String[] items = { "Ja", "Nein" };
		_aiCombo.setItems(items);
		_aiCombo.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				if (_selectedPlayer != null) {
					if (_aiCombo.getItem(_aiCombo.getSelectionIndex()).equals("Ja")) {
						_selectedPlayer.setAI(true);
					} else {
						_selectedPlayer.setAI(false);
					}
					update();
				}
			}
		});

		Label winLabel = new Label(selectionComposite, SWT.NONE);
		winLabel.setText("Gewonnen");

		Label remiLabel = new Label(selectionComposite, SWT.NONE);
		remiLabel.setText("Unentschieden");

		Label loseLabel = new Label(selectionComposite, SWT.NONE);
		loseLabel.setText("Verloren");

		_winText = new Text(selectionComposite, SWT.BORDER | SWT.READ_ONLY);
		_winText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		_winText.setEnabled(false);

		_remiText = new Text(selectionComposite, SWT.BORDER | SWT.READ_ONLY);
		_remiText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		_remiText.setEnabled(false);

		_loseText = new Text(selectionComposite, SWT.BORDER | SWT.READ_ONLY);
		_loseText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		_loseText.setEnabled(false);

		_resetButton = new Button(selectionComposite, SWT.PUSH);
		_resetButton.setText("Zurücksetzen");
		_resetButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		_resetButton.setEnabled(false);
		_resetButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				_selectedPlayer.resetStats();
				_winText.setText(_selectedPlayer.getWinns() + "");
				_remiText.setText(_selectedPlayer.getRemis() + "");
				_loseText.setText(_selectedPlayer.getRemis() + "");
				if (_selectedPlayer.getWinns() != 0 || _selectedPlayer.getRemis() != 0
						|| _selectedPlayer.getLoses() != 0) {
					_resetButton.setEnabled(true);
				} else {
					_resetButton.setEnabled(false);
				}
				update();
			}
		});

		_deleteButton = new Button(selectionComposite, SWT.PUSH);
		_deleteButton.setText("Spieler löschen");
		_deleteButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		_deleteButton.setEnabled(false);
		_deleteButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				_playerTable.remove(_selectedPlayer);
				pPlayer.remove(_selectedPlayer);
			}
		});

		_closeButton = new Button(selectionComposite, SWT.PUSH);
		_closeButton.setText("Schließen");
		_closeButton.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
		_closeButton.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				shell.close();
			}
		});

		new Label(selectionComposite, SWT.NONE);

		_playerTable.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent pArg0) {
				StructuredSelection selection = (StructuredSelection) _playerTable.getSelection();
				_selectedPlayer = (Player) selection.getFirstElement();
				resetSelection();
			}
		});

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return SWT.OK;
	}

	/**
	 * 
	 */
	private void resetSelection() {
		if (_selectedPlayer != null) {
			_nameText.setText(_selectedPlayer.getName());
			_nameText.setEnabled(true);
			if (_selectedPlayer.isAI()) {
				_aiCombo.select(_aiCombo.indexOf("Ja"));
			} else {
				_aiCombo.select(_aiCombo.indexOf("Nein"));
			}
			_aiCombo.setEnabled(true);
			_winText.setText(_selectedPlayer.getWinns() + "");
			_winText.setEnabled(true);
			_remiText.setText(_selectedPlayer.getRemis() + "");
			_remiText.setEnabled(true);
			_loseText.setText(_selectedPlayer.getLoses() + "");
			_loseText.setEnabled(true);

			if (_selectedPlayer.getWinns() != 0 || _selectedPlayer.getRemis() != 0 || _selectedPlayer.getLoses() != 0) {
				_resetButton.setEnabled(true);
			} else {
				_resetButton.setEnabled(false);
			}
			_deleteButton.setEnabled(true);
		} else {
			_nameText.setText("");
			_nameText.setEnabled(false);
			_aiCombo.select(-1);
			_aiCombo.setEnabled(false);
			_winText.setText("");
			_winText.setEnabled(false);
			_remiText.setText("");
			_remiText.setEnabled(false);
			_loseText.setText("");
			_loseText.setEnabled(false);
			_resetButton.setEnabled(false);
			_deleteButton.setEnabled(false);
		}
	}

	/**
	 * 
	 */
	private void update() {
		_playerTable.refresh(_selectedPlayer);
	}
}
