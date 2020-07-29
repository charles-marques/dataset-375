package de.hwr.damolog.controller;

import java.util.List;

import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

/**
 * 
 * Adapter die Die Befehle aus dem Controller in ein PrologQuerry umwandelt und dann mit der Schnittstelle der Prolog
 * Bibliothek arbeitet
 * 
 * @author nsmeenk
 * 
 */
public interface IPrologAdapter {

	/**
	 * Gibt alle Figuren die gezogen werden k�nnen
	 * 
	 * @return
	 */
	public List<Checker> getPossibleCheckersToPlay(Model pModel);

	/**
	 * Gibt alle Punkte, an die eine Figur zihen kann
	 * 
	 * @param pChecker
	 * @return
	 */
	public List<Point> getPossiblePointsToGoWith(Checker pChecker);

	/**
	 * Gibt alle Figuren zur�ck
	 * 
	 * @return
	 */
	public List<Checker> getCheckers();

	/**
	 * Setzt die Spieldaten zur�ck
	 */
	public void resetData();

	/**
	 * Setzt eine Figur auf ein Feld
	 * 
	 * @param pSelectedChecker
	 * @param pPoint
	 */
	public void setCheckerOnField(Checker pSelectedChecker, Point pPoint);

	/**
	 * Gibt zur�ck ob das SPiel bereits beendet ist
	 * 
	 * @return
	 */
	public boolean isGameEnded();

	/**
	 * Gibt die Farbe des Gewinners zur�ck, oder null falls unentschieden
	 * 
	 * @return
	 */
	public Color getWinnerColor();

	public boolean nextTurn(Point pPoint, boolean hasJumped);

}
