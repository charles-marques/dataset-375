package racingManager.gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConstructorChampionchipFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	JTable table;

	private String[] columnNames = { "Place", "Team", "Points" };
	// TODO: read row numbers, column numbers, column lables from argument
	private DefaultTableModel dtm = new DefaultTableModel(columnNames, 14);

	public ConstructorChampionchipFrame() {
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());

		table = new JTable(dtm);

		cp.add(new JScrollPane(table), BorderLayout.CENTER);

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setTitle("Constructor Championship");
		this.pack();
	}

	public void updateTableContent(String[][] currentConstructorChampionchip) {

		for (int i = 0; i < currentConstructorChampionchip.length; i++)
			for (int j = 0; j < currentConstructorChampionchip[i].length; j++) {
				table.setValueAt(currentConstructorChampionchip[i][j], i, j);
			}
	}
}
