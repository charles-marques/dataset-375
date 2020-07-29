package racingManager.model.calc;

import java.util.*;

import racingManager.model.Driver;
import racingManager.model.GrandPrix;
import racingManager.model.Season;
import racingManager.model.Tupel;
import racingManager.model.history.RaceResult;
import racingManager.model.history.RaceResultDriverRecord;
import racingManager.util.RandomBasics;
import racingManager.util.Time;

/**
 * Race Calculation.
 * 
 */
public class RaceCalc {

	public static Hashtable<Integer, Tupel<Driver, Time>> calcResult(
			Season season, GrandPrix gp) {
//
//		// TODO: Do some _correct_ calculation here (at the moment, a random
//		// order is calculated for testing reason; no fastest lap is
//		// calculated)
//		ArrayList<Driver> drivers = season.getDrivers();
//		for (int i = 0; i < drivers.size(); i++) {
//			int randPlace = RandomBasics.randInt(1, drivers.size() + 1);
//			// Search for place which is not already used
//			while (raceResult.containsKey(randPlace))
//				randPlace = RandomBasics.randInt(1, drivers.size() + 1);
//			// Add driver and place to the result
//			raceResult.put(randPlace, new Tupel<Driver, Time>(drivers.get(i),
//					new Time(0, 0, 0)));
//		}
//
//		RaceResult raceResult = new RaceResult();
//		return raceResult;
		return null;
	}

	private static RaceResultDriverRecord calcRaceResultDriverRecord(
			Driver driver, GrandPrix gp) {
		ArrayList<Time> laps = new ArrayList<Time>(0);
		for(int i = 0; i < gp.getRacetrack().getLaps(); i++) {
			laps.add(calcLapTime(driver, gp));
		}
		
		// calculate race time
		
		// TODO !
		return null;		
	}

	private static Time calcLapTime(Driver driver, GrandPrix gp) {
		// TODO Do some real calculation
		return new Time(RandomBasics.randInt(1, 10),
				RandomBasics.randInt(1, 10), RandomBasics.randInt(1, 10));
	}
}
