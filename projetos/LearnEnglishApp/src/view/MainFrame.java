/*
 * MainFrame.java 1.2 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * MainFrame - The main frame of the learnEnglish system.
 * <p>
 * Generate the login frame.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see view.AbstractFrame
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.io.FilenameUtils;

public class MainFrame extends AbstractFrame {
	private JTable lessonTable;
	private JScrollPane lessonScrollPanel;
	private JButton logoutButton, testAllButton, addLessonButton,
			deleteLessonButton;
	private JLabel titleLabel, historyLabel, listLabel;
	private HistoryPanel historyPanel;
	private LessonPanel lessonPanel;
	private DefaultTableModel tableModel;
	private int sum_lesson;

	/**
	 * To constructs an instance of the main frame
	 */
	public MainFrame() {
		super(FilenameUtils.separatorsToSystem("resource/MainFrame.png"));
		sum_lesson = 0;
		getExitButton().setBounds(getWidth() - 90, 17, 30, 30);
		getShrinkButton().setBounds(getWidth() - 140, 22, 50, 20);
		initTitleLabel();
		initLogoutButton();
		initLessonScrollPanel();

		initLessonTable();
		initLessonButton();
		initHistoryPanel();
		initLessonPanel();
		initTestAllButton();
		initDefaultLabel();
	}

	/**
	 * @return the historyPanel
	 */
	public HistoryPanel getHistoryPanel() {
		return historyPanel;
	}

	/**
	 * @return the lessonPanel
	 */
	public LessonPanel getLessonPanel() {
		return lessonPanel;
	}

	/**
	 * @return the lessonTable
	 */
	public JTable getLessonTable() {
		return lessonTable;
	}

	/**
	 * @return the logoutButton
	 */
	public JButton getLogoutButton() {
		return logoutButton;
	}

	/**
	 * @return the testAllButton
	 */
	public JButton getTestAllButton() {
		return testAllButton;
	}

	/**
	 * @return the addLessonButton
	 */
	public JButton getAddLessonButton() {
		return addLessonButton;
	}

	/**
	 * @return the deleteLessonButton
	 */
	public JButton getDeleteLessonButton() {
		return deleteLessonButton;
	}

	/**
	 * The inner class of the MainFrame
	 * 
	 * @author Luo Yaoshen
	 * 
	 */
	private class HistoryLabelAdapter extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			buildHistoryPanel();
		}
	}

	/**
	 * The inner class of the main frame
	 * 
	 * @author Luo Yaoshen
	 * 
	 */
	private class ListLabelAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			buildLessonPanel();
		}
	}

	/**
	 * To build the history panel
	 */
	private void buildHistoryPanel() {

		historyPanel = HistoryPanel.getHistoryPanel();
		if (lessonPanel != null)
			getContentPane().remove(lessonPanel);
		repaint();
		getContentPane().add(historyPanel);
	}

	/**
	 * To build the lesson panel
	 */
	private void buildLessonPanel() {
		lessonPanel = LessonPanel.getLessonPanel();
		if (historyPanel != null)
			getContentPane().remove(historyPanel);
		repaint();
		getContentPane().add(lessonPanel);
	}

	/**
	 * To initialize the logout button of the main frame
	 */
	private void initLogoutButton() {
		logoutButton = new JButton(new ImageIcon("resource/LogoutButton_1.png"));
		logoutButton.setBounds(20, 20, 100, 60);
		logoutButton.setBorderPainted(false);
		logoutButton.setToolTipText("Logout");
		logoutButton.setOpaque(false);
		logoutButton.setBackground(new Color(0, 0, 0, 0));
		getContentPane().add(logoutButton);
		logoutButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutButton.setIcon(new ImageIcon("resource/LogoutButton.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				logoutButton.setIcon(new ImageIcon("resource/LogoutButton_1.png"));
			}
		});
	}

	/**
	 * To initialize the title label of the main frame
	 */
	private void initTitleLabel() {
		titleLabel = new JLabel("English Tutor");
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		titleLabel.setBounds(170, 5, 150, 100);
		getContentPane().add(titleLabel);
	}

	/**
	 * To initialize the lesson scroll panel
	 */
	private void initLessonScrollPanel() {
		lessonScrollPanel = new JScrollPane();

		lessonScrollPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		lessonScrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		lessonScrollPanel.getViewport().setOpaque(false);
		lessonScrollPanel.setOpaque(false);
		lessonScrollPanel.setBounds(35, 80, 400, 500);
		lessonScrollPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.white));
		getContentPane().add(lessonScrollPanel);
	}

	/**
	 * To initialize the lesson table
	 */
	private void initLessonTable() {
		tableModel = new DefaultTableModel(0, 3);

		lessonTable = new JTable() {
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) c;

				if (isRowSelected(row)) {
					jc.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0,
							new Color(202, 76, 67)));
					jc.setBackground(new Color(0, true));
					jc.setForeground(Color.BLUE);
				} else {
					jc.setForeground(Color.black);
				}
				return c;
			}
		};

		lessonTable.setColumnSelectionAllowed(false);
		lessonTable.setRowSelectionAllowed(true);

		lessonTable.setModel(tableModel);
		lessonTable.setBounds(0, 0, 380, 500);
		lessonTable.setOpaque(false);
		lessonTable.setBackground(new Color(0, true));
		lessonTable.setIntercellSpacing(new Dimension(0, 0));
		lessonTable.setShowVerticalLines(false);
		lessonTable.setShowHorizontalLines(false);
		lessonTable.setRowHeight(30);
		lessonTable.setBorder(null);
		lessonTable.getTableHeader().setPreferredSize(new Dimension(0, 0));
		ListSelectionModel listSelectionModel = lessonTable.getSelectionModel();
		listSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lessonTable.getTableHeader().setVisible(false);

		lessonScrollPanel.setViewportView(lessonTable);
	}

	/**
	 * To initialize the lesson button of the main frame
	 */
	private void initLessonButton() {
		addLessonButton = new JButton(new ImageIcon("resource/Add.png"));
		addLessonButton.setBounds(34, 580, 37, 37);
		addLessonButton.setOpaque(false);
		addLessonButton.setBackground(new Color(0,0,0,0));
		addLessonButton.setBorderPainted(false);
		addLessonButton.addMouseListener(new AddLessonButtonAdapter());
		addLessonButton.setToolTipText("Add a lesson");

		deleteLessonButton = new JButton(new ImageIcon("resource/Delete.png"));
		deleteLessonButton.setBounds(79, 580, 37, 37);
        deleteLessonButton.setOpaque(false);
        deleteLessonButton.setBorderPainted(false);
        deleteLessonButton.setBackground(new Color(0,0,0,0));
        deleteLessonButton.setToolTipText("Delete a lesson");
		
		getContentPane().add(addLessonButton);
		getContentPane().add(deleteLessonButton);
	}

	/**
	 * To initialize the test all button of the main frame
	 */
	private void initTestAllButton() {
		testAllButton = new JButton("Test All");
		testAllButton.setFont(new Font("Arial", Font.BOLD, 16));
		testAllButton.setBounds(121, 580, 110, 36);
		testAllButton.setOpaque(false);
		testAllButton.setBackground(new Color(0,0,0,0));
		testAllButton.setBorderPainted(false);
		testAllButton.setToolTipText("Test all lessons");
		getContentPane().add(testAllButton);
		testAllButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				testAllButton.setForeground(new Color(133, 249, 80));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				testAllButton.setForeground(Color.black);
			}
		});
	}

	/**
	 * To initialize the default label of the main frame
	 */
	private void initDefaultLabel() {
		historyLabel = new JLabel(new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/History.png")));
		historyLabel.setBounds(938, 60, 40, 80);
		historyLabel.setToolTipText("Open the history list");
		historyLabel.addMouseListener(new HistoryLabelAdapter());
		getContentPane().add(historyLabel);

		listLabel = new JLabel(new ImageIcon(
				FilenameUtils.separatorsToSystem("resource/Phrases.png")));
		listLabel.setToolTipText("Open the phrase list");
		listLabel.setBounds(938, 210, 40, 80);
		listLabel.addMouseListener(new ListLabelAdapter());
		getContentPane().add(listLabel);
	}

	/**
	 * To initialize the history panel of the main frame
	 */
	private void initHistoryPanel() {
		historyPanel = HistoryPanel.getHistoryPanel();
	}

	/**
	 * To initialize the lesson panel of the main frame
	 */
	private void initLessonPanel() {
		lessonPanel = LessonPanel.getLessonPanel();
		getContentPane().add(lessonPanel);
	}

	/**
	 * To add lesson into the main frame
	 * 
	 * @param lessonName
	 *            the name of the lesson
	 */
	public void addLesson(String lessonName) {
		sum_lesson++;

		DefaultTableModel newTableModel = new DefaultTableModel(sum_lesson, 3) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		for (int i = 0; i < sum_lesson - 1; i++)
			for (int j = 0; j < 3; j++) {
				newTableModel.setValueAt(tableModel.getValueAt(i, j), i, j);
			}
		lessonTable.setModel(newTableModel);
		tableModel = newTableModel;

		lessonTable.setValueAt("", sum_lesson - 1, 0);
		lessonTable.setValueAt(lessonName, sum_lesson - 1, 1);
		lessonTable.setValueAt("", sum_lesson - 1, 2);
	}

	/**
	 * To delete the lesson of the main frame
	 * 
	 * @param rowToRemove
	 *            the row of the deleted lesson
	 */
	public void deleteLesson(int rowToRemove) {
		((DefaultTableModel) lessonTable.getModel()).removeRow(rowToRemove);
	}

	/**
	 * The inner class of the main frame
	 * 
	 * @author Luo Yaoshen
	 * 
	 */
	private class AddLessonButtonAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
		}
	}

	/**
	 * To set the statue of the learned lesson.
	 * 
	 * @param lessonName
	 *            the name of the lesson
	 */
	public void setLearnedStatue(String lessonName) {
		for (int i = 0; i < sum_lesson; i++) {
			if (lessonTable.getValueAt(i, 1).equals(lessonName)) {
				lessonTable.setValueAt("", sum_lesson - 1, 0);
				lessonTable.setValueAt(lessonName, sum_lesson - 1, 1);
				lessonTable.setValueAt("", sum_lesson - 1, 2);
			}
		}
	}

	/**
	 * To clear content of the lesson table
	 */
	public void clearLessonTableContent() {
		DefaultTableModel mtl = (DefaultTableModel) lessonTable.getModel();
		int row = mtl.getRowCount();
		while (--row >= 0) {
			mtl.removeRow(row);
		}
		sum_lesson = 0;
	}

	public static void main(String args[]) {
		MainFrame test = new MainFrame();
		test.setVisible(true);
		test.addLesson("Lesson_1");
		test.setLearnedStatue("Lesson_1");
		test.addLesson("Lesson_2");
	}
}
