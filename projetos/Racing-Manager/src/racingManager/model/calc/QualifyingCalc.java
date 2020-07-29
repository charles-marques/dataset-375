package racingManager.model.calc;

import java.util.*;

import racingManager.model.Driver;
import racingManager.model.GrandPrix;
import racingManager.model.Season;
import racingManager.model.Tupel;
import racingManager.util.RandomBasics;
import racingManager.util.Time;

/**
 * Qualifying Calculation.
 * 
 */
public class QualifyingCalc {

	public static Hashtable<Integer, Tupel<Driver, Time>> calcResult(
			Season season, GrandPrix gp) {
		Hashtable<Integer, Tupel<Driver, Time>> raceResult = new Hashtable<Integer, Tupel<Driver, Time>>(
				0);

		// TODO: Do some _correct_ calculation here (at the moment, a random
		// order is calculated for testing reason; no fastest lap is
		// calculated)
		ArrayList<Driver> drivers = season.getDrivers();
		for (int i = 0; i < drivers.size(); i++) {
			int randPlace = RandomBasics.randInt(1, drivers.size() + 1);
			// Search for place which is not already used
			while (raceResult.containsKey(randPlace))
				randPlace = RandomBasics.randInt(1, drivers.size() + 1);
			// Add driver and place to the result
			raceResult.put(randPlace, new Tupel<Driver, Time>(drivers.get(i),
					new Time(0, 0, 0)));
		}
		return raceResult;
	}
}
