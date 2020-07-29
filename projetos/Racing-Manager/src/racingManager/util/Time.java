package racingManager.util;

/**
 * A time.
 * 
 */
public class Time {
	private int h;
	private int m;
	private int s;

	public Time(int h, int m, int s) {
		// TODO check valid time
		this.h = h;
		this.m = m;
		this.s = s;
	}

	public Time(int seconds) {
		// TODO check valid time
		int temp = seconds;

		this.s = temp % 60;

		temp = ((temp - this.s) / 60);
		this.m = temp % 60;

		temp = ((temp - this.m) / 60);
		this.h = temp;
	}

	public int getHours() {
		return h;
	}

	public int getMinutes() {
		return m;
	}

	public int getSeconds() {
		return s;
	}

	public void setHours(int h) {
		this.h = h;
	}

	public void setMinutes(int m) {
		this.m = m;
	}

	public void setSeconds(int s) {
		this.s = s;
	}

	public String toString() {
		return h + ":" + m + ":" + s;
	}

	// public Time add(Time that) {
	// int thisSeconds = this.timeInSeconds();
	// int thatSeconds = that.timeInSeconds();
	//
	// }

	public int timeInSeconds() {
		return h * 60 * 60 + m * 60 + s;
	}
}
