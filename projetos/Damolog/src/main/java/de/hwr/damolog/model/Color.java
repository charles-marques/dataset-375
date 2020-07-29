package de.hwr.damolog.model;

/**
 * 
 * Definition der Farben Schwarz und Wei� f�r die Spielsteine
 * 
 * @author nsmeenk
 * 
 */
public enum Color {

	BLACK,
	WHITE;

	/**
	 * Gbt die Farbwerte f�r eine RGB Verteilung zur�ck
	 * 
	 * @return Farbwerte in einem Array mit 3 Zahlen (RGB)
	 */
	public int[] getRGB() {
		if (this == BLACK) {
			int[] rgb = { 0, 0, 0 };
			return rgb;
		} else if (this == WHITE) {
			int[] rgb = { 255, 255, 255 };
			return rgb;
		}
		return null;
	}

	public Color switchColor() {
		return this == BLACK ? WHITE : BLACK;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		if (this == BLACK)
			return "black";
		else if (this == WHITE)
			return "white";
		return null;
	}

}
