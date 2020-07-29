package racingManager.model;

public class Tupel<K, L> {
	private K first = null;
	private L second = null;

	public Tupel(K first, L second) {
		this.first = first;
		this.second = second;
	}

	public K getFirst() {
		return first;
	}

	public void setFirst(K first) {
		this.first = first;
	}

	public L getSecond() {
		return second;
	}

	public void setSecond(L second) {
		this.second = second;
	}

}
