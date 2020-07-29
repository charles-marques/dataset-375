package racingManager.model.history;

import racingManager.model.Driver;
import racingManager.util.Time;

public class QualifyingResultDriverRecord {
	Driver driver;
	int place;
	Time fastestLap;

	public QualifyingResultDriverRecord(Driver driver, int place,
			Time fastestLap) {
		this.driver = driver;
		this.place = place;
		this.fastestLap = fastestLap;
	}
}
