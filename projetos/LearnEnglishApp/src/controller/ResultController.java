/*
 * ResultController.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Phrase;
import view.ResultFrame;

/**
 * A class controls the view which show the result of the test and show all the
 * wrong answer, and give some advice.
 * 
 * @author Eric
 * @version 1.1
 * @see model.Phrase;
 * @see view.ResultFrame;
 */
public class ResultController {

	private ArrayList<Phrase> wrongPhrases;
	private ResultFrame view;
	private int score;

	/**
	 * Create a new instance of ResultController to show the result of the test.
	 * Initially the view is visible.
	 * 
	 * @param wrongPhrases the phrases user made mistake
	 * @param questionTypes the question type of the wrong answers
	 * @param wrongAnswers the wrong answers
	 * @param score the score of the test
	 */
	public ResultController(ArrayList<Phrase> wrongPhrases,
			ArrayList<Integer> questionTypes, ArrayList<String> wrongAnswers,
			int score) {

		int count = wrongPhrases.size();
		this.wrongPhrases = wrongPhrases;
		this.score = score;
		String[][] data = new String[count][4];

		// set up the data that will pase to the view
		for (int i = 0; i < count; ++i) {
			Phrase p = wrongPhrases.get(i);
			data[i][0] = p.getLessonName();
			data[i][2] = wrongAnswers.get(i);
			switch (questionTypes.get(i)) {
			case TestController.ENGLISH_QUESTION_TYPE:
				data[i][1] = p.getEnglish();
				data[i][3] = p.getChinese();
				break;
			case TestController.CHINESE_QUESTION_TYPE:
				data[i][1] = p.getChinese();
				data[i][3] = p.getEnglish();
				break;
			case TestController.AUDIO_QUESTION_TYPE:
				data[i][1] = "AUDIO";
				data[i][3] = p.getEnglish();
			}
		}

		view = new ResultFrame(data);
		addListener();
		setViewContent();
		view.setVisible(true);
	}

	/**
	 * Add some listeners to the view
	 */
	private void addListener() {

		// finish listener
		view.getFinishButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.setVisible(false);
				view.dispose();
				MainController main = MainController.getMainController();
				main.getLessonController().initAllPhrases();
				main.getHistoryController().initAllHistory();
				main.setVisible(true);
			}
		});

		// when exiting, back to main frame
		view.addExitingReturnToMainController();
	}

	/**
	 * set the content of the view
	 */
	private void setViewContent() {

		int wrongCount = wrongPhrases.size();
		view.getScoreLabel().setText("Score: " + score);

		if (wrongCount > 80) {
			view.getSuggestionTwoLabel().setText(
					"Congraduration, you can learn next lesson");
		} else if (wrongCount > 60) {
			view.getSuggestionTwoLabel().setText(
					"You are recommended to retest this lesson");
		} else {
			view.getSuggestionTwoLabel().setText(
					"You are recommended to relearn this lesson");
		}
	}

}
