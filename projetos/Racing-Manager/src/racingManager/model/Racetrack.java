package racingManager.model;

/**
 * A Racetrack.
 * 
 */
public class Racetrack {
	// Track information
	int laps; /* Number of laps */
	int lapLength; /* Lap length in meter */

	/**
	 * Constructor of a racetrack.
	 * 
	 * @param laps
	 * @param lapLengthInMeter
	 */
	public Racetrack(int laps, int lapLengthInMeter) {
		this.laps = laps;
		this.lapLength = lapLengthInMeter;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public int getLapLength() {
		return lapLength;
	}

	public void setLapLength(int lapLength) {
		this.lapLength = lapLength;
	}

	/**
	 * Returns string representation of a racetrack.
	 */
	public String toString() {
		return "Laps: " + laps + ", Lap length (meter): " + lapLength;
	}
}
