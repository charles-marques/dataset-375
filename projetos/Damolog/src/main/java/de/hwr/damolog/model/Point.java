package de.hwr.damolog.model;

/**
 * 
 * Speichert Koordinaten
 * 
 * @author nsmeenk
 * 
 */
public class Point {

	private int _x;

	private int _y;

	/**
	 * Konstruktor für die Position auf einem Feld
	 * 
	 * @param pX
	 *            X
	 * @param pY
	 *            Y
	 */
	public Point(int pX, int pY) {
		_x = pX;
		_y = pY;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return _x;
	}

	/**
	 * @param pX
	 *            the x to set
	 */
	public void setX(int pX) {
		_x = pX;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return _y;
	}

	/**
	 * @param pY
	 *            the y to set
	 */
	public void setY(int pY) {
		_y = pY;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _x;
		result = prime * result + _y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (_x != other._x)
			return false;
		if (_y != other._y)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + _x + "|" + _y + ")";
	}
}
