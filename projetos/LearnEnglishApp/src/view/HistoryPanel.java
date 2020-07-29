/*
 * HistoryPanel.java 1.2 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * HistoryPanel - The history panel which is added into the main frame.
 * <p>
 * Generate the history panel of the main frame
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see javax.swing.JPanel
 */

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.commons.io.FilenameUtils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

public class HistoryPanel extends JPanel {
	private JLabel titleLabel;
	private JTable historyTable;
	private JScrollPane historyScrollPanel;
	private DefaultTableModel tableModel;
	private int sum_history;

	private static HistoryPanel historyPanel;

	

	/**
	 * To get the instance of the HistoryPanel.
	 * 
	 * @return an instance of the HistoryPanel
	 */
	public static HistoryPanel getHistoryPanel() {
		if (historyPanel == null) {
			synchronized (HistoryPanel.class) {
				if (historyPanel == null) {
					historyPanel = new HistoryPanel();
				}
			}
		}
		return historyPanel;
	}

	/**
	 * Generate the singleton of the HistoryPanel.
	 */
	private HistoryPanel() {
		super();
		setBounds(500, 50, 400, 600);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setLayout(null);

		initTitleLabel();
		initHistoryScrollPanel();
		initHistoryTable();

		sum_history = 0;
	}

	/**
	 * To get the title label of the History panel
	 * 
	 * @return the title label of the history panel
	 */
	public JLabel getTitleLabel() {
		return titleLabel;
	}

	/**
	 * To get the history table of the history panel
	 * 
	 * @return the history table
	 */
	public JTable getHistoryTable() {
		return historyTable;
	}
    
	/**
	 * To initialize the title label of the history panel.
	 */
	private void initTitleLabel() {
		titleLabel = new JLabel("History");
		titleLabel.setBounds(170, 0, 80, 100);
		titleLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD, 20));
		add(titleLabel);
	}

	/**
	 * To initialize the history scroll panel.
	 */
	private void initHistoryScrollPanel() {
		historyScrollPanel = new JScrollPane();

		historyScrollPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		historyScrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		historyScrollPanel.setBounds(0, 100, 400, 400);
		historyScrollPanel.getViewport().setOpaque(false);
		historyScrollPanel.setOpaque(false);
		historyScrollPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1,
				1, Color.white));

		add(historyScrollPanel);
	}

	/**
	 * To initialize the history table.
	 */
	private void initHistoryTable() {
		String[] columnNames = new String[3];
		columnNames[0] = "Date";
		columnNames[1] = "Mark";
		columnNames[2] = "Evaluation";

		tableModel = new DefaultTableModel(0, 3);
		tableModel.setColumnIdentifiers(columnNames);

		historyTable = new JTable();
		historyTable.setModel(tableModel);
		historyTable.setOpaque(false);
		historyTable.setBackground(new Color(0, true));
		historyTable.setIntercellSpacing(new Dimension(0, 0));
		historyTable.setShowVerticalLines(false);
		historyTable.setShowHorizontalLines(false);
		historyTable.setRowSelectionAllowed(false);
		historyTable.setRowHeight(25);
		historyTable.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD, 20));
		historyTable.getTableHeader().setFont(
				new Font("Adobe Caslon Pro Bold", Font.BOLD, 20));

		historyTable.getTableHeader().setBackground(new Color(0, true));
		historyTable.setBounds(0, 0, 400, 550);
		historyScrollPanel.setViewportView(historyTable);
	}

	/**
	 * To add history into the history table
	 * 
	 * @param time
	 *            the time of the test
	 * @param mark
	 *            the mark of the test
	 */
	public void addHistory(String time, int mark) {
		sum_history++;

		DefaultTableModel newTableModel = new DefaultTableModel(sum_history, 3) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		String[] columnNames = new String[3];
		columnNames[0] = "Date";
		columnNames[1] = "Mark";
		columnNames[2] = "Evaluation";

		newTableModel.setColumnIdentifiers(columnNames);

		for (int i = 0; i < sum_history - 1; i++)
			for (int j = 0; j < 3; j++) {
				newTableModel.setValueAt(tableModel.getValueAt(i, j), i, j);
			}

		historyTable.setModel(newTableModel);
		tableModel = newTableModel;

		historyTable.setValueAt(time, sum_history - 1, 0);
		historyTable.setValueAt(mark, sum_history - 1, 1);
		if (mark <= 60) {
			historyTable.setValueAt("Bad", sum_history - 1, 2);
		} else if (mark <= 80) {
			historyTable.setValueAt("Normal", sum_history - 1, 2);
		} else {
			historyTable.setValueAt("Excellent", sum_history - 1, 2);
		}

	}

	/**
	 * To clear the content of the history table.
	 */
	public void clearHistoryTableContent() {
		DefaultTableModel mtl = (DefaultTableModel) historyTable.getModel();
		int row = mtl.getRowCount();
		while (--row >= 0) {
			mtl.removeRow(row);
		}
		sum_history = 0;
	}
	
	public static void main(String args[]) {
		MainFrame test = new MainFrame();
		HistoryPanel p = new HistoryPanel();
		test.getContentPane().add(p);
		p.addHistory("05/20", 60);
		p.addHistory("05/21", 70);
		p.addHistory("06/01", 95);
		test.setVisible(true);
	}
}
