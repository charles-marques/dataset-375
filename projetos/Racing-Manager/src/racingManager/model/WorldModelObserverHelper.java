package racingManager.model;

import java.util.ArrayList;
import java.util.Hashtable;

import racingManager.util.Time;

public class WorldModelObserverHelper {

	public static String[][] qualifyingResultToStringTable(
			Hashtable<Integer, Tupel<Driver, Time>> qualifyingResult) {
		int numOfDrivers = qualifyingResult.size();
		String[][] placeResults = new String[numOfDrivers][5];

		for (int i = 0; i < numOfDrivers; i++) {
			Driver driver = qualifyingResult.get(i + 1).getFirst();
			placeResults[i][0] = (i + 1) + ".";
			placeResults[i][1] = driver.getForename();
			placeResults[i][2] = driver.getLastName();
			placeResults[i][3] = driver.getTeamName();
			placeResults[i][4] = qualifyingResult.get(i + 1).getSecond()
					.toString();
		}
		return placeResults;
	}

	public static String[][] raceResultToStringTable(
			Hashtable<Integer, Tupel<Driver, Time>> raceResult) {
		int numOfDrivers = raceResult.size();
		String[][] placeResults = new String[numOfDrivers][5];
		for (int i = 0; i < numOfDrivers; i++) {
			Driver driver = raceResult.get(i + 1).getFirst();
			placeResults[i][0] = (i + 1) + ".";
			placeResults[i][1] = driver.getForename();
			placeResults[i][2] = driver.getLastName();
			placeResults[i][3] = driver.getTeamName();
			placeResults[i][4] = String
					.valueOf(Season.placeToPoints.get(i + 1));
		}
		return placeResults;
	}

	/**
	 * Returns driver championchip information as string matrix.
	 * 
	 * @return
	 */
	public static String[][] driverChampionchipToStringTable(Season season,
			ArrayList<Driver> drivers) {
		String[][] driverChampionship = new String[drivers.size()][5];
		for (int i = 0; i < drivers.size(); i++) {
			Driver driver = drivers.get(i);
			driverChampionship[i][0] = (i + 1) + ".";
			driverChampionship[i][1] = driver.getForename();
			driverChampionship[i][2] = driver.getLastName();
			driverChampionship[i][3] = driver.getTeamName();
			driverChampionship[i][4] = String.valueOf(driver.getPoints());
		}
		

		// for(int i = 0; i < drivers.size(); i++) {
		// for(int j = 0; j < season.getNumOfGPs(); j++) {
		// GrandPrix gp = season.getGP(j);
		// driverChampionship[i][j+5] = gp.getRaceResult().get(i)
		// }
		// }
		return driverChampionship;
	}

	public static String[] driverChampionchipColumnNamesToString(Season season) {
		String[] columnNames = new String[5 + season.getNumOfGPs()];
		columnNames[0] = "Place";
		columnNames[1] = "Forename";
		columnNames[2] = "Name";
		columnNames[3] = "Team";
		columnNames[4] = "Points";
		for (int i = 0; i < season.getNumOfGPs(); i++) {
			columnNames[i + 5] = season.getGP(i).getGPName();
		}
		return columnNames;
	}
}
