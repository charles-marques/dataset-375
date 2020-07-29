package edu.fh.ostfalia;

import javax.swing.JFrame;

import edu.fh.ostfalia.controller.Controller;
import edu.fh.ostfalia.controller.DefaultProjectController;
import edu.fh.ostfalia.dao.Dao;
import edu.fh.ostfalia.ui.view.ProjectView;
import edu.fh.ostfalia.ui.view.View;

public class Application {

	public Application() {
		View projectView = new ProjectView();
		View backlogView;
		View sprintView;

		Controller projectController = new DefaultProjectController();
		Controller backlController;
		Controller sprintController;

		Dao projectDao = null;
//		= new InMemoryDao();
		Dao backlogDao;
		Dao sprintDao;
		
		projectView.setController(projectController);
		projectController.setDao(projectDao);

		JFrame frame = new JFrame();
		frame.setTitle("Scrum");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setLocationRelativeTo(null);
		frame.add(projectView.getComponent());
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Application();
	}
}
