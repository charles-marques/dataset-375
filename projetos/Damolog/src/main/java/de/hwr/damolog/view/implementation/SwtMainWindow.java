package de.hwr.damolog.view.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.hwr.damolog.constants.Images;
import de.hwr.damolog.constants.Strings;
import de.hwr.damolog.controller.IController;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Player;
import de.hwr.damolog.view.IObservingView;
import de.hwr.damolog.view.implementation.dialogs.AboutDialog;
import de.hwr.damolog.view.implementation.dialogs.GamerDialog;
import de.hwr.damolog.view.implementation.dialogs.HandbookDialog;
import de.hwr.damolog.view.implementation.dialogs.SelectPlayerDialog;

/**
 * 
 * Hauptfenster
 * 
 * @author nsmeenk
 * 
 */
public class SwtMainWindow implements IObservingView {

	private Display _display;
	private Shell _shell;
	private IController _controller;
	private FieldComposite _fieldPainter;
	// private Player _spieler1;
	// private Player _spieler2;

	private List<Player> _players;
	private Label _player1Label;
	private Label _player2Label;
	private Player _player1;
	private Player _player2;

	/**
	 * Konstruktor
	 */
	public SwtMainWindow() {
		loadPlayers();

		_display = new Display();
		_shell = new Shell(_display, SWT.CLOSE);

		_shell.setText(Strings.TITLE);
		_shell.setImage(Images.TITLE);
		_shell.setLayout(new GridLayout(1, false));

		Menu menuBar = new Menu(_shell, SWT.BAR);

		MenuItem gameMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		gameMenuHeader.setText(Strings.GAME_MENU);

		Menu gameMenu = new Menu(_shell, SWT.DROP_DOWN);
		gameMenuHeader.setMenu(gameMenu);

		MenuItem newGameItem = new MenuItem(gameMenu, SWT.PUSH);
		newGameItem.setText(Strings.NEW_MENU_ENTRY);
		newGameItem.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				newGame();
			}
		});

		MenuItem gamersItem = new MenuItem(gameMenu, SWT.PUSH);
		gamersItem.setText(Strings.GAMER_MENU_ENTRY);
		gamersItem.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				new GamerDialog(_shell).open(_players);
			}
		});

		new MenuItem(gameMenu, SWT.SEPARATOR);

		MenuItem endGameItem = new MenuItem(gameMenu, SWT.PUSH);
		endGameItem.setText(Strings.GAME_EXIT_ENTRY);
		endGameItem.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				_shell.close();
			}
		});

		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		helpMenuHeader.setText(Strings.HELP_MENU);

		Menu helpMenu = new Menu(_shell, SWT.DROP_DOWN);
		helpMenuHeader.setMenu(helpMenu);

		MenuItem helpItem = new MenuItem(helpMenu, SWT.PUSH);
		helpItem.setText(Strings.HANDBOOK_MENU_ENTRY);
		helpItem.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				new HandbookDialog(_shell).open();
			}
		});

		MenuItem aboutItem = new MenuItem(helpMenu, SWT.PUSH);
		aboutItem.setText(Strings.ABOUT_MENU_ENTRY);
		aboutItem.addSelectionListener(new SelectionAdapter() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
			 * .swt.events.SelectionEvent)
			 */
			@Override
			public void widgetSelected(SelectionEvent pE) {
				new AboutDialog(_shell).open();
			}
		});

		_shell.setMenuBar(menuBar);

		_player1Label = new Label(_shell, SWT.NONE);
		_player1Label.setText(" ");
		_player1Label.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false));

		_fieldPainter = new FieldComposite(_shell, SWT.NONE, this);
		_fieldPainter.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		_player2Label = new Label(_shell, SWT.NONE);
		_player2Label.setText(" ");
		_player2Label.setLayoutData(new GridData(SWT.CENTER, SWT.NONE, true, false));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.view.IObservingView#open()
	 */
	@Override
	public void open() {

		_shell.setSize(_fieldPainter.getSize().x + 17, _fieldPainter.getSize().y + 98);

		_shell.open();
		while (!_shell.isDisposed()) {
			if (!_display.readAndDispatch()) {
				_display.sleep();
			}
		}
		_display.dispose();

		savePlayer();
		System.exit(0);
	}

	/**
	 * 
	 */
	private void loadPlayers() {
		File file = new File("plr.dml");
		_players = new ArrayList<Player>();
		if (file.exists()) {
			FileReader reader;
			try {
				reader = new FileReader(file);
				BufferedReader bReader = new BufferedReader(reader);
				String line = null;
				while ((line = bReader.readLine()) != null) {
					String[] splited = line.split(",");
					try {
						int wins = Integer.parseInt(splited[1]);
						int remis = Integer.parseInt(splited[2]);
						int loses = Integer.parseInt(splited[3]);
						boolean isAi = splited[4].equals("true");
						Player player = new Player(splited[0], wins, loses, remis);
						player.setAI(isAi);
						_players.add(player);
					} catch (Exception e) {
						System.err.println(e);
					}
				}
				bReader.close();
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	private void savePlayer() {
		File file = new File("plr.dml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer;
		try {
			writer = new FileWriter(file);
			BufferedWriter bWriter = new BufferedWriter(writer);
			for (Player player : _players) {
				bWriter.write(player.getName());
				bWriter.write(",");
				bWriter.write(player.getWinns() + "");
				bWriter.write(",");
				bWriter.write(player.getRemis() + "");
				bWriter.write(",");
				bWriter.write(player.getLoses() + "");
				bWriter.write(",");
				bWriter.write(Boolean.toString(player.isAI()));

				bWriter.newLine();
			}

			bWriter.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hwr.damolog.view.IObservingView#setController(de.hwr.damolog.controller
	 * .IController)
	 */
	@Override
	public void setController(IController pController) {
		_controller = pController;
		_fieldPainter.setController(pController);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hwr.damolog.controller.IObserver#update(java.lang.Object)
	 */
	@Override
	public void update(Object pChanged) {
		if (pChanged instanceof Model) {
			_fieldPainter.setModel((Model) pChanged);
		}
	}

	/**
	 * 
	 */
	private void newGame() {
		SelectPlayerDialog dialog = new SelectPlayerDialog(_shell);
		if (SWT.OK == dialog.open(_players)) {
			_controller.createNewGame();
			_player1 = dialog.getPlayer1();
			_player2 = dialog.getPlayer2();
			_player1Label.setText(dialog.getPlayer1().getName());
			_player2Label.setText(dialog.getPlayer2().getName());
			_shell.layout();
		}
	}

	/**
	 * 
	 */
	public void endGame() {
		if (_controller.hasGameEnded()) {
			Player winner = null;
			Player loser = null;
			Color winnerColor = _controller.getWinner();
			if (winnerColor != null) {
				switch (winnerColor) {
				case BLACK:
					winner = _player1;
					loser = _player2;
					break;
				case WHITE:
					winner = _player2;
					loser = _player1;
					break;
				}
			}

			MessageBox message = new MessageBox(_shell, SWT.CLOSE | SWT.ICON_INFORMATION | SWT.OK);
			message.setText("Spiel beendet");

			if (winner != null) {
				message.setMessage("Gewonnen hat " + winner.getName() + "!");
				winner.addWinn();
				loser.addLose();

			} else {
				message.setMessage("Unentschieden!");
				_player1.addRemi();
				_player2.addRemi();
			}
			message.open();

		}
	}

}
