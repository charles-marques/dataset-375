/*
 * Lesson.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */
package model;

/**
 * @version 0.1
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import org.apache.commons.io.FilenameUtils;

/**
 * A class represents lesson in the real world. It contains lots of phrases, it
 * store the phrases in SQLite. It also stores the result of tests
 * 
 * @author Eric
 * @version 1.1
 * @see Phrase
 * 
 */
public class Lesson {
	private ArrayList<Phrase> phrases;
	private Connection connection;
	private Statement statement;
	private PreparedStatement prep;

	private String lessonName;
	private int phraseCount;
	private boolean isEnable;
	private boolean isNeededToRestore;

	// there is no need to use Hash for times and scores for test result,
	// we do not need to get one according to another, we just need to iterate
	// them.
	private ArrayList<String> testResultTimes;
	private ArrayList<Integer> testResultScores;
	private ArrayList<String> newTestResultTimes;
	private ArrayList<Integer> newTestResultScores;

	/**
	 * Create a new instance of Lesson.
	 * 
	 * @param connection connection to the database
	 * @param lessonName name of the lesson
	 * @param isEnabled ability to be tested or learned
	 * @throws SQLException exception to load database, create table
	 */
	public Lesson(Connection connection, String lessonName, boolean isEnabled)
			throws SQLException {
		this.connection = connection;
		this.lessonName = lessonName;
		this.phraseCount = 0;
		this.isEnable = isEnabled;
		phrases = new ArrayList<Phrase>();
		testResultTimes = new ArrayList<String>();
		testResultScores = new ArrayList<Integer>();
		newTestResultTimes = new ArrayList<String>();
		newTestResultScores = new ArrayList<Integer>();

		statement = connection.createStatement();
		statement
				.executeUpdate("CREATE TABLE IF NOT EXISTS "
						+ lessonName
						+ " (English, Chinese, Audio, LastReviewTime, Accuracy, TestCount);");
	}

	/**
	 * load default lesson with index
	 * 
	 * @param connection connection to the database
	 * @param index index of default lesson
	 * @throws SQLException exception to load database, create table
	 */
	public Lesson(Connection connection, int index) throws Exception {
		this.connection = connection;
		this.phraseCount = 0;

		phrases = new ArrayList<Phrase>();
		testResultTimes = new ArrayList<String>();
		testResultScores = new ArrayList<Integer>();
		newTestResultTimes = new ArrayList<String>();
		newTestResultScores = new ArrayList<Integer>();

		statement = connection.createStatement();
		loadDefaultLesson(index);
	}

	/**
	 * get the time of results
	 * 
	 * @return testResultTimes the time of results
	 */
	public ArrayList<String> getTestResultTimes() {
		return testResultTimes;
	}

	/**
	 * get the score of results
	 * 
	 * @return the testResultScores the score of the results
	 */
	public ArrayList<Integer> getTestResultScores() {
		return testResultScores;
	}

	/**
	 * is the lesson enable?
	 * 
	 * @return the isEnable
	 */
	public boolean isEnable() {
		return isEnable;
	}

	/**
	 * set the isEnable
	 * 
	 * @param isEnable the isEnable to set
	 */
	public void setEnable(boolean isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * get the need to store
	 * 
	 * @return isNeededToRestore is need to store?
	 */
	public boolean isNeededToRestore() {
		return isNeededToRestore;
	}

	/**
	 * set the need to store
	 * 
	 * @param isNeededToRestore the need to store
	 */
	public void setNeededToRestore(boolean isNeededToRestore) {
		this.isNeededToRestore = isNeededToRestore;
	}

	/**
	 * get the name of lesson
	 * 
	 * @return lessonName name of lesson
	 */
	public String getLessonName() {
		return lessonName;
	}

	/**
	 * set the name of lesson
	 * 
	 * @param lessonName the lessonName to set
	 */
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	/**
	 * get the count of phrases
	 * 
	 * @return phraseCount the count of phrases
	 */
	public int getPhraseCount() {
		return phraseCount;
	}

	/**
	 * get the phrase according to the index
	 * 
	 * @param index the index of the phrase
	 * @return
	 */
	public Phrase getPhrase(int index) {
		return phrases.get(index);
	}

	/**
	 * get all the phrases
	 * 
	 * @return all the phrases
	 */
	public ArrayList<Phrase> getAllPhrases() {
		return phrases;
	}

	/**
	 * load default lesson of the giving index
	 * 
	 * @param index the index of default lesson
	 */
	public void loadDefaultLesson(int index) {
		try {
			lessonName = "Lesson_" + index;

			statement
					.executeUpdate("create table if not exists "
							+ lessonName
							+ "(English, Chinese, Audio, LastReviewTime, Accuracy, TestCount);");
			prep = connection.prepareStatement("INSERT INTO " + lessonName
					+ " VALUES (?, ?, ?, ?, ?, ?);");

			BufferedReader br = new BufferedReader(new FileReader(
					FilenameUtils
							.separatorsToSystem("data/DefaultLessons/Lesson_"
									+ index + ".csv")));
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(","); // separator

				Phrase p = new Phrase(values[0], values[1], values[2]);
				phrases.add(p);

				// Use a PeparedStatemant, it?s easier and safer
				prep.setString(1, values[0]);
				prep.setString(2, values[1]);
				prep.setString(3, values[2]);
				prep.addBatch();
				phraseCount++;
			}
			br.close();

			connection.setAutoCommit(false);
			prep.executeBatch();
			connection.commit();

			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + lessonName
					+ "_result (Time, Score);");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load the lesson from the database
	 */
	public void loadFromDatabase() {
		try {
			// phrases
			statement
					.executeUpdate("CREATE TABLE IF NOT EXISTS "
							+ lessonName
							+ " (English, Chinese, Audio, LastReviewTime, Accuracy, TestCount);");
			ResultSet rs = statement.executeQuery("SELECT * FROM " + lessonName
					+ ";");
			while (rs.next()) {
				Phrase p = new Phrase(rs.getString("English"),
						rs.getString("Chinese"), rs.getString("Audio"),
						rs.getString("LastReviewTime"), rs.getInt("Accuracy"),
						rs.getInt("TestCount"));
				phrases.add(p);
				phraseCount++;
			}
			rs.close();

			// test results
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + lessonName
					+ "_result (Time, Score);");
			rs = statement.executeQuery("SELECT * FROM " + lessonName
					+ "_result ;");
			while (rs.next()) {
				testResultTimes.add(rs.getString("Time"));
				testResultScores.add(rs.getInt("Score"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * get no more than ten phrases from the lesson
	 * 
	 * @return no more than ten phrases from the lesson
	 */
	public ArrayList<Phrase> getRandomTenPhrase() {
		ArrayList<Phrase> phrases = new ArrayList<Phrase>();
		// TODO
		int count = this.phrases.size();
		if (phraseCount > 10) {
			count = 10;
		}

		HashSet<Integer> randomNums = getRandomNumSet(count,
				this.phrases.size());
		Iterator<Integer> itr = randomNums.iterator();
		try {
			while (itr.hasNext()) {
				Integer element = itr.next();
				Phrase p = getPhrase(element.intValue());
				p.setLessonName(lessonName);
				phrases.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return phrases;
	}

	/**
	 * get random nums
	 * 
	 * @param num the count of num
	 * @param range the range
	 * @return
	 */
	private static HashSet<Integer> getRandomNumSet(int num, int range) {
		HashSet<Integer> randomNums = new HashSet<Integer>(num);
		Random random = new Random();
		while (randomNums.size() < num) {
			int randomNum = random.nextInt(range);
			if (randomNum < 0)
				randomNum = -randomNum;
			randomNums.add(new Integer(randomNum));
		}
		return randomNums;
	}

	/**
	 * add the phrase to the database
	 * 
	 * @param english
	 * @param chinese
	 * @param audio
	 * @throws SQLException
	 */
	public void addPharse(String english, String chinese, String audio)
			throws SQLException {
		prep = connection.prepareStatement("INSERT INTO " + lessonName
				+ " VALUES (?, ?, ?, ?, ?, ?);");
		prep.setString(1, english);
		prep.setString(2, chinese);
		prep.setString(3, audio);
		prep.addBatch();
		connection.setAutoCommit(false);
		prep.executeBatch();
		connection.setAutoCommit(true);

		phraseCount++;
	}

	/**
	 * add phrase
	 * 
	 * @param p the phrase to add
	 */
	public void addPhrase(Phrase p) {
		phrases.add(p);
		this.isNeededToRestore = true;
	}

	/**
	 * delete phrase from the lesson
	 * 
	 * @param p
	 */
	public void deletePhrase(Phrase p) {
		phrases.remove(p);
		this.isNeededToRestore = true;
	}

	/**
	 * close the database
	 */
	public void close() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method seems crude to delete the old lesson table and create a new
	 * one. We can implement another method that we can create several ArrayList
	 * to store the deleting, adding, editing phrases and optimally write the
	 * delete, insert and update statements. But we have to pay attention to the
	 * two facts. The first one is that when the user learn or test the whole
	 * lesson, which happens frequently, the whole lesson must be updated, which
	 * cost more than deleting all and inserting them back. Secondly, the amount
	 * of phrases of one lesson is no more than a thousand at most time.
	 * Rewriting it may cost slightly more. On the other hand, the method of
	 * second version gains little, but introduces unnecessary complexity and
	 * more likely introduces bugs in the program and difficulty in maintenance.
	 */
	public void writeToDatabase() {
		try {
			// phrase
			statement.executeUpdate("DROP TABLE IF EXISTS " + lessonName + ";");
			statement
					.executeUpdate("CREATE TABLE "
							+ lessonName
							+ " (English, Chinese, Audio, LastReviewTime, Accuracy, TestCount);");
			prep = connection.prepareStatement("INSERT INTO " + lessonName
					+ " VALUES (?, ?, ?, ?, ?, ?);");
			Iterator<Phrase> itr = phrases.iterator();
			while (itr.hasNext()) {
				Phrase p = itr.next();
				prep.setString(1, p.getEnglish());
				prep.setString(2, p.getChinese());
				prep.setString(3, p.getAudio());
				prep.setString(4, p.getLastReviewTime());
				prep.setInt(5, p.getAccuracy());
				prep.setInt(6, p.getTestCount());
				prep.addBatch();
			}
			connection.setAutoCommit(false);
			prep.executeBatch();
			connection.commit();

			// result
			prep = connection.prepareStatement("INSERT INTO " + lessonName
					+ "_result VALUES (?, ?);");
			Iterator<String> timeItr = newTestResultTimes.iterator();
			Iterator<Integer> scoreItr = newTestResultScores.iterator();
			while (timeItr.hasNext() && scoreItr.hasNext()) {
				prep.setString(1, timeItr.next());
				prep.setInt(2, scoreItr.next());
				prep.addBatch();
			}
			connection.setAutoCommit(false);
			prep.executeBatch();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * add test result
	 * 
	 * @param time the time of result
	 * @param score the score of result
	 */
	public void addTestResult(String time, int score) {
		newTestResultTimes.add(time);
		newTestResultScores.add(score);
		testResultTimes.add(time);
		testResultScores.add(score);
	}

	/**
	 * clear the data.
	 */
	public void deleteSelf() {
		try {
			statement.executeUpdate("DROP TABLE " + lessonName + ";");
			statement.executeUpdate("DROP TABLE " + lessonName + "_result;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
