package de.hwr.damolog.model;

import java.util.ArrayList;
import java.util.List;

import de.hwr.damolog.controller.Subject;

/**
 * 
 * Speicherung des Datenmodells und die Operationen die der Controlle daruf
 * ausführt
 * 
 * @author nsmeenk
 * 
 */
public class Model extends Subject {

	private List<Checker> _checkers = new ArrayList<Checker>();

	public static final int FIELD_SIZE = 8;

	/**
	 * Konstruktor
	 */
	public Model() {
		// NOI
	}

	/**
	 * Setzt das Feld in den Ausgangszustand zurück
	 */
	public void resetField() {
		_checkers.clear();
		for (int y = 0; y < FIELD_SIZE; y++) {
			for (int x = 0; x < FIELD_SIZE; x++) {
				if (y < FIELD_SIZE / 2 - 1) {
					if (isBlackField(x, y)) {
						Checker newChecker = new Checker(Color.BLACK, new Point(x, y));
						_checkers.add(newChecker);
					}
				}
				if (y > FIELD_SIZE / 2) {
					if (isBlackField(x, y)) {
						Checker newChecker = new Checker(Color.WHITE, new Point(x, y));
						_checkers.add(newChecker);
					}
				}
			}
		}
		updateModel();
	}

	/**
	 * @param pX
	 * @param pY
	 * @return
	 */
	private boolean isBlackField(int pX, int pY) {
		return ((pX + pY) % 2) == 0;
	}

	/**
	 * @return the checkers
	 */
	public Checker[] getCheckers() {
		return _checkers.toArray(new Checker[0]);
	}

	/**
	 * Updated das Model
	 */
	public void updateModel() {
		updateObserver(this);
	}

	/**
	 * Löscht einen Spielstein
	 * 
	 * @param pChecker
	 */
	public void delete(Checker pChecker) {
		_checkers.remove(pChecker);
		updateModel();
	}

	/**
	 * @param pPoint
	 */
	public Checker getCheckerAtPosition(Point pPoint) {
		for (Checker checker : _checkers) {
			if (checker.getPlace().equals(pPoint)) {
				return checker;
			}
		}
		return null;
	}

	/**
	 * @param pCheckers
	 *            the checkers to set
	 */
	public void setCheckers(Checker[] pCheckers) {
		_checkers.clear();
		for (Checker checker : pCheckers) {
			_checkers.add(checker);
		}
		updateModel();
	}
}
