package racingManager.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlaceToCashFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JTable table;

	private String[] columnNames = { "Place", "Cash" };
	// TODO: read row numbers, column numbers, column lables from argument
	private DefaultTableModel dtm = new DefaultTableModel(columnNames, 28);

	public PlaceToCashFrame() {
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		table = new JTable(dtm);

		cp.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Place -> Cash");
		this.pack();
	}

	public void updateTableContent(String[][] currentPlaceToCash) {

		for (int i = 0; i < currentPlaceToCash.length; i++)
			for (int j = 0; j < currentPlaceToCash[i].length; j++) {
				table.setValueAt(currentPlaceToCash[i][j], i, j);
			}
	}
}
