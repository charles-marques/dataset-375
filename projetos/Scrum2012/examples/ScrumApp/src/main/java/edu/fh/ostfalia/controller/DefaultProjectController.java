package edu.fh.ostfalia.controller;

import java.util.List;

import edu.fh.ostfalia.dao.Dao;
import edu.fh.ostfalia.dao.ProjectDao;
import edu.fh.ostfalia.domain.Project;

public class DefaultProjectController implements ProjectController {
	ProjectDao projectDao;

	private Project selectedProject;

	public Project createProject() {
		return new Project();
	};

	public List<Project> listProjects() {
		return projectDao.list();
	};

	public void saveProject() {
		projectDao.save(getSelectedProject());
	};

	public void deleteProject() {
	}

	public Project getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Project selectedProject) {
		this.selectedProject = selectedProject;
	}

	public void setDao(Dao projectDao) {
		// TODO Auto-generated method stub
		
	};
}
