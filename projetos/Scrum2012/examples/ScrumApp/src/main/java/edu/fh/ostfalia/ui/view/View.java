package edu.fh.ostfalia.ui.view;

import javax.swing.JComponent;

import edu.fh.ostfalia.controller.Controller;

public interface View<C extends Controller> {
	public JComponent getComponent();
	public void setController(C projectController);
}
