/*
 * TestController.java 1.1 2012/6/1
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import model.Account;
import model.Lesson;
import model.Phrase;
import program.SoundEngine;
import view.TestFrame;

/**
 * A class controls the view which tests phrase by phrase. After a test, it will
 * calculate the score and create result view to show the score.
 * 
 * @author Eric
 * @version 1.1
 * @see model.Account;
 * @see model.Lesson;
 * @see model.Phrase;
 * @see program.SoundEngine;
 * @see view.TestFrame;
 */
public class TestController {
	// constants to identify the type of the question
	public static final int ENGLISH_QUESTION_TYPE = 284;
	public static final int CHINESE_QUESTION_TYPE = 230;
	public static final int AUDIO_QUESTION_TYPE = 273;

	private static TestController testController;
	private static boolean isAddListener;
	// enum to identify the type of the test
	private enum TestType {
		TEST_ONE_LESSON, TEST_ALL_LESSONS
	}

	// enum to identify the state of test
	private enum TestState {
		NEXT_PHRASE, FINISH_TEST
	}

	private TestFrame view;

	private TestType testType;
	private TestState testState;
	private int currentQuestionType;
	private int currentPhraseIndex;
	private int totalCorrectPhraseNum;

	private Account account;
	private Phrase currentPhrase;
	private Lesson selectedLesson;

	private ArrayList<Phrase> tenPhrases;
	private ArrayList<Integer> questionTypes;
	private ArrayList<Phrase> wrongPhrases;
	private ArrayList<String> wrongAnswers;

	
	/**
	 * get TestController singleton
	 * 
	 * @return TestController singleton
	 */
	public static TestController getTestController() {
		if (testController == null) {
			synchronized (TestController.class) {
				if (testController == null) {
					testController = new TestController();
				}
			}
		}
		return testController;
	}
	
	/**
	 * Create an instance of TestController to test one lesson. Initially the
	 * view is visible
	 * 
	 * @param l the current lesson
	 */
	private TestController(Lesson l) {
		testType = TestType.TEST_ONE_LESSON;
		selectedLesson = l;

		initialize();
	}

	/**
	 * Create an instance of TestController to all the lessons which have
	 * learned. Initially the view is visible
	 * 
	 * @param a the account to test
	 */
	private TestController(Account a) {
		testType = TestType.TEST_ALL_LESSONS;
		account = a;

		initialize();
	}
	
	/**
	 *  set the lesson and show the next phrase
	 */	 
	public void setLesson(Lesson l){
		selectedLesson = l;
		clearALl();
		testType = TestType.TEST_ONE_LESSON;
		view.setVisible(true);
		showNextPhrase();

	}
	
	/**
	 *  set the account and show the next phrase
	 */
	public void setAccount(Account a){
		account = a;
		clearALl();
		testType = TestType.TEST_ALL_LESSONS;
		view.setVisible(true);
		showNextPhrase();

	}
	
	/**
	 * clear almost all the thing in this controller
	 */
	private void clearALl(){
		view = TestFrame.getTestFrame();
		clearComponent();
		view.clearAll();
		tenPhrases.clear();
		questionTypes.clear();
		wrongPhrases.clear();
		wrongAnswers.clear();
		currentPhraseIndex = -1;
		testState = TestState.NEXT_PHRASE;
	}

	/**
	 * Initialize the TestController
	 */
	private void initialize() {
		view = TestFrame.getTestFrame();
		clearComponent();
		view.clearAll();
		tenPhrases = new ArrayList<Phrase>();
		questionTypes = new ArrayList<Integer>();
		wrongPhrases = new ArrayList<Phrase>();
		wrongAnswers = new ArrayList<String>();

		currentPhraseIndex = -1;
		testState = TestState.NEXT_PHRASE;
		addActionListener();

		view.setVisible(true);
		//showNextPhrase();
	}

	/**
	 * According to the test type, prepare no more than ten phrases
	 */
	private void prepareTenPhrase() {
		switch (testType) {
		case TEST_ONE_LESSON:
			tenPhrases = selectedLesson.getRandomTenPhrase();
			break;
		case TEST_ALL_LESSONS:
			tenPhrases = account.getRandomTenPhrase();
			break;
		}
	}

	/**
	 * Show the next phrase and randomly choose the type of the test.
	 */
	public void showNextPhrase() {
		// prepare the test
		if (tenPhrases.size() == 0) {
			this.prepareTenPhrase();
		}
		int index = currentPhraseIndex + 1;
		// is finished?
		if (index > tenPhrases.size() - 1 || index < 0)
			return;

		Phrase p = tenPhrases.get(++currentPhraseIndex);
		if (p == null)
			return;

		currentPhrase = p;

		Random ran = new Random();
		int rnum = ran.nextInt(3);
		switch (rnum) {
		case 0: // show Chinese
			currentQuestionType = TestController.CHINESE_QUESTION_TYPE;
			view.getQuestionLabel().setText(p.getChinese());
			break;
		case 1: // show English
			currentQuestionType = TestController.ENGLISH_QUESTION_TYPE;
			view.getQuestionLabel().setText(p.getEnglish());
			break;
		case 2: // speak English
			currentQuestionType = TestController.AUDIO_QUESTION_TYPE;
			SoundEngine.playSound(p.getAudio());
			break;
		}
	}

	/**
	 * before showing next phrase, clear the label component
	 */
	private void clearComponent() {
		// view.getCorrectAnswerLabel().setText("");
		view.getQuestionLabel().setText("");
		view.getAnswerTextField().setText("");
	}

	/**
	 * verify the answer is correct or not
	 */
	private void verifyAnswer() {
		// correct answer
		if (currentPhrase.getEnglish().equals(
				view.getAnswerTextField().getText())) {
			totalCorrectPhraseNum++;
			currentPhrase.increaseAccuracy();

			// wrong answer
		} else {
			questionTypes.add(currentQuestionType);
			wrongPhrases.add(currentPhrase);
			wrongAnswers.add(view.getAnswerTextField().getText());

			currentPhrase.decreaseAccuracy();
		}
		// set the phrase to be tested
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		currentPhrase.setLastReviewTime(dateString);

	}

	/**
	 * the action when press the next button
	 */
	private void pressNextButton() {

		switch (testState) {
		case NEXT_PHRASE:
			verifyAnswer();
			// before showing next phrase, clear the label component
			clearComponent();
			showNextPhrase();
			// it's time to finish the test
			if (currentPhraseIndex == tenPhrases.size() - 1) {
				testState = TestState.FINISH_TEST;
			}
			break;
		case FINISH_TEST:
			verifyAnswer();
			// generate the result
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			Date date = new Date();
			String dateString = dateFormat.format(date);
			int score = (int) ((totalCorrectPhraseNum * 1.0 / tenPhrases.size()) * 100);
			if(testType ==TestType.TEST_ONE_LESSON){
				selectedLesson.addTestResult(dateString, score);
				// next lesson can be learned and tested from now on
				if (score >= 80) {
					MainController.getMainController().increaseEnabeLessonIndex();
					this.selectedLesson.setEnable(true);
				}
			}

			// create a result view to show the result
			new ResultController(wrongPhrases, questionTypes, wrongAnswers,
					score);
			view.setVisible(false); // you can't see me!
			view.dispose(); // Destroy the JFrame object
			break;
		}
	}

	/**
	 * Add some listeners to the view
	 */
	private void addActionListener() {
		//if(isAddListener)	return;
		//isAddListener = true;
		// Next phrase listener
		/*for( ActionListener al : view.getNextButton().getActionListeners() ) {
			if(MouseAdapter.class.isInstance(al)){
				continue;
			}
			view.getNextButton().removeActionListener( al );
	    }
		view.nextButtonAddMouseAdapter();*/
		view.getNextButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pressNextButton();
			}
		});

		// sound listener
		view.getSoundButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundEngine.playSound(currentPhrase.getAudio());
			}
		});

		// when exiting, back to main frame
		//view.addExitingReturnToMainController();
	}

	public static void main(String[] args) throws Exception {

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:data/test.db");
		Lesson l = new Lesson(conn, 1);

		new TestController(l);
	}
}
