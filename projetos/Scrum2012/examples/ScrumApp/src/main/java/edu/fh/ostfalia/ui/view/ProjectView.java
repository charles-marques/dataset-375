package edu.fh.ostfalia.ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.fh.ostfalia.controller.ProjectController;
import edu.fh.ostfalia.ui.action.CreateProjectAction;

public class ProjectView implements View<ProjectController> {

	private ProjectController projectController;

	public JComponent getComponent() {

		JPanel panel = new JPanel();
		panel.add(new JButton(new CreateProjectAction(projectController)));
		
		return panel;
	}

	public void setController(ProjectController projectController) {
		this.projectController = projectController;
	}

}
