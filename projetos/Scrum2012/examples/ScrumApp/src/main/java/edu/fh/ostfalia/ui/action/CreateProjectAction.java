package edu.fh.ostfalia.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import edu.fh.ostfalia.controller.ProjectController;
import edu.fh.ostfalia.domain.Project;

public class CreateProjectAction extends AbstractAction{

	private final ProjectController controller;

	public CreateProjectAction(ProjectController controller) {
		super("Create...");
		this.putValue(Action.SHORT_DESCRIPTION, "Neues Projekt anlegen");
		this.setEnabled(false);
		this.controller = controller;
		
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Project project = controller.createProject();
		System.out.println("Project " + project + " created.");
	}

}
