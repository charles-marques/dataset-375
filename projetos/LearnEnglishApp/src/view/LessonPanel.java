/*
 * LearnPanel.java 1.1 2012/10/18
 *
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 *
 * All rights reserved.
 *
 */

package view;

/**
 * LearnPanel - The learn panel of the learnEnglish system.
 * <p>
 * Generate the learn panel.
 * 
 * @author Luo Yaoshen
 * @version 1.2
 * @see javax.swing.JPanel
 */
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import model.Phrase;

import org.apache.commons.io.FilenameUtils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LessonPanel extends JPanel {

	private JButton learnLessonButton, testLessonButton, deletePhraseButton,
			addPhraseButton;
	private JTextField chineseTextField, englishTextField;
	private DefaultTableModel tableModel;
	private JScrollPane phraseScrollPanel;
	private JTable phraseTable;
	private JLabel chineseLabel, englishLabel;
	private int sum_phrase;

	private static LessonPanel lessonPanel;

	/**
	 * To get the singleton of the lessonPanel
	 * 
	 * @return the singleton of the lessonPanel
	 */
	public static LessonPanel getLessonPanel() {
		if (lessonPanel == null) {
			synchronized (LessonPanel.class) {
				if (lessonPanel == null) {
					lessonPanel = new LessonPanel();
				}
			}
		}
		return lessonPanel;
	}

	/**
	 * To generate the singleton of the lessonPanel.
	 */
	private LessonPanel() {
		super();
		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		setBounds(500, 40, 400, 600);

		initLearnLessonButton();
		initTestLessonButton();
		initPhraseScrollPanel();
		initPhraseTable();
		initAddPhrase();
		initDeletePhraseButton();

		sum_phrase = 0;
	}

	/**
	 * @return the phraseTable
	 */
	public JTable getPhraseTable() {
		return phraseTable;
	}

	/**
	 * To get the Chinese text field of the lesson panel
	 * 
	 * @return the ChineseTextField
	 */
	public JTextField getChineseTextField() {
		return chineseTextField;
	}

	/**
	 * To get the English text field
	 * 
	 * @return the englishTextField
	 */
	public JTextField getEnglishTextField() {
		return englishTextField;
	}

	/**
	 * To get the learn button of the lesson panel
	 * 
	 * @return the learn button
	 */
	public JButton getLearnButton() {
		return learnLessonButton;
	}

	/**
	 * To get the test button of the lesson panel
	 * 
	 * @return the test button of the lesson panel
	 */
	public JButton getTestButton() {
		return testLessonButton;
	}

	/**
	 * To get the add button of the lesson panel
	 * 
	 * @return the add button
	 */
	public JButton getAddButton() {
		return addPhraseButton;
	}

	/**
	 * To get the delete button
	 * 
	 * @return the delete button
	 */
	public JButton getDeleteButton() {
		return deletePhraseButton;
	}

	/**
	 * To initialize the learn lesson button of the lesson panel.
	 */
	private void initLearnLessonButton() {
		learnLessonButton = new JButton("Learn");
		learnLessonButton.setFont(new Font("Arial", Font.BOLD, 16));
		learnLessonButton.setBounds(15, 0, 100, 30);
		learnLessonButton.setOpaque(false);
		learnLessonButton.setBackground(new Color(0,0,0,0));
		learnLessonButton.setBorderPainted(false);
		learnLessonButton.setToolTipText("Learn this lesson");
		add(learnLessonButton);
		learnLessonButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				learnLessonButton.setForeground(new Color(133, 249, 80));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				learnLessonButton.setForeground(Color.black);
			}
		});
	}

	/**
	 * To initialize the test lesson button of the lesson panel.
	 */
	private void initTestLessonButton() {
		testLessonButton = new JButton("Test");
		testLessonButton.setFont(new Font("Arial", Font.BOLD, 16));
		testLessonButton.setBounds(100, 0, 90, 30);
		testLessonButton.setOpaque(false);
		testLessonButton.setBorderPainted(false);
		testLessonButton.setBackground(new Color(0,0,0,0));
		testLessonButton.setToolTipText("Test this lesson");
		add(testLessonButton);
		testLessonButton.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				testLessonButton.setForeground(new Color(133, 249, 80));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				testLessonButton.setForeground(Color.black);
			}
		});
	}

	/**
	 * To initialize the phrase scroll panel of the lesson panel.
	 */
	private void initPhraseScrollPanel() {
		phraseScrollPanel = new JScrollPane();

		phraseScrollPanel
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		phraseScrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		phraseScrollPanel.getViewport().setOpaque(false);
		phraseScrollPanel.setOpaque(false);
		phraseScrollPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
				Color.white));

		phraseScrollPanel.setBounds(15, 39, 400, 461);
		add(phraseScrollPanel);
	}

	/**
	 * To initialize the phrase table of the lesson panel.
	 */
	private void initPhraseTable() {
		String[] columnNames = new String[5];
		columnNames[0] = "Chinese";
		columnNames[1] = "English";
		columnNames[2] = "Sound";
		columnNames[3] = "Date";
		columnNames[4] = "Accuracy";

		tableModel = new DefaultTableModel(0, 5);
		tableModel.setColumnIdentifiers(columnNames);
		phraseTable = new JTable();
		phraseTable.setModel(tableModel);
		phraseTable.setRowHeight(25);
		phraseTable.setBounds(0, 0, 350, 450);

		phraseTable.setOpaque(false);
		phraseTable.setBackground(new Color(0, true));
		phraseTable.setIntercellSpacing(new Dimension(0, 0));
		phraseTable.setShowVerticalLines(false);
		phraseTable.setShowHorizontalLines(false);
		phraseTable.setFont(new Font("Arial", Font.PLAIN, 15));
        
        //System.out.println(phraseTable.getFont());
		
		ListSelectionModel listSelectionModel = phraseTable.getSelectionModel();
		listSelectionModel
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		phraseScrollPanel.setViewportView(phraseTable);
	}

	/**
	 * To initialize the add phrase of the lesson panel.
	 */
	private void initAddPhrase() {
		chineseLabel = new JLabel("Chinese");
		chineseLabel.setBounds(30, 530, 60, 30);
		add(chineseLabel);

		englishLabel = new JLabel("English");
		englishLabel.setBounds(30, 560, 60, 30);
		add(englishLabel);

		chineseTextField = new JTextField();
		chineseTextField.setBounds(90, 530, 250, 30);
		add(chineseTextField);

		englishTextField = new JTextField();
		englishTextField.setBounds(90, 560, 250, 30);
		add(englishTextField);

		addPhraseButton = new JButton(new ImageIcon("resource/Add.png"));
		addPhraseButton.setOpaque(false);
		addPhraseButton.setBackground(new Color(0,0,0,0));
		addPhraseButton.setBorderPainted(false);
		addPhraseButton.setBounds(340, 530, 60, 60);
		addPhraseButton.setToolTipText("Add a phrase");
		add(addPhraseButton);
	}

	/**
	 * To initialize the delete phrase button of the lesson panel.
	 */
	private void initDeletePhraseButton() {
		deletePhraseButton = new JButton(new ImageIcon("resource/delete.png"));
		deletePhraseButton.setBounds(352, 498, 37, 37);
		deletePhraseButton.setOpaque(false);
		deletePhraseButton.setBackground(new Color(0,0,0,0));
		deletePhraseButton.setBorderPainted(false);
		deletePhraseButton.setToolTipText("Delete a phrase");
		add(deletePhraseButton);
	}

	/**
	 * To add the phrase into the phrase table
	 * 
	 * @param p
	 *            the special phrase
	 */
	public void addPhrase(Phrase p) {
		String chinese = p.getChinese();
		String english = p.getEnglish();
		String date = p.getLastReviewTime();
		String accuracy = "" + p.getAccuracy();
		sum_phrase++;

		String[] columnNames = new String[5];
		columnNames[0] = "Chinese";
		columnNames[1] = "English";
		columnNames[2] = "Sound";
		columnNames[3] = "Date";
		columnNames[4] = "Accuracy";

		DefaultTableModel newTableModel = new DefaultTableModel(sum_phrase, 5) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		newTableModel.setColumnIdentifiers(columnNames);

		for (int i = 0; i < sum_phrase - 1; i++)
			for (int j = 0; j < 5; j++) {
				newTableModel.setValueAt(tableModel.getValueAt(i, j), i, j);
			}

		phraseTable.setModel(newTableModel);
		tableModel = newTableModel;
		phraseTable.setValueAt(chinese, sum_phrase - 1, 0);
		phraseTable.setValueAt(english, sum_phrase - 1, 1);

		Image i = Toolkit.getDefaultToolkit()
				.getImage("resource/VoiceButton.png")
				.getScaledInstance(25, 25, Image.SCALE_DEFAULT);

		if (p.getAudio() != null)
			phraseTable.setValueAt(
					new ImageIcon(FilenameUtils
							.separatorsToSystem("resource/VoiceButton.png")),
					sum_phrase - 1, 2);
		else
			phraseTable.setValueAt(new ImageIcon(), sum_phrase - 1, 2);
		phraseTable.setValueAt(date, sum_phrase - 1, 3);
		phraseTable.setValueAt(accuracy, sum_phrase - 1, 4);

		phraseTable.getColumn("Sound").setCellEditor(
				phraseTable.getDefaultEditor(Icon.class));
		phraseTable.getColumn("Sound").setCellRenderer(
				phraseTable.getDefaultRenderer(Icon.class));

	}

	/**
	 * To delete the phrase .
	 * 
	 * @param row
	 *            the row of the delete phrase
	 */
	public void deletePhrase(int row) {
		((DefaultTableModel) phraseTable.getModel()).removeRow(row);
		sum_phrase--;
	}

	/**
	 * To clear the phrase table content.
	 */
	public void clearPhraseTabelContent() {
		DefaultTableModel mtl = (DefaultTableModel) phraseTable.getModel();
		int row = mtl.getRowCount();
		while (--row >= 0) {
			mtl.removeRow(row);
		}
		sum_phrase = 0;
	}

	/**
	 * To clear the English and Chinese text field
	 */
	public void clearEnglishChineseTextField() {
		chineseTextField.setText("");
		englishTextField.setText("");
	}

	public static void main(String args[]) {
		MainFrame test = new MainFrame();
		LessonPanel p = new LessonPanel();
		test.getContentPane().add(p);
		test.setVisible(true);
	}
}
