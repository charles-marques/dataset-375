package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lambdaworks.crypto.SCryptUtil;

import model.Message;
import model.MessageType;
import model.User;

/**
 * Class UserAPI
 * 
 * This is the place to go if you want to do any
 * interaction with the database concerning users
 */

public class UserAPI {
	static final int N = 8; // CPU cost parameter (must be a power of 2)
	static final int r = 10; // Memory cost parameter
	static final int p = 2;  // Parallelization parameter

	private DBConnection db;

	/**
	 * Constructor for UserAPI
	 * 
	 * @param db	- DBConnection from the server class
	 */
	public UserAPI(DBConnection db) {
		this.db = db;
	}
	
	/**
	 * Take a user and a password, hash the password using scrypt,
	 * and store them in the database
	 * 
	 * @param user a User object
	 * @param password the user's password
	 * @throws Exception if the method is unsuccessful
	 */
	public void addUser(User user, String password) throws Exception {
		String query = "INSERT INTO user(name, username, password, email, position) VALUES(?, ?, ?, ?, ?)";
		PreparedStatement ps;
		String hash = SCryptUtil.scrypt(password, N, r, p);
		
		try {
			ps = db.preparedStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getUsername());
			ps.setString(3, hash);
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPosition());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while adding user: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * Takes a user object and updates it in the database
	 * 
	 * @param user
	 * @throws Exception if the method is unsuccessful
	 */
	public void updateUser(User user) throws Exception {
		String query = "UPDATE user SET name=?, email=?, position=? WHERE username=?";
		
		try {
			PreparedStatement ps = db.preparedStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPosition());
			ps.setString(4, user.getUsername());
			
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while updating user: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * Takes a username and deletes the user related to it in the database
	 * 
	 * @param username
	 * @throws Exception if the method is unsuccessful
	 */
	public void deleteUser(String username) throws Exception {
		String query = "DELETE FROM user WHERE username=?";
		
		try {
			PreparedStatement ps = db.preparedStatement(query);
			ps.setString(1, username);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while deleting user: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * Takes the username and returns the corresponding
	 * user object from database
	 * 
	 * Returns null if no match is found
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		String query = "SELECT * FROM user WHERE username='" + username + "'";
		User user = null;
		
		try {
			ResultSet result = db.singleQuery(query);
			
			if(result.next()) {
				int id = result.getInt("id");
				String userName = result.getString("username");
				String name = result.getString("name");
				String email = result.getString("email");
				String position = result.getString("position");
				
				user = new User(id, userName, name, email, position);			
			}
		} catch (SQLException e) {
			System.err.println("Error while getting user: " + e.getMessage());
		}
		return user;
	}
	
	public String getPasswordHash(String username) {
		String query = "SELECT password FROM user WHERE username = ?";
		String passwordHash = null;
		PreparedStatement ps;
		try {
			ps = db.preparedStatement(query);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				passwordHash = result.getString("password");
			}
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while getting password hash: " + e.getMessage());
		}
		return passwordHash;
		
	}
	
	/**
	 * Takes a string with search criteria as input
	 * Returns an array of matching users
	 * 
	 * @param search
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public List<User> findUsers(String search) {
		List<User> users = new ArrayList<User>();
		String query = "SELECT * FROM user WHERE name LIKE '%" + search + "%'";
		
		try {
			ResultSet result = db.singleQuery(query);
			while(result.next()) {
				int id = result.getInt("id");
				String userName = result.getString("username");
				String name = result.getString("name");
				String email = result.getString("email");
				String position = result.getString("position");
				
				users.add(new User(id, userName, name, email, position));
			}
		} catch (SQLException e) {
			System.err.println("Error while searching for user: " + e.getMessage());
		}
		return users;
	}
	
	/**
	 * Computes and returns all relevant messages for
	 * given user
	 * 
	 * @param username
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public List<Message> getMessages(String username) {
		List<Message> messages = new ArrayList<Message>();
		String query = "SELECT * " +
				"FROM message " +
				"LEFT JOIN user " +
				"ON userId=user.id " +
				"WHERE username='" + username + "' " +
				"ORDER BY timestamp DESC";
		
		try {
			ResultSet result = db.singleQuery(query);
			
			while(result.next()) {
				int eventId = result.getInt("eventId");
				Timestamp timestamp = result.getTimestamp("timestamp");
				MessageType type;
				
				switch(result.getInt("type")) {
				case 0:
					type = MessageType.USER_DECLINED;
					break;
				case 2:
					type = MessageType.ALL_ACCEPTED;
					break;
				case 3:
					type = MessageType.CHANGE;
					break;
				default:
					type = MessageType.INVITE;
					break;
				}
				
				messages.add(new Message(eventId, type, timestamp));
			}
		} catch (SQLException e) {
			System.err.println("Error while getting messages: " + e.getMessage());
		}
		return messages;
	}
}
