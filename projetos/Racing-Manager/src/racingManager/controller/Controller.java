package racingManager.controller;

import java.util.*;

import racingManager.gui.*;
import racingManager.model.WorldModel;
import racingManager.model.WorldModelObserver;

//TODO: set other model classes private, so that GUI can not use them -> no private classes allowed ? 

/**
 * Connector between Model and GUI.
 * 
 * The class provides controlling and information methods which can be used by
 * the GUI to control the game or to inform the player about the model state.
 * 
 * ATTENTION: Only these methods are allowed to be used by the GUI to interact
 * with the model.
 * 
 */
public class Controller {
	private WorldModel model;

	public Controller(MainFrame mainFrame, String playerName,
			String playerTeamName) {
		model = new WorldModel(playerName, playerTeamName);
	}

	public void addObserver(WorldModelObserver observer) {
		model.addObserver(observer);
	}

	public void execNextEvent() {
		model.execNextEvent();
	}
}