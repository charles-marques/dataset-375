package de.hwr.damolog.controller;

import java.util.List;

import de.hwr.damolog.model.Checker;
import de.hwr.damolog.model.Color;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.model.Point;

/**
 * 
 * Interface um einen Controller zu definieren
 * 
 * @author nsmeenk
 * 
 */
public interface IController {

	/**
	 * Setzt das Modell
	 * 
	 * @param pModel
	 */
	public void setModel(Model pModel);

	/**
	 * gibt eine Liste der Figuren zur�ck, die gesetzt werden k�nnen
	 * 
	 * @return Liste der setzbaren Figuren
	 */
	public List<Checker> getPossibleCheckersToPlay();

	/**
	 * Gibt eine Liste der m�glichen orte an, an die eine Figur gesetzt werden
	 * kann
	 * 
	 * @param pFigure
	 *            Figur von der die Orte angegeben werden sollen
	 * @return Ote an die die Figur gehen kann
	 */
	public List<Point> getPossiblePointsToGoWith(Checker pFigure);

	/**
	 * Gibt die Figur zur�ck, die gerade ausgew�hlt ist
	 * 
	 * @return
	 */
	public Checker getSelectedChecker();

	/**
	 * wird vond er View aufgerufen, wenn ein Feld geklickt bzw angegeben wird
	 * 
	 * @param pPoint
	 */
	public void useField(Point pPoint);

	/**
	 * erstellt ein neues Spiel
	 */
	public void createNewGame();

	/**
	 * gibt zur�ck ob das spiel beendet ist
	 */
	public boolean hasGameEnded();

	/**
	 * gibt die gewinnerfarbe zur�ck oder null wenn unentschieden
	 */
	public Color getWinner();
}
