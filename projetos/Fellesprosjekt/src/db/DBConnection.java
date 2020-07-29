package db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * Class DBConnection
 *
 * Class to handle the database connection
 * It will keep track of how many queries have been
 * executed, and close and reopen the connection when
 * a given limit is reached. 
 */

public class DBConnection {

	private Connection connection;
	private String driver;
	private String url;
	private String dbName;
	private String host;
	private String dbUsername;
	private String dbPassword;
	
	/**
	 * DBConnection constructor
	 * 
	 * @param limit						- limit	how many queries to execute before connection reset
	 */
	public DBConnection() {
		Properties connectionProperties = new Properties();
		
		ClassLoader cldr = this.getClass().getClassLoader();
		java.net.URL fileUrl = cldr.getResource("db/Properties.properties");
		
		try {
			connectionProperties.load(new FileInputStream(new File(fileUrl.toURI())));
			driver = connectionProperties.getProperty("jdbcDriver");
			host = connectionProperties.getProperty("dbAddress");
			dbName = connectionProperties.getProperty("dbName");
			url = host + "/" + dbName;
			dbUsername = connectionProperties.getProperty("dbUsername");
			dbPassword = connectionProperties.getProperty("dbPassword");
			
			setupDB();
		} catch (FileNotFoundException e) {
			System.err.println("Fatal error - could not find properties file: " + e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Fatal error - could not read the properties file: " + e.getMessage());
			System.exit(1);
		} catch (URISyntaxException e) {
			System.err.println("Fatal error - invalid URL for properties file: " + e.getMessage());
			System.exit(1);
		}
		// TODO: replace System.exit() calls with calls that only stop the current thread
		
	}
	
	/**
	 * Handles setting up the database
	 */
	protected void setupDB() {
		try {
			Class.forName(driver);
			Properties properties = new Properties();
			properties.setProperty("user", dbUsername);
			properties.setProperty("password", dbPassword);
			
			connection = DriverManager.getConnection(url, properties);
		} catch (ClassNotFoundException e) {
			System.err.println("Fatal error - JDBC driver not found: " + e.getMessage());
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Fatal error - could not connect to database: " + e.getMessage());
			System.exit(1);
		}
		// TODO: replace System.exit() calls with calls that only stop the current thread
		
	}
	
	/**
	 * Resets the connection if more queries than the limit
	 * has been executed
	 * 
	 * @throws SQLException
	 */
	
	/**
	 * Handles execution of a single query
	 * 
	 * @param query
	 * @return ResultSet
	 * @throws SQLException
	 */
	public ResultSet singleQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	/**
	 * Handles single queries, like insert and update.
	 * 
	 * @param query
	 * @return int						- Count of affected rows
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException {
		Statement st = connection.createStatement();
		int result = st.executeUpdate(query);
		return result;
	}
	
	/**
	 * Handles preparing the query statement
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement preparedStatement(String query) throws SQLException {
		return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	}
	
	/**
	 * Closes a query
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public void closeQuery(ResultSet rs) throws SQLException {
		if(rs.getStatement() != null) {
			rs.getStatement().close();
		} else {
			rs.close();
		}
	}
	
	public void commit() throws SQLException {
		connection.commit();
	}
	
	/**
	 * Handles closing the connection
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
