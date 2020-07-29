/*
 * ResultFrame.java 1.2 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * ResultFrame - The result frame of the learnEnglish system.
 * <p>
 * Generate the result frame.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see view.AbstractFrame
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.io.FilenameUtils;

public class ResultFrame extends AbstractFrame {

	private JTable resultTable;
	private JLabel titleLabel, scoreLabel, suggestionOneLabel,
			suggestionTwoLabel;
	private JButton finishButton;

	

	/**
	 * To constructs an instance of the result frame
	 * 
	 * @param rowData
	 *            the data of the result frame
	 */
	public ResultFrame(Object[][] rowData) {
		super("resource/ResultFrame.png", 20, 20, 30, 12);
		initTitleLabel();
		initScoreLabel();
		initResultTable(rowData);
		initSuggestionOneLabel();
		initSuggestionTwoLabel();
		initFinishButton();
		exitButton.setBounds(getWidth() - 35, 0, 30, 30);
		shrinkButton.setBounds(getWidth() - 75, 5, 50, 20);
	}

	/**
	 * @return the titleLabel
	 */
	public JLabel getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @return the suggestionLabel
	 */
	public JLabel getSuggestionTwoLabel() {
		return suggestionTwoLabel;
	}

	/**
	 * @return the finishButton
	 */
	public JButton getFinishButton() {
		return finishButton;
	}
	
	/**
	 * To initialize the title label of the result frame.
	 */
	private void initTitleLabel() {
		titleLabel = new JLabel("Result");
		titleLabel.setBounds(200, 60, 200, 100);
		titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		getContentPane().add(titleLabel);
	}

	/**
	 * To initialize the score label of the result frame
	 */
	private void initScoreLabel() {
		scoreLabel = new JLabel("SCORE:");
		scoreLabel.setBounds(43, 145, 100, 30);
		scoreLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD, 20));
		getContentPane().add(scoreLabel);
	}

	/**
	 * To initialize the result table of the result frame
	 * 
	 * @param rowData
	 *            the data of the result table
	 */
	private void initResultTable(Object[][] rowData) {
		String[] name = new String[4];
		name[0] = "Lesson";
		name[1] = "Question";
		name[2] = "Your Answer";
		name[3] = "Correct Answer";
		resultTable = new JTable(rowData, name) {

			public Class getColumnClass(int column) {
				return getValueAt(0, column).getClass();
			}

			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				JComponent jc = (JComponent) c;
				jc.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0,
						Color.white));

				if (isRowSelected(row)) {
				} else {
				}

				return c;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		resultTable.setIntercellSpacing(new Dimension(0, 0));
		resultTable.setShowVerticalLines(false);
		resultTable.setRowSelectionAllowed(false);
		resultTable.setRowHeight(25);

		resultTable.setBounds(53, 184, 392, 300);
		resultTable.setOpaque(false);
		resultTable.setBackground(new Color(0, true));

		resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		getContentPane().add(resultTable);
	}

	/**
	 * To initialize the suggestionOneLabel
	 */
	private void initSuggestionOneLabel() {
		suggestionOneLabel = new JLabel();
		suggestionOneLabel.setOpaque(false);
		suggestionOneLabel.setText("Suggestion:");
		suggestionOneLabel.setBounds(43, 450, 120, 25);
		suggestionOneLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD,
				20));
		getContentPane().add(suggestionOneLabel);
	}

	/**
	 * To initialize the suggestionTwoLabel
	 */
	private void initSuggestionTwoLabel() {
		suggestionTwoLabel = new JLabel();
		suggestionTwoLabel.setOpaque(false);
		suggestionTwoLabel.setText("Congraduation: XXX.");
		suggestionTwoLabel.setBounds(53, 481, 370, 45);
		suggestionTwoLabel.setFont(new Font("Adobe Caslon Pro Bold", Font.BOLD,
				15));
		getContentPane().add(suggestionTwoLabel);
	}

	/**
	 * To initialize the finish button of the resultFrame
	 */
	private void initFinishButton() {
		finishButton = new JButton(new ImageIcon("resource/Finish.png"));
		finishButton.setBackground(new Color(0, 0, 0, 0));
		finishButton.setBounds(420, 500, 60, 60);
		getContentPane().add(finishButton);
	}

	public static void main(String args[]) {
		String[][] x = new String[3][4];
		x[0][0] = "Lesson";
		x[0][1] = "Question";
		x[0][2] = "Your Answer";
		x[0][3] = "Correct Answer";

		x[1][0] = "1";
		x[1][1] = "MM is b";
		x[1][2] = "Your sister";
		x[1][3] = "Your mother";

		x[2][0] = "1";
		x[2][1] = "MM is b";
		x[2][2] = "Your sister";
		x[2][3] = "Your mother";
		ResultFrame test = new ResultFrame(x);
		test.setVisible(true);
	}

	/**
	 * @return the scoreLabel
	 */
	public JLabel getScoreLabel() {
		return scoreLabel;
	}
}
