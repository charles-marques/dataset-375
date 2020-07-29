package racingManager.gui;

import java.awt.BorderLayout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import racingManager.controller.Controller;
import racingManager.model.*;
import racingManager.util.Util;

public class MainFrame extends JFrame implements WorldModelObserver {
	private static final long serialVersionUID = 1L;

	// Player information
	// TODO Read this from player via gui dialog
	private String playerName = "Max";
	private String playerTeamName = "Mercedes GP";

	// Controller reference
	private Controller con = null;

	// Enumeration for the states of the game.
	private static enum State {
		INITIALIZED, PLAYING, PAUSED, GAMEOVER, DESTROYED
	}

	private static State state; // current state of the game

	// Panels
	private GameJPanel gameJPanel;
	private ControlJPanel controlJPanel;
	private TableJPanel tableJPanel;

	// Dialog references
	private DriverChampionshipFrame driverChampionChipFrame = null;
	private ConstructorChampionchipFrame constructorChampionchipFrame = null;
	private TeamsDriversFrame teamsDriversFrame = null;
	private PlaceToPointsFrame placeToPointsFrame = null;
	private PlaceToCashFrame placeToCashFrame = null;

	// private ConstructorChampionchipFrame constructorChampionChipFrame = null;
	// private TeamsDriversFrame teamsDriversFrame = null;
	// private PlaceToPointsFrame placeToPointsFrame = null;
	// private PlaceToCashFrame placeToCashFrame = null;

	public MainFrame() {
		// Create GUI elements
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		gameJPanel = new GameJPanel();
		cp.add(gameJPanel, BorderLayout.WEST);

		controlJPanel = new ControlJPanel();
		cp.add(controlJPanel, BorderLayout.SOUTH);

		tableJPanel = new TableJPanel();
		cp.add(tableJPanel, BorderLayout.CENTER);

		// Initialize frame objects
		driverChampionChipFrame = new DriverChampionshipFrame();
		constructorChampionchipFrame = new ConstructorChampionchipFrame();
		teamsDriversFrame = new TeamsDriversFrame();
		placeToPointsFrame = new PlaceToPointsFrame();
		placeToCashFrame = new PlaceToCashFrame();

		// Initialize Model and Controller
		gameInit();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setTitle("");
		this.setVisible(true);
	}

	public void gameInit() {
		con = new Controller(this, playerName, playerTeamName);
		con.addObserver(this);
		state = State.INITIALIZED;
	}

	// Start the game.
	public void gameStart() {

		// Regenerate the game objects for a new game
		if (state == State.INITIALIZED || state == State.GAMEOVER) {
			// Setze Werte der Welt wieder auf Anfang.
			// Kein neues Welt Objekt erzeugen nötig.
			// world.regenerate();
			state = State.PLAYING;
		}

		if (state == State.GAMEOVER) {
			// gameInit();
		}
		if (state == State.PLAYING) {
			con.execNextEvent();
		}
		repaint();
	}

	// Shutdown des Spiels, clean up code welcher nur einmal läuft.
	public void gameShutdown() {

	}

	// Custom drawing panel, written as an inner class.
	class GameJPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private JLabel lblFormulaOneManagerName;
		private JLabel lblContentFormulaOneManagerName;

		private JLabel lblCash;
		private JLabel lblContentCash;

		private JLabel lblTeamName;
		private JLabel lblContentTeamName;

		private JLabel lblDriverOneName;
		private JLabel lblContentDriverOneName;

		private JLabel lblDriverTwoName;
		private JLabel lblContentDriverTwoName;

		private JLabel lblSeason;
		private JLabel lblContentSeason;

		private JLabel lblCurrentGP;
		private JLabel lblContentCurrentGP;

		private JLabel lblGPStatus;
		private JLabel lblContentGPStatus;

		public GameJPanel() {
			this.setLayout(new GridLayout(8, 2));
			this.setPreferredSize(new Dimension(200, 430));

			lblFormulaOneManagerName = new JLabel("Team-Manager:");
			lblContentFormulaOneManagerName = new JLabel("");

			lblCash = new JLabel("Cash:");
			lblContentCash = new JLabel("");

			lblTeamName = new JLabel("Team:");
			lblContentTeamName = new JLabel("");

			lblDriverOneName = new JLabel("Driver 1:");
			lblContentDriverOneName = new JLabel("");

			lblDriverTwoName = new JLabel("Driver 2:");
			lblContentDriverTwoName = new JLabel("");

			lblSeason = new JLabel("Season:");
			lblContentSeason = new JLabel("");

			lblCurrentGP = new JLabel("Grand Prix:");
			lblContentCurrentGP = new JLabel("");

			lblGPStatus = new JLabel("GP Status:");
			lblContentGPStatus = new JLabel("");

			add(lblFormulaOneManagerName);
			add(lblContentFormulaOneManagerName);

			add(lblCash);
			add(lblContentCash);

			add(lblTeamName);
			add(lblContentTeamName);

			add(lblDriverOneName);
			add(lblContentDriverOneName);

			add(lblDriverTwoName);
			add(lblContentDriverTwoName);

			add(lblSeason);
			add(lblContentSeason);

			add(lblCurrentGP);
			add(lblContentCurrentGP);

			add(lblGPStatus);
			add(lblContentGPStatus);

			this.setVisible(true);
		}

		public void updateFormulaOneManagerName(String formulaOneManagerName) {
			lblContentFormulaOneManagerName.setText(formulaOneManagerName);
		}

		public void updateFormulaOneManagerCash(int cash) {
			lblContentCash.setText(String.valueOf(cash));
		}

		public void updateFormulaOneManagerTeamName(String teamName) {
			lblContentTeamName.setText(teamName);
		}

		public void updateFormulaOneManagerDriverOneName(String forename,
				String familyName) {
			lblContentDriverOneName.setText(forename + " " + familyName);
		}

		public void updateFormulaOneManagerDriverTwoName(String forename,
				String familyName) {
			lblContentDriverTwoName.setText(forename + " " + familyName);
		}

		public void updateSeason(int year) {
			lblContentSeason.setText(String.valueOf(year));
		}

		public void updateCurrentGP(int number, String name) {
			lblContentCurrentGP.setText("(" + number + ") " + name);
		}

		public void updateGPStatus() {
			// TODO
			lblContentGPStatus.setText("test");
		}
	}

	// Custom drawing panel, written as an inner class.
	class TableJPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		private DefaultTableModel dtm;
		JTable resultTable;

		public TableJPanel() {
			dtm = new DefaultTableModel(0, 0);
			resultTable = new JTable(dtm);
			add(new JScrollPane(resultTable), BorderLayout.CENTER);
			this.setVisible(true);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // einfach immer einfügen, hilft
										// Hintergrundverunreinigungen
										// automatisch zu korrigieren

			// dtm.setDataVector(game.getSeason().getRaceResult((game.getSeason().getCurrentGrandPrixNumber()-1)),
			// columnNames);
			// resultTable.set.getSeason().getLastRaceResult(), columnNames);
			dtm.fireTableDataChanged();
			dtm.fireTableStructureChanged();
		}

		public void updateTableContent(String[] columnNames, String[][] content) {
			// Create new Table model
			resultTable.setModel(new DefaultTableModel(columnNames,
					content.length));
			// Fill new table model
			for (int i = 0; i < content.length; i++)
				for (int j = 0; j < content[i].length; j++) {
					resultTable.setValueAt(content[i][j], i, j);
				}
		}
		
		public void clearTable() {
			resultTable.setModel(new DefaultTableModel(0, 0));
		}
	}

	// Custom drawing panel, written as an inner class.
	class ControlJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private JButton btnStart;
		private JComboBox cbInformation;

		public ControlJPanel() {
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

			btnStart = new JButton("Start");
			btnStart.setEnabled(true);
			add(btnStart);
			btnStart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch (state) {
					case INITIALIZED:
					case GAMEOVER:
					case PLAYING:
						gameStart();
						break;
					}
				}
			});

			String[] names = { "Driver Championchip",
					"Constructor Championship", "Place -> Cash",
					"Place -> Points", "Teams / Drivers" };
			cbInformation = new JComboBox(names);
			cbInformation.setEnabled(true);
			add(cbInformation);
			cbInformation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switch (cbInformation.getSelectedIndex()) {
					case 0: {
						driverChampionChipFrame.setVisible(true);
						break;
					}
					case 1: {
						constructorChampionchipFrame.setVisible(true);
						break;
					}
					case 2: {
						placeToCashFrame.setVisible(true);
						break;
					}
					case 3: {
						placeToPointsFrame.setVisible(true);
						break;
					}
					case 4: {
						teamsDriversFrame.setVisible(true);
						break;
					}
					}
				}
			});
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g); // einfach immer einfügen, hilft
										// Hintergrundverunreinigungen
										// automatisch zu korrigieren
		}
	}

	/*** Observer Methods ***/

	/*** Game ***/

	public void updateGameName(String gameName) {
		super.setTitle(gameName);
	}

	/*** Formula One Manager ***/

	public void updateFormulaOneManagerName(String formulaOneManagerName) {
		gameJPanel.updateFormulaOneManagerName(formulaOneManagerName);
	}

	public void updateFormulaOneManagerCash(int cash) {
		gameJPanel.updateFormulaOneManagerCash(cash);
	}

	public void updateFormulaOneManagerTeamName(String teamName) {
		gameJPanel.updateFormulaOneManagerTeamName(teamName);
	}

	public void updateFormulaOneManagerDriverOneName(String forename,
			String familyName) {
		gameJPanel.updateFormulaOneManagerDriverOneName(forename, familyName);
	}

	public void updateFormulaOneManagerDriverTwoName(String forename,
			String familyName) {
		gameJPanel.updateFormulaOneManagerDriverTwoName(forename, familyName);
	}

	/*** Season ***/

	public void updateSeasonYear(int year) {
		gameJPanel.updateSeason(year);
	}

	public void updateCurGPName(int number, String gpName) {
		gameJPanel.updateCurrentGP(number, gpName);
	}

	public void updateNumOfDrivers(int numOfDrivers) {
		// TODO
	}

	public void updatePlaceToPoints(Hashtable<Integer, Integer> placeToPoints) {
		placeToPointsFrame.updateTableContent(Util
				.HashtableToStringArray(placeToPoints));
	}

	public void updateCurDriverChampionchip(String[] columnNames, String[][] curDriverChampionchip) {
		driverChampionChipFrame.updateTableContent(columnNames,curDriverChampionchip);
	}

	/*** Grand Prix ***/

	public void updateGPQualifyingResults(String[][] qualifyingResult) {
		String[] columnNames = { "Place", "Forename", "Last Name", "Team",
				"Fastest Lap" };
		tableJPanel.updateTableContent(columnNames, qualifyingResult);
	}

	public void updateGPRaceResults(String[][] raceResult) {
		String[] columnNames = { "Place", "Forename", "Last Name", "Team",
				"Points" };
		tableJPanel.updateTableContent(columnNames, raceResult);
	}

	public void updateGPState(int gpState) {
		switch (gpState) {
		case GrandPrix.GP_STARTED:
			tableJPanel.clearTable();
		case GrandPrix.QUALIFYING_FINISHED:
			// Do nothing
			break;
		case GrandPrix.RACE_FINISHED:
			// Do nothing
			break;
		default:
			System.out.println("Error: Unknown state " + gpState);
		}
	}
}
