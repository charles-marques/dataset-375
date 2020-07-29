package racingManager.model;

import static racingManager.model.GrandPrix.GP_STARTED;
import static racingManager.model.GrandPrix.QUALIFYING_FINISHED;
import static racingManager.model.GrandPrix.RACE_FINISHED;
import static racingManager.model.WorldModelObserverHelper.*;

import java.util.*;

/**
 * A Formula 1 Season.
 * 
 */
public class Season {
	// WorldModel Reference
	private WorldModel worldModel;
	// Season information
	private int year;
	private int numOfDrivers;
	// Season state
	private boolean seasonFinished;
	private int curGP;
	// Season settings
	public static Hashtable<Integer, Integer> placeToPoints;
	private GrandPrix[] grandsPrix;
	// Drivers (ordered by their current championchip position, best driver at
	// index 0)
	private ArrayList<Driver> drivers;

	/**
	 * Season Constructor.
	 * 
	 * @param year
	 * @param grandPrixList
	 * @param driver
	 */
	public Season(WorldModel worldModel, int year, GrandPrix[] grandPrixList,
			ArrayList<Driver> driver, Hashtable<Integer, Integer> placeToPoints) {
		this.worldModel = worldModel;
		this.year = year;
		this.numOfDrivers = driver.size();
		this.curGP = 0;
		this.grandsPrix = grandPrixList;
		this.drivers = driver;
		this.seasonFinished = false;
		Season.placeToPoints = placeToPoints;
	}

	public void startGP() {
		grandsPrix[curGP].startGP();

		worldModel.getWorldModelObserver().updateCurGPName(getCurGPNum() + 1,
				getCurGPName());
	}

	public void startQualifying() {
		grandsPrix[curGP].startQualifying(this);
	}

	public void startRace() {
		grandsPrix[curGP].startRace(this);
		addDriverPoints();
		sortDriverChampionchip();

		// Has season finished ?
		seasonFinished = (curGP == grandsPrix.length);
	}

	/**
	 * Adds the drivers points from the race to the driver championchip points.
	 * 
	 * @param season
	 */
	private void addDriverPoints() {
		// Walk through places and add points to drivers
		for (int i = 0; i < getDrivers().size(); i++) {
			// TODO
			//Driver driver = getCurGP().getRaceResult().get(i + 1).getFirst();
			int pointsToAdd = Season.placeToPoints.get(i + 1);
			// TODO
			//driver.addPoints(pointsToAdd);
		}
	}

	public String getCurGPName() {
		return getCurGP().getGPName();
	}

	public GrandPrix getCurGP() {
		return grandsPrix[curGP];
	}

	public int getYear() {
		return year;
	}

	public int getNumOfDrivers() {
		return numOfDrivers;
	}

	public int getCurGPNum() {
		return curGP;
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}

	public Hashtable<Integer, Integer> getPlaceToPoints() {
		return placeToPoints;
	}

	public GrandPrix getGP(int numOfGP) {
		if (0 <= curGP && curGP < grandsPrix.length)
			return grandsPrix[numOfGP];
		else
			return null;
	}

	/**
	 * Sorts the driver ArrayList by the drivers current championchip positions.
	 */
	public void sortDriverChampionchip() {
		// TODO: Replace simple BubbleSort with better sorting algorithm
		for (int i = 0; i < drivers.size(); i++) {
			for (int j = i + 1; j < drivers.size(); j++) {
				// Has driver at i less points than driver at j (where i < j) ?
				if (drivers.get(i).getPoints() < drivers.get(j).getPoints()) {
					// Switch driver i and j objects in the ArrayList
					Driver tmp_i = drivers.get(i);
					drivers.set(i, drivers.get(j));
					drivers.set(j, tmp_i);
				}
			}
		}
	}

	public int getNumOfGPs() {
		return grandsPrix.length;
	}

	public void execNextEvent() {
		// Start next event depending on current GP status
		switch (getCurGP().getState()) {
		case GP_STARTED:
			startQualifying();
			break;
		case QUALIFYING_FINISHED:
			startRace();
			worldModel.getWorldModelObserver().updateCurDriverChampionchip(
					driverChampionchipColumnNamesToString(this),
					driverChampionchipToStringTable(this, drivers));
			// TODO construcot driver update
			break;
		case RACE_FINISHED:
			curGP++;
			startGP();

			break;
		default:
			System.out
					.println("Error: Unknown State: " + getCurGP().getState());
		}
	}
}
