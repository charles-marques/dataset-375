package racingManager.model;

import static racingManager.model.WorldModelObserverHelper.*;

import java.util.*;

import racingManager.model.calc.QualifyingCalc;
import racingManager.model.calc.RaceCalc;
import racingManager.model.history.QualifyingResult;
import racingManager.model.history.RaceResult;
import racingManager.util.Time;

/**
 * A Formula 1 Grand Prix.
 * 
 */
public class GrandPrix {
	// World Model Reference
	private WorldModel worldModel;

	// Grand Prix state constants
	public static final int GP_STARTED = 0;
	public static final int QUALIFYING_FINISHED = 1;
	public static final int RACE_FINISHED = 2;

	// Grand Prix information
	private String nation;
	private String gpName;
	// TODO: Read country, grand prix name from CSV
	private Racetrack racetrack;
	private int state;

	// Grand Prix results
	QualifyingResult qualifyingResult;
	RaceResult raceResult;

	/**
	 * Grand Prix Constructor.
	 * 
	 * @param nation
	 * @param gpName
	 * @param laps
	 * @param lapLengthInMeter
	 */
	public GrandPrix(WorldModel worldModel, String nation, String gpName,
			int laps, int lapLengthInMeter) {
		this.worldModel = worldModel;
		this.nation = nation;
		this.gpName = gpName;
		this.racetrack = new Racetrack(laps, lapLengthInMeter);
		this.state = GP_STARTED;
		this.qualifyingResult = null;
		this.raceResult = null;
	}

	public Racetrack getRacetrack() {
		return racetrack;
	}
	
	protected void startGP() {
		this.state = GP_STARTED;
		
		worldModel.getWorldModelObserver().updateGPState(GP_STARTED);
	}

	protected void startQualifying(Season season) {
		// TODO
		// qualifyingResult = QualifyingCalc.calcResult(season, this);
		state = QUALIFYING_FINISHED;
		
		// TODO
		// worldModel.getWorldModelObserver().updateGPQualifyingResults(
		// 		qualifyingResultToStringTable(qualifyingResult));
		worldModel.getWorldModelObserver().updateGPState(QUALIFYING_FINISHED);
	}

	protected void startRace(Season season) {
		// TODO
		// raceResult = RaceCalc.calcResult(season, this);
		state = RACE_FINISHED;

		// TODO
		//worldModel.getWorldModelObserver().updateGPRaceResults(
		//		raceResultToStringTable(raceResult));
		worldModel.getWorldModelObserver().updateGPState(RACE_FINISHED);
	}

	protected String getGPName() {
		return gpName;
	}

	protected int getState() {
		return state;
	}

	protected RaceResult getRaceResult() {
		return raceResult;
	}

	protected QualifyingResult getQualifyingResult() {
		return qualifyingResult;
	}
}
