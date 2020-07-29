package comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.net.ssl.SSLSocket;

import db.DBConnection;
import db.EventAPI;
import db.RoomAPI;
import db.UserAPI;

import model.User;

/**
 * Each connected client gets its own thread running a ServerConnection on the server.
 * @author larskinn
 */
public class ServerConnection implements Runnable {
	DBConnection db;
	EventAPI events;
	RoomAPI rooms;
	UserAPI users;
	SSLSocket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	String clientAddress;
	User connectedUser;
	boolean isRunning;
	
	/**
	 * Create a new ServerConnection.
	 * @param socket the TCP socket from which the connection was initialized
	 */
	public ServerConnection(SSLSocket socket) {
		this.socket = socket;
		clientAddress = socket.getInetAddress().getHostAddress();
		db = new DBConnection();
		events = new EventAPI(db);
		rooms = new RoomAPI(db);
		users = new UserAPI(db);
	}
	
	@Override
	public void run() {
		isRunning = true;
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Connection initialized with client " + clientAddress);
			while (isRunning) {
				Object input = in.readObject();
				if (isLoggedIn())
					handleInput(input);
				else
					handleLogin(input);
			}
		} catch (IOException e) {
			System.err.println("Error with input or output stream: " + e.getMessage());
			isRunning = false;
		} catch (ClassNotFoundException e) {
			System.err.println("The received object doesn't match a known class: " + e.getMessage());
		}
	}
	
	/**
	 * Handle client login. If the input is not an APICall.Login, close the connection.
	 * @param input the APICall as an object
	 */
	private void handleLogin(Object input) {
		if (input instanceof APICall.Login) {
			APICall call = (APICall) input;
			call.execute(this);
		} else {
			closeConnection();
		}
	}
	
	/**
	 * Handle the input from an already logged-in client.
	 * @param input the APICall as an object
	 */
	private void handleInput(Object input) {
		if (input instanceof APICall) {
			APICall call = (APICall) input;
			call.execute(this);
		}
	}

	/**
	 * Close the connection and log out the user.
	 * @throws IOException
	 */
	 void closeConnection() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Error while closing connection: " + e.getMessage());
		}
		setUser(null);
		isRunning = false;
		System.out.println("Connection from " + socket.getInetAddress().getHostAddress() + " closed");
	}
	
	/**
	 * Define which user is logged in on this connection
	 * @param user
	 */
	void setUser(User user) {
		this.connectedUser = user;
	}
	
	/**
	 * Get the user which is logged in on this connection
	 * @return a User object
	 */
	User getUser() {
		return connectedUser;
	}
	
	/**
	 * Send an object to the client
	 * @param o the object to send
	 */
	void sendToClient(Object o) {
		try {
			out.writeObject(o);
		} catch (IOException e) {
			System.err.println("Error while sending object to client: " + e.getMessage());
		}
	}
	
	boolean isLoggedIn() {
		return (connectedUser != null);
	}
	
	EventAPI getEventAPI() {
		return events;
	}
	RoomAPI getRoomAPI() {
		return rooms;
	}
	UserAPI getUserAPI() {
		return users;
	}
}
