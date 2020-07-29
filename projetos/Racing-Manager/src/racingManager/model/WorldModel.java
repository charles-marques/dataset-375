package racingManager.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static racingManager.model.GrandPrix.*;
import static racingManager.model.WorldModelObserverHelper.driverChampionchipColumnNamesToString;
import static racingManager.model.WorldModelObserverHelper.driverChampionchipToStringTable;

import au.com.bytecode.opencsv.*;

public class WorldModel {
	// Observer Constants
	// TODO: constant needed ?
	private static final int CHANGED_RacingManager_PARAMETERS = 1;
	// Relative path to CSV files
	private static final String RELATIV_CSV_PATH = "settings/";
	private static final String SEASON_CSV_FILE = "Season_2011.csv";
	private String DRIVER_CSV_FILE;
	private String GRANDS_PRIX_CSV_FILE;
	private String POINT_MAPPING_CSV_FILE;
	// Loaded CSV entries
	private List<String[]> seasonEntries;
	private List<String[]> driverEntries;
	private List<String[]> gpEntries;
	private List<String[]> pointMappingEntries;
	// World Model parameters
	private String gameName;
	private FormulaOneManager formulaOneManager;
	private Season season;

	// Observer references
	WorldModelObserver observer = null;

	/**
	 * Constructor of a Game model.
	 * 
	 * @param playerName
	 * @param playerTeamName
	 */
	public WorldModel(String playerName, String playerTeamName) {
		gameName = "F1 Manager Simulation";
		season = createSeason();
		// TODO: Drivers for player should be selected with help of GUI or CSV,
		// not here
		formulaOneManager = new FormulaOneManager(playerName, playerTeamName,
				season.getDrivers().get(1), season.getDrivers().get(2));
	}

	public WorldModelObserver getWorldModelObserver() {
		return observer;
	}

	private String getGameName() {
		return this.gameName;
	}

	private FormulaOneManager getFormulaOneManager() {
		return formulaOneManager;
	}

	private Season getSeason() {
		return season;
	}

	public void addObserver(WorldModelObserver observer) {
		this.observer = observer;

		// Update observer with current values
		observer.updateGameName(gameName);
		observer.updateFormulaOneManagerName(formulaOneManager.getName());
		observer.updateFormulaOneManagerCash(formulaOneManager.getCash());
		observer.updateFormulaOneManagerTeamName(formulaOneManager
				.getTeamName());
		updateDriverOneName();
		updateDriverTwoName();
		observer.updateSeasonYear(season.getYear());
		observer.updatePlaceToPoints(season.getPlaceToPoints());
		observer.updateCurGPName(season.getCurGPNum() + 1,
				season.getCurGPName());
		observer.updateCurDriverChampionchip(
				driverChampionchipColumnNamesToString(season),
				driverChampionchipToStringTable(season, season.getDrivers()));
		// TODO update further information
	}

	private Season createSeason() {
		seasonEntries = loadCSVFile(RELATIV_CSV_PATH + SEASON_CSV_FILE);

		// Read season informaion (first line of csv file are headers)
		int year = Integer.valueOf(seasonEntries.get(1)[1]);
		DRIVER_CSV_FILE = seasonEntries.get(2)[1];
		GRANDS_PRIX_CSV_FILE = seasonEntries.get(3)[1];
		POINT_MAPPING_CSV_FILE = seasonEntries.get(4)[1];

		// Load other setting files
		driverEntries = loadCSVFile(RELATIV_CSV_PATH + DRIVER_CSV_FILE);
		gpEntries = loadCSVFile(RELATIV_CSV_PATH + GRANDS_PRIX_CSV_FILE);
		pointMappingEntries = loadCSVFile(RELATIV_CSV_PATH
				+ POINT_MAPPING_CSV_FILE);

		// Create Driver objects (first line of csv file are headers)
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		for (int i = 1; i < driverEntries.size(); i++) {
			String[] driverEntry = driverEntries.get(i);
			drivers.add(new Driver(driverEntry[1], driverEntry[0], Integer
					.valueOf(driverEntry[2])));
		}

		// Create Grand Prix objects (first line of csv file are headers)
		GrandPrix[] grandsPrix = new GrandPrix[gpEntries.size()-1];
		for (int i = 1; i < gpEntries.size(); i++) {
			String[] entry = gpEntries.get(i);
			grandsPrix[i - 1] = new GrandPrix(this, entry[0], entry[1],
					Integer.parseInt(entry[3]), Integer.parseInt(entry[2]));
		}

		// Create point mapping (first line of CSV file are headers)
		Hashtable<Integer, Integer> pointMapping = new Hashtable<Integer, Integer>();
		for (int i = 1; i < pointMappingEntries.size(); i++) {
			String[] pointMappingEntry = pointMappingEntries.get(i);
			pointMapping.put(Integer.valueOf(pointMappingEntry[0]),
					Integer.valueOf(pointMappingEntry[1]));
		}
		// Set other places to 0
		for (int i = pointMapping.size() + 1; i < drivers.size() + 1; i++)
			pointMapping.put(i, 0);

		return new Season(this, year, grandsPrix, drivers, pointMapping);
	}

	/**
	 * Loads the given CSV file and returns the result as String matrix.
	 * 
	 * @param filePath
	 * @return
	 */
	private List<String[]> loadCSVFile(String filePath) {
		List<String[]> entries = null;
		try {
			// Read Season CSV file
			CSVReader csvReader = new CSVReader(new FileReader(filePath));
			entries = csvReader.readAll();
		} catch (Exception e) {
			// TODO: Print error messages in GUI (Error Message Class ?)
			System.err.println(e.getMessage());
		}
		return entries;
	}

	/* * * * * Information methods * * * * */

	/**
	 * Returns the current season state as integer array, where [0] = year, [1]
	 * = current GP number, [2] = current GP state.
	 * 
	 * @return
	 */
	private int[] getSeasonState() {
		int[] seasonState = new int[3];
		seasonState[0] = season.getYear();
		seasonState[1] = season.getCurGPNum();
		if (0 <= seasonState[1] && seasonState[1] < season.getNumOfGPs())
			seasonState[2] = season.getGP(seasonState[1]).getState();
		else
			seasonState[2] = -1;
		return seasonState;
	}

	/* * * * * Controlling methods * * * * */

	public void execNextEvent() {

		// TODO execute next event of _current_ season
		season.execNextEvent();
		// TODO call correct observer methods

		// TODO season finished -> start next season

		// ****

		// TODO switch case which even is next !
		// season.startGP();
		// observer.updateCurGPName(season.getCurGPNum() + 1,
		// season.getCurGPName());
		//
		// season.startQualifying();
		// season.startRace();
		//
		// observer.updateCurDriverChampionchip(season.getCurDriverChampionship());

		// TODO eval state and start next event

		// TODO: Distinguish between race start and qualifying start and
		// // print "Start Qualifying" or "Start Race" on button ?
		// // TODO: Check states ! Is a qualifying/race executable ?
		// int[] seasonState = con.getSeasonState();
		// switch (seasonState[2]) {
		// case -1:
		// System.out.println("Started first GP");
		// con.startGP();
		// break;
		// case GrandPrix.NOT_STARTED:
		// System.out.println("Started GP: " + seasonState[1]);
		// con.startGP();
		// break;
		// case GrandPrix.QUALIFYING:
		// System.out.println("Started Quali GP: " + seasonState[1]);
		// con.startQualifying();
		// break;
		// case GrandPrix.RACE:
		// System.out.println("Started Race GP: " + seasonState[1]);
		// con.startRace();
		// break;
		// case GrandPrix.FINISHED:
		// System.out.println("Start next GP: " + (seasonState[1] + 1));
		// con.startGP();
		// break;
		// }
	}

	/*** Helper methods for observer updated ***/

	private void updateDriverOneName() {
		Driver driverOne = formulaOneManager.getDriverOne();
		observer.updateFormulaOneManagerDriverOneName(driverOne.getForename(),
				driverOne.getLastName());
	}

	private void updateDriverTwoName() {
		Driver driverTwo = formulaOneManager.getDriverTwo();
		observer.updateFormulaOneManagerDriverTwoName(driverTwo.getForename(),
				driverTwo.getLastName());
	}
}