package edu.fh.ostfalia.ui.model;

import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.fh.ostfalia.domain.Project;

public class ProjectTM extends AbstractTableModel {

	private final List<Project> projects;

	public ProjectTM(List<Project> projects) {
		this.projects = projects;
	}

	
	@Override
	public Class<?> getColumnClass(int arg0) {
		if (arg0 == 0)
			return Project.class;
		if (arg0 == 1)
			return  Project.class;
		if (arg0 == 2)
			return Date.class;
		return null;
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int arg0) {
		if (arg0 == 0)
			return "Project name";
		if (arg0 == 1)
			return "Owner";
		if (arg0 == 2)
			return "Timestamp";
		return null;
	}

	public int getRowCount() {
		return this.projects.size();
	}

	public Object getValueAt(int row, int column) {
		Project project = this.projects.get(row);
		if (column == 0)
			return project;
		if (column == 1)
			return project;
		if (column == 2)
			return project.getTimestamp();
		
		return null;
	}

	public void remove(int row) {
		this.projects.remove(row);
		this.fireTableStructureChanged();
	}

}
