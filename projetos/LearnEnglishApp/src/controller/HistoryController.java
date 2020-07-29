/*
 * HistoryController.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package controller;

import java.util.ArrayList;

import model.Lesson;
import view.HistoryPanel;

/**
 * A class controls the view which shows the history of the selected lesson. It
 * simply pass the data to view.
 * 
 * @author Eric
 * @version 1.1
 * @see view.HistoryPanel
 * @see model.Lesson
 * 
 */
public class HistoryController {

	private Lesson selectedLesson;
	private HistoryPanel view;

	/**
	 * Create a new instance of HistoryController and let view set up the data.
	 * Initially, the view is invisible.
	 * 
	 * @param lesson the lesson to show the history
	 */
	public HistoryController(Lesson lesson) {
		selectedLesson = lesson;
		view = HistoryPanel.getHistoryPanel();

		initAllHistory();
	}

	/**
	 * Set the selectedLesson and then let view set up the data.
	 * 
	 * @param l lesson to set
	 */
	public void setSelectedLesson(Lesson l) {
		selectedLesson = l;
		initAllHistory();
	}

	/**
	 * Let the view set up the data.
	 */
	public void initAllHistory() {
		// firstly tell the view clear its history content.
		view.clearHistoryTableContent();

		ArrayList<Integer> scores = selectedLesson.getTestResultScores();
		ArrayList<String> times = selectedLesson.getTestResultTimes();

		int count = times.size();
		for (int i = 0; i < count; ++i) {
			view.addHistory(times.get(i), scores.get(i));
		}
	}
}
