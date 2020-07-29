/*
 * LessonController.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JTable;

import model.Lesson;
import model.Phrase;
import program.SoundEngine;
import view.LessonPanel;

/**
 * A class controls the view which show all the phrases of the selected lesson
 * and add/delete phrase. Besides, it add some ActionListeners to the view.
 * 
 * @author Eric
 * @version 1.1
 * @see model.Lesson;
 * @see model.Phrase;
 * @see program.SoundEngine;
 * @see view.LessonPanel;
 */
public class LessonController {

	private static LessonController lessonController;
	private Lesson selectedLesson;
	private LessonPanel view;

	public static LessonController getLessonController(){
		if (lessonController == null) {
			synchronized (LessonController.class) {
				if (lessonController == null) {
					lessonController = new LessonController();
				}
			}
		}
		return lessonController;
	}
	/**
	 * Create a new instance of LessonController and let the view set up the
	 * data. , and add some AcitonListeners to the component of the view.
	 * Initially the view is visible.
	 * 
	 * @param lesson the lesson to show and modify.
	 */
	private LessonController() {
		view = LessonPanel.getLessonPanel();
		view.clearPhraseTabelContent();
		//initAllPhrases();
		addALlListeners();

		//view.setVisible(true);
	}
	
	/**
	  * set the lesson and update the controller
	  */
	public void setLesson(Lesson lesson){
		selectedLesson = lesson;
		view = LessonPanel.getLessonPanel();
		view.clearPhraseTabelContent();
		initAllPhrases();

		view.setVisible(true);
	}

	/**
	 * Set the selectedLesson and then let view set up the data. It also tell
	 * the view to check the button is enable.
	 * 
	 * @param l lesson to set
	 * */
	public void setSelectedLesson(Lesson l) {
		selectedLesson = l;
		initAllPhrases();
		resetButtons();
	}

	/**
	 * Let the view set up the data.
	 */
	public void initAllPhrases() {
		// firstly tell the view clear its history content.
		view.clearPhraseTabelContent();

		for (Phrase p : selectedLesson.getAllPhrases()) {
			view.addPhrase(p);
		}

	}

	/**
	 * Tell the view to check the button is enable.
	 */
	public void resetButtons() {
		boolean bool = selectedLesson.isEnable();
		view.getTestButton().setEnabled(bool);
		view.getLearnButton().setEnabled(bool);
	}

	/**
	 * Add some listeners to the view
	 */
	private void addALlListeners() {

		// Add phrase listener
		view.getAddButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String english = view.getEnglishTextField().getText();
				String chinese = view.getChineseTextField().getText();
				if (english.equals("") && chinese.equals(""))
					return;
				Phrase p = new Phrase(english, chinese, null);
				selectedLesson.addPhrase(p);
				view.addPhrase(p);
				view.clearEnglishChineseTextField();
			}
		});

		// Delete phrase listener
		view.getDeleteButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = view.getPhraseTable().getSelectedRow();
				if (index == -1)
					return;
				Phrase p = selectedLesson.getPhrase(index);
				selectedLesson.deletePhrase(p);
				view.deletePhrase(index);
			}
		});

		// Test lesson listener
		view.getTestButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestController.getTestController().setLesson(selectedLesson);
				MainController.getMainController().setVisible(false);
				selectedLesson.setNeededToRestore(true);
			}
		});

		// Learn lesson listener
		view.getLearnButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LearnController(selectedLesson);
				MainController.getMainController().setVisible(false);
				selectedLesson.setNeededToRestore(true);
			}
		});

		// Sound listener
		view.getPhraseTable().addMouseListener(new SoundAdapter());

	}

	/**
	 * A class to sound when the user press the sound column in the table
	 * 
	 * @author Luo Yaoshen
	 * @author Eric
	 * @see program.SoundEngine
	 */
	private class SoundAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JTable phraseTable = view.getPhraseTable();
			int count = phraseTable.getRowCount();
			for (int i = 0; i < count; i++) {
				if (phraseTable.isCellSelected(i, 2)
						&& phraseTable.isColumnSelected(2)
						&& phraseTable.getValueAt(i, 2).toString().charAt(0) != 'j') {
					SoundEngine.playSound(selectedLesson.getPhrase(i)
							.getAudio());
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:data/lesson_test.db");
		Lesson l = new Lesson(conn, 1);

		//new LessonController(l);
	}

}
