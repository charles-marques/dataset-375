package racingManager.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlaceToPointsFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JTable table;

	private String[] columnNames = { "Place", "Points" };
	// TODO: read row numbers, column numbers, column lables from argument
	private DefaultTableModel dtm = new DefaultTableModel(columnNames, 28);

	public PlaceToPointsFrame() {
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		table = new JTable(dtm);

		cp.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Place -> Points");
		this.pack();
	}

	public void updateTableContent(String[][] placeToPoints) {
		for (int i = 0; i < placeToPoints.length; i++)
			for (int j = 0; j < placeToPoints[i].length; j++) {
				table.setValueAt(placeToPoints[i][j], i, j);
			}
	}
}
