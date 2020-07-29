package edu.fh.ostfalia.ui.renderer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import edu.fh.ostfalia.domain.Project;

public class ProjectRenderer implements TableCellRenderer {

	public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
		
		Project project = (Project) arg1;
		
		JLabel label = new JLabel();
		if(arg5 == 0)
			label.setText(project.getName() + " Sprint 6");
		if(arg5 == 1)
			label.setText(project.getOwner() + "(a@me.com)");
		if(arg5 == 2)
			label.setText(project.getTimestamp().toString());
		
		label.setToolTipText(project.getDescription());
		return label;
	}

}
