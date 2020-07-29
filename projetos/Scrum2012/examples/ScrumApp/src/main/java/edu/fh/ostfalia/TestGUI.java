package edu.fh.ostfalia;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import edu.fh.ostfalia.domain.Project;
import edu.fh.ostfalia.ui.model.ProjectTM;
import edu.fh.ostfalia.ui.renderer.ProjectRenderer;

public class TestGUI {
	public static void main(String[] args) {

		// Aufruf Dao.
		List<Project> projects = new LinkedList<Project>();
		projects.add(new Project("Google", "Larry Page", "Suchmaschine"));
		projects.add(new Project("Facebook", "Zuckerberg", "Social Network"));
		projects.add(new Project("Twitter", "Mr. X", "Social Network for geeks"));

		// Logik im Controller.
		TableModel model = new ProjectTM(projects);

		// View.
		JFrame frame = new JFrame();
		frame.setSize(600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTable table = new JTable(model);
		table.setDefaultRenderer(Project.class, new ProjectRenderer());
//		table.setDefaultRenderer(Date.class, new TableCellRenderer() {
//			
//			public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
//				
//				JLabel jLabel = new JLabel(DateFormat.getDateInstance().format((Date)arg1));
//				return jLabel ;
//			}
//		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			public void valueChanged(ListSelectionEvent arg0) {
				System.out.println(arg0.getFirstIndex());
			}
		});

		frame.add(table);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
