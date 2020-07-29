package edu.fh.ostfalia.controller;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import edu.fh.ostfalia.dao.ProjectDao;
import edu.fh.ostfalia.domain.Project;

public class TestProjectController {

	@Test
	public void createProject() {
		DefaultProjectController controller = new DefaultProjectController();
		Project project = controller.createProject();

		assertNotNull(project);
	}

	
	
	
	@Test
	public void saveProject() throws Exception {
		ProjectDao dao = Mockito.mock(ProjectDao.class);
		DefaultProjectController controller = new DefaultProjectController();
		controller.projectDao = dao;

		Project project = controller.createProject();

		controller.setSelectedProject(project);
		controller.saveProject();

		Mockito.verify(dao).save(project);
	}
	
	@Test
	public void listProjects() throws Exception {
		ProjectDao dao = Mockito.mock(ProjectDao.class);
		Mockito.when(dao.list()).thenReturn(Arrays.asList(new Project(), new Project(), new Project()));

		DefaultProjectController controller = new DefaultProjectController();
		controller.projectDao = dao;

		List<Project> projects = controller.listProjects();
		assertEquals(3, projects.size());
	}

}
