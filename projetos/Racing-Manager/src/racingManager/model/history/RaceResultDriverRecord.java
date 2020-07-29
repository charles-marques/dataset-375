package racingManager.model.history;

import java.util.ArrayList;

import racingManager.model.Driver;
import racingManager.util.Time;

public class RaceResultDriverRecord {
	Driver driver;
	int place;
	int points;
	Time time;
	ArrayList<Time> laps;
	
	public RaceResultDriverRecord(Driver driver, int place, int points, Time time, ArrayList<Time> laps){
		this.driver = driver;
		this.place = place;
		this.points = points;
		this.time = time;
		this.laps = laps;
	}
}
