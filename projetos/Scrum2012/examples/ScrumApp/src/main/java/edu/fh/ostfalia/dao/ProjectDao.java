package edu.fh.ostfalia.dao;

import java.util.List;

import edu.fh.ostfalia.domain.Project;

public interface ProjectDao extends Dao<Project> {
	public Project save(Project project);
	public List<Project> list();
}
