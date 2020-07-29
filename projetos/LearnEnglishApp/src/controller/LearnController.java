/*
 * LearnController.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import model.Lesson;
import model.Phrase;
import view.LearnFrame;

/**
 * A class controls the view which to learn the lesson
 * 
 * @author Eric
 * @version 1.1
 * @see model.Lesson;
 * @see model.Phrase;
 * @see view.LearnFrame;
 */
public class LearnController {

	private Lesson selectedLesson;
	private LearnFrame view;

	/**
	 * Create a new instance of LearnController, let the view set up the data,
	 * show all the phrases. Initially the view is visible.
	 * 
	 * @param lesson
	 */
	public LearnController(Lesson lesson) {
		selectedLesson = lesson;
		view = LearnFrame.getLearnFrame();
		view.clearAllPhraseItem();

		initAllPhrases();
		addAllListener();

		view.setVisible(true);
	}

	/**
	 * @param lesson
	 */
	public void setLesson(Lesson lesson) {
		selectedLesson = lesson;
		initAllPhrases(); // TODO
	}

	/**
	 * show all the phrase in the view
	 */
	private void initAllPhrases() {
		for (Phrase p : selectedLesson.getAllPhrases()) {
			view.addPhraseItem(p);
		}
	}

	/**
	 * Add some listeners to the view
	 */
	private void addAllListener() {

		// finish listener
		view.getFinishButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainController main = MainController.getMainController();
				main.getLessonController().initAllPhrases();
				main.setVisible(true);

				view.setVisible(false);
				view.dispose();
				selectedLesson = null;
			}
		});

		// when exiting, back to main frame
		view.addExitingReturnToMainController();
	}

	public static void main(String[] args) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:data/lesson_test.db");
		Lesson l = new Lesson(conn, 1);

		new LearnController(l);
	}
}
