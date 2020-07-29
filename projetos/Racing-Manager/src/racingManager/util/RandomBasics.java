package racingManager.util;

import java.util.*;

/**
 * Basic random functions.
 * 
 */
public final class RandomBasics {
	private static final Random r = new Random();

	/**
	 * Suppress default constructor for noninstantiability.
	 */
	private RandomBasics() {
		// this constructor will never be invoked
	}

	/**
	 * Creates random integer i with "0 <= i < high".
	 * 
	 * @param high
	 * @return
	 */
	public static int createRandomNumber(int high) {
		return r.nextInt(high);
	}

	/**
	 * Creates random integer i with "low <= i < high".
	 * 
	 * @param low
	 * @param high
	 * @return
	 */
	public static int randInt(int low, int high) {
		return r.nextInt(high - low) + low;
	}
}