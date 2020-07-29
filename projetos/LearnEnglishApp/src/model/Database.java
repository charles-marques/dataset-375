/*
 * Database.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import program.InvalidFileNameException;

/**
 * A class stores the names of account, which use for add and deleting account
 * 
 * @author Eric
 * @version 1.1
 * @see program.InvalidFileNameException
 */

public class Database {

	private Connection connection;
	private ArrayList<String> accountNames;

	/**
	 * Create a new instance of Database.
	 */
	public Database() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(FilenameUtils
					.separatorsToSystem("jdbc:sqlite:data/account_names.db"));
			Statement statement = connection.createStatement();
			statement
					.executeUpdate("CREATE TABLE IF NOT EXISTS account_names (Name UNIQUE);");
			accountNames = new ArrayList<String>();
			/*
			 * account names ResultSet rs =
			 * statement.executeQuery("SELECT * FROM account_names;");
			 * while(rs.next()){ accountNames.add(rs.getString("Name")); }
			 * rs.close();
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create a new instance of Database. use for test
	 * 
	 * @param connection
	 */
	public Database(Connection connection) {
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = connection;
			Statement statement = connection.createStatement();
			statement
					.executeUpdate("CREATE TABLE IF NOT EXISTS account_names (Name UNIQUE);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * create a new account.
	 * 
	 * @param name the name of the account
	 * @return the new instance of account
	 * @throws SQLException access database error
	 * @throws InvalidFileNameException the name is invalid
	 */
	public Account createAccount(String name) throws SQLException,
			InvalidFileNameException {
		// Statement statement = connection.createStatement();
		Account a = new Account(name);
		PreparedStatement prep = connection
				.prepareStatement("INSERT INTO account_names VALUES (?);");
		prep.setString(1, name);
		prep.executeUpdate();
		accountNames.add(name);
		return a;
	}

	/**
	 * delete an account according to the giving index
	 * 
	 * @param index of account
	 */
	public void deleteAccount(int index) {
		try {
			accountNames.remove(index);
			connection.createStatement().executeUpdate(
					"drop table account_names;");
			connection.createStatement().executeUpdate(
					"CREATE TABLE IF NOT EXISTS account_names (Name UNIQUE);");
			PreparedStatement prep = connection
					.prepareStatement("INSERT INTO account_names VALUES (?);");
			int count = accountNames.size();
			for (int i = 0; i < count; ++i) {
				prep.setString(1, accountNames.get(i));
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
	 * just query a small amount of date, do not need a field to store the
	 * names;
	 * 
	 * @return array of names of accounts
	 */
	public ArrayList<String> getAllAccountNames() {
		accountNames.clear();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement
					.executeQuery("SELECT * FROM account_names ;");
			while (result.next()) {
				String name = result.getString("Name");
				accountNames.add(name);
			}
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountNames;
	}
}
