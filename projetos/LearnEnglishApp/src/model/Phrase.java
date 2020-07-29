/*
 * Phrase.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package model;

/**
 * A class stores all the information of a phrase, namely Chinese, English,
 * sound file path, its time of last reviewed, accuracy, count of testing and so
 * forth.
 * 
 * @author Eric
 * @version 1.1
 * @see Lesson
 * 
 */
public class Phrase {

	private String chinese;
	private String english;
	private String audio;
	private String lastReviewTime;
	private int accuracy;
	private int testCount;
	private String lessonName;

	/**
	 * Create a new instance of phrase
	 * 
	 * @param english English phrase
	 * @param chinese Chinese phrase
	 * @param audio audio file path
	 */
	public Phrase(String english, String chinese, String audio) {
		this.chinese = chinese;
		this.english = english;
		this.audio = audio;
	}

	/**
	 * Create a new instance of phrase
	 * 
	 * @param english English phrase
	 * @param chinese Chinese phrase
	 * @param audio audio file path
	 * @param time time of last reviewed
	 * @param accuracy test accuracy
	 * @param count count of all test
	 */
	public Phrase(String english, String chinese, String audio, String time,
			int accuracy, int count) {
		this.chinese = chinese;
		this.english = english;
		this.audio = audio;
		lastReviewTime = time;
		this.accuracy = accuracy;
		testCount = count;
	}

	/**
	 * copy constructor
	 * 
	 * @param p phrase to be copied
	 */
	public Phrase(Phrase p) {
		chinese = p.chinese;
		english = p.english;
		audio = p.audio;
	}

	/**
	 * get the name of the lesson.
	 * 
	 * @return lessonName name of the lesson
	 */
	public String getLessonName() {
		return lessonName;
	}

	/**
	 * set the name of the lesson
	 * 
	 * @param lessonName the lessonName to set
	 */
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	/**
	 * get count of all test
	 * 
	 * @return testCount count of all test
	 */
	public int getTestCount() {
		return testCount;
	}

	/**
	 * get Chinese phrase
	 * 
	 * @return chinese Chinese phrase
	 */
	public String getChinese() {
		return chinese;
	}

	/**
	 * set Chinese phrase
	 * 
	 * @param chinese the Chinese phrase to be set
	 */
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	/**
	 * get the English phrase
	 * 
	 * @return english English phrase
	 */
	public String getEnglish() {
		return english;
	}

	/**
	 * set the English phrase
	 * 
	 * @param english the English phrase to be set
	 */
	public void setEnglish(String english) {
		this.english = english;
	}

	/**
	 * get the audio file path
	 * 
	 * @return audio the audio path
	 */
	public String getAudio() {
		return audio;
	}

	/**
	 * get the audio file path
	 * 
	 * @param audio the audio to be set
	 */
	public void setAudio(String audio) {
		this.audio = audio;
	}

	/**
	 * get the time of last reviewed
	 * 
	 * @return lastReviewTime time of last reviewed
	 */
	public String getLastReviewTime() {
		return lastReviewTime;
	}

	/**
	 * set the time of last reviewed
	 * 
	 * @param lastReviewTime the lastReviewTime to be set
	 */
	public void setLastReviewTime(String lastReviewTime) {
		this.lastReviewTime = lastReviewTime;
	}

	/**
	 * set the test accuracy
	 * 
	 * @return accuracy the test accuracy
	 */
	public int getAccuracy() {
		return accuracy;
	}

	/**
	 * increase the test accuracy
	 */
	public void increaseAccuracy() {
		int i = (int) ((int) testCount * accuracy * 0.01);
		accuracy = (int) (++i * 100.0 / ++testCount);
	}

	/**
	 * decrease the test accuracy
	 */
	public void decreaseAccuracy() {
		int i = (int) ((int) testCount * accuracy * 0.01);
		accuracy = (int) (i * 100.0 / ++testCount);
	}
}
