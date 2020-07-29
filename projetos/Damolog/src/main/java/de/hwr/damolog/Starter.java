package de.hwr.damolog;

import de.hwr.damolog.controller.IController;
import de.hwr.damolog.controller.impl.prolog.Prolog2Controller;
import de.hwr.damolog.model.Model;
import de.hwr.damolog.view.IObservingView;
import de.hwr.damolog.view.implementation.SwtMainWindow;

/**
 * 
 * In dieser Klasse wird das Programm gestartet.
 * 
 * @author nsmeenk
 * 
 */
public class Starter {

	public static void main(String[] args) {

		// MVC
		Model model = new Model();
		IController controller = new Prolog2Controller();
		IObservingView view = new SwtMainWindow();

		// Observer Design Pattern
		model.addObserver(view);

		view.setController(controller);
		controller.setModel(model);

		view.open();
	}
}
