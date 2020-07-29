package racingManager.model;

import java.util.Hashtable;

import racingManager.util.Time;

/**
 * An observer of a world model object.
 * 
 */
public interface WorldModelObserver {

	/*** Game ***/

	public void updateGameName(String gameName);

	/*** Formula One Manager ***/

	public void updateFormulaOneManagerName(String formulaOneManagerName);
	
	public void updateFormulaOneManagerCash(int cash);

	public void updateFormulaOneManagerTeamName(String teamName);

	public void updateFormulaOneManagerDriverOneName(String forename,
			String familyName);

	public void updateFormulaOneManagerDriverTwoName(String forename,
			String familyName);

	/*** Season ***/

	public void updateSeasonYear(int year);

	public void updateCurGPName(int number, String gpName);

	public void updateNumOfDrivers(int numOfDrivers);

	public void updatePlaceToPoints(Hashtable<Integer, Integer> placeToPoints);
	
	public void updateCurDriverChampionchip(String[] columnNames, String[][] curDriverChampionchip);
	
	/*** Grand Prix ***/
	
	public void updateGPQualifyingResults(String[][] qualifyingResult);

	public void updateGPRaceResults(String[][] raceResult);
	
	public void updateGPState(int gpState); 
	
	// TODO Constructor championchip
	// TODO Place to Cash
	// TODO Teams/Drivers
}