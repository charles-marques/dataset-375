package comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.lambdaworks.crypto.SCryptUtil;

import model.Event;
import model.Message;
import model.Room;
import model.User;

/**
 * A class implementing APICall represents a call to a server-side API method.
 * <p>The call can be executed on the server by running the implementing class'
 * {@code execute()} method, passing in a reference to a running {@code ServerConnection}.
 * @author Lars Kinn Ekroll
 */
public interface APICall {
	/**
	 * Execute the API call.
	 * @param conn - a running ServerConnection
	 */
	void execute(final ServerConnection conn);
	
	/*
	 * Authentication methods
	 */
	
	/**
	 * Log in the user by checking the supplied password against the hash in the
	 * database.
	 * @author Lars Kinn Ekroll
	 */
	class Login implements APICall, Serializable {
		final String username, password;
		
		/**
		 * Log in the user by checking the supplied password against the hash in the
		 * database. Uses the scrypt key derivation function.
		 * <p>If the API call fails, return {@code ServerReply.LOGIN_FAILED} to the client.
		 * @param username - the user's username
		 * @param password - the user's password
		 */
		public Login(String username, String password) {
			this.username = username;
			this.password = password;
		}
		@Override
		public void execute(final ServerConnection conn) {
			String hash = conn.getUserAPI().getPasswordHash(username);
			if (hash != null && SCryptUtil.check(password, hash)) {
				User user = conn.getUserAPI().getUser(username);
				conn.sendToClient(ServerReply.LOGIN_OK);
				conn.sendToClient(ServerReply.NEXT_IS_OBJECT);
				conn.sendToClient(user);
				conn.setUser(user);
			} else {
				conn.sendToClient(ServerReply.LOGIN_FAILED);
			}
		}
	}
	
	/**
	 * Log the current user out and close the connection.
	 * @author Lars Kinn Ekroll
	 */
	class Logout implements APICall, Serializable {
		/**
		 * Log the current user out and close the connection.
		 */
		public Logout() { }
		
		@Override
		public void execute(final ServerConnection conn) {
			conn.closeConnection();
		}
	}
	
	/*
	 * User API
	 */
	
	/**
	 * Update the information about the currently logged in user.
	 * @author Lars Kinn Ekroll
	 */
	class UpdateUser implements APICall, Serializable {
		final User user;
		
		/**
		 * Update the information about the currently logged in user.
		 * <p>If the API call fails, return {@code ServerReply.UPDATE_USER_FAILED} to the client.
		 * @param user - a User object representing the currently logged in user
		 */
		public UpdateUser(User user) {
			this.user = user;
		}
		@Override
		public void execute(final ServerConnection conn) {
			if (user.getUsername().equals(conn.getUser().getUsername())) {
				try {
					conn.getUserAPI().updateUser(user);
				} catch (Exception e) {
					conn.sendToClient(ServerReply.UPDATE_USER_FAILED);
				}
			}
		}
	}
	
	/**
	 * Get a user by username.
	 * @author Lars Kinn Ekroll
	 */
	class GetUser implements APICall, Serializable {
		final String username;
		
		/**
		 * Get a user by username.
		 * <p>The API call returns either a {@code User} object or a {@code ServerReply.EMPTY_RESULT}.
		 * @param username - the username of the wanted user
		 */
		public GetUser(String username) {
			this.username = username;
		}
		@Override
		public void execute(final ServerConnection conn) {
			User user = conn.getUserAPI().getUser(username);
			if (user == null)
				conn.sendToClient(ServerReply.EMPTY_RESULT);
			else {
				conn.sendToClient(ServerReply.NEXT_IS_OBJECT);
				conn.sendToClient(user);
			}
		}
	}
	
	/**
	 * Get a list of users by searching through all users' names.
	 * @author Lars Kinn Ekroll
	 *
	 */
	class FindUsers implements APICall, Serializable {
		final String search;
		/**
		* Get a list of users by searching through all users' names.
		* <p>The API call returns a {@code List<User>} or a {@code ServerReply.EMPTY_RESULT}.
		* @param search - the search string
		*/
		public FindUsers(String search) {
			this.search = search;
		}
		@Override
		public void execute(final ServerConnection conn) {
			List<User> result = conn.getUserAPI().findUsers(search);
			conn.sendToClient(ServerReply.NEXT_IS_OBJECT);				
			conn.sendToClient(result);
		}
	}
	
	/**
	 * Get a list of messages concerning the currently logged in user.
	 * @author Lars Kinn Ekroll
	 */
	class GetMessages implements APICall, Serializable {
		final String username;
		
		/**
		 * Get a list of messages concerning the currently logged in user.
		 * <p>The API call returns a {@code List<Message>} or a {@code ServerReply.EMPTY_RESULT}.
		 * @param username - the user's username
		 */
		public GetMessages(String username) {
			this.username = username;
		}
		@Override
		public void execute(final ServerConnection conn) {
			List<Message> result = conn.getUserAPI().getMessages(username);
			conn.sendToClient(ServerReply.NEXT_IS_OBJECT);				
			conn.sendToClient(result);
		}
	}
	
	/*
	 * Event API
	 */
	/**
	 * Add a new event.
	 * @author Lars Kinn Ekroll
	 */
	class AddEvent implements APICall, Serializable {
		final Event event;
		
		/**
		 * Add a new event.
		 * <p>If the API call fails, return {@code ServerReply.ADD_EVENT_FAILED} to the client.
		 * @param event - an Event object representing a new event
		 */
		public AddEvent(Event event) {
			this.event = event;
		}
		@Override
		public void execute(final ServerConnection conn) {
			try {
				event.setLeader(conn.getUser());
				conn.getEventAPI().addEvent(event);
				conn.sendToClient(ServerReply.EVENT_ADDED);
			} catch (Exception e) {
				conn.sendToClient(ServerReply.ADD_EVENT_FAILED);
			}
		}
	}
	
	/**
	 * Update an existing event.
	 * @author Lars Kinn Ekroll
	 */
	class UpdateEvent implements APICall, Serializable {
		final Event event;
		
		/**
		 * Update an existing event.
		 * <p>If the API call fails, return {@code ServerReply.UPDATE_EVENT_FAILED} to the client.
		 * @param event - an Event object representing the updated event
		 */
		public UpdateEvent(Event event) {
			this.event = event;
		}
		@Override public void execute(final ServerConnection conn) {
			try {
				conn.getEventAPI().updateEvent(event);
				conn.sendToClient(ServerReply.EVENT_UPDATED);
			} catch (Exception e) {
				conn.sendToClient(ServerReply.UPDATE_EVENT_FAILED);
			}
		}
	}
	
	/**
	 * Get an event by ID.
	 * @author Lars Kinn Ekroll
	 */
	class GetEvent implements APICall, Serializable {
		final int id;
		
		/**
		 * Get an event by ID.
		 * <p>The API call returns an {@code Event} object or a {@code ServerReply.EMPTY_RESULT}.
		 * @param id - the event's ID
		 */
		public GetEvent(int id) {
			this.id = id;
		}
		@Override
		public void execute(final ServerConnection conn) {
			Event event = conn.getEventAPI().getEvent(id);
			if (event == null)
				conn.sendToClient(ServerReply.EMPTY_RESULT);
			else {
				conn.sendToClient(ServerReply.NEXT_IS_OBJECT);				
				conn.sendToClient(event);
			}
		}
	}
	
	/**
	 * Get all the events for the specified users in the specified period
	 * @author Lars Kinn Ekroll
	 */
	class GetEventsInPeriod implements APICall, Serializable {
		final Calendar start, end;
		final List<User> users = new ArrayList<User>();
		
		/**
		 * Get all the events for the current user in the specified period
		 * <p>The API call returns a {@code List<Event>} or a {@code ServerReply.EMPTY_RESULT}.
		 * @param start - the start date/time
		 * @param end - the end date/time
		 */
		public GetEventsInPeriod(Calendar start, Calendar end) {
			this.start = start;
			this.end = end;
		}
		
		/**
		 * Get all the events for the current user and the specified users in the specified period
		 * <p>The API call returns a {@code List<Event>} or a {@code ServerReply.EMPTY_RESULT}.
		 * @param start - the start date/time
		 * @param end - the end date/time
		 * @param users - a list of User objects
		 */
		public GetEventsInPeriod(Calendar start, Calendar end, List<User> users) {
			this.start = start;
			this.end = end;
			this.users.addAll(users);
		}
		
		@Override
		public void execute(final ServerConnection conn) {
			users.add(conn.getUser());
			List<Event> events = conn.getEventAPI().getEventsInPeriod(users, start, end);
			conn.sendToClient(ServerReply.NEXT_IS_OBJECT);				
			conn.sendToClient(events);
		}
	}
	
	/**
	 * Delete an event by ID.
	 * @author Lars Kinn Ekroll
	 */
	class DeleteEvent implements APICall, Serializable {
		final int id;
		
		/**
		 * Delete an event by ID.
		 * <p>If the API call fails, return {@code ServerReply.DELETE_EVENT_FAILED} to the client.
		 * @param id - the event's ID
		 */
		public DeleteEvent(int id) {
			this.id = id;
		}

		@Override
		public void execute(final ServerConnection conn) {
			try {
				conn.getEventAPI().deleteEvent(id);
			} catch (Exception e) {
				conn.sendToClient(ServerReply.DELETE_EVENT_FAILED);
			}
		}
	}
	
	/**
	 * Accept an event invitation.
	 * @author Lars Kinn Ekroll
	 */
	class AcceptEvent implements APICall, Serializable {
		final int id;
		
		/**
		 * Accept an event invitation.
		 * <p>If the API call fails, return {@code ServerReply.ACCEPT_EVENT_FAILED} to the client.
		 * @param id - the event's ID
		 */
		public AcceptEvent(int id) {
			this.id = id;
		}
		@Override
		public void execute(final ServerConnection conn) {
			try {
				conn.getEventAPI().acceptEvent(id, conn.getUser());
				conn.sendToClient(ServerReply.EVENT_ACCEPTED);
			} catch (Exception e) {
				conn.sendToClient(ServerReply.ACCEPT_EVENT_FAILED);
			}
		}
	}
	
	/**
	 * Decline an event invitation.
	 * @author Lars Kinn Ekroll
	 */
	class DeclineEvent implements APICall, Serializable {
		final int id;
		
		/**
		 * Decline an event invitation.
		 * <p>If the API call fails, return {@code ServerReply.DECLINE_EVENT_FAILED} to the client.
		 * @param id - the event's ID
		 */
		public DeclineEvent(int id) {
			this.id = id;
		}
		@Override
		public void execute(final ServerConnection conn) {
			try {
				conn.getEventAPI().declineEvent(id, conn.getUser());
			} catch (Exception e) {
				conn.sendToClient(ServerReply.DECLINE_EVENT_FAILED);
			}
		}
	}
	
	/*
	 * Room API
	 */
	
	/**
	 * Get the available meeting rooms in a period.
	 * @author Lars Kinn Ekroll
	 */
	class GetAvailableRooms implements APICall, Serializable {
		final Calendar start, end;
		
		/**
		 * Get the available meeting rooms in a period.
		 * <p>The API call returns a {@code List<Room>} or a {@code ServerReply.EMPTY_RESULT}.
		 * @param start - the start date/time
		 * @param end - the end date/time
		 */
		public GetAvailableRooms(Calendar start, Calendar end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public void execute(final ServerConnection conn) {
			List<Room> rooms = conn.getRoomAPI().getAvailableRooms(start, end);
			conn.sendToClient(ServerReply.NEXT_IS_OBJECT);				
			conn.sendToClient(rooms);
		}
	}
}