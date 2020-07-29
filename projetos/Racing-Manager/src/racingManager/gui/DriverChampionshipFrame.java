package racingManager.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DriverChampionshipFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	JTable table;

	private DefaultTableModel dtm = new DefaultTableModel(0, 0);

	public DriverChampionshipFrame() {
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		table = new JTable(dtm);

		cp.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Driver Championship");
		this.pack();
	}
	
	public void updateTableContent(String[] columnNames, String[][] content) {
		// Create new Table model
		table.setModel(new DefaultTableModel(columnNames,
				content.length));
		// Fill new table model
		for (int i = 0; i < content.length; i++)
			for (int j = 0; j < content[i].length; j++) {
				table.setValueAt(content[i][j], i, j);
			}
	}
}
