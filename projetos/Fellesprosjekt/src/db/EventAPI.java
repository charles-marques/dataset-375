package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.Event;
import model.MessageType;
import model.Room;
import model.Status;
import model.User;

/**
 * Class EventAPI
 * 
 * This is the place to go if you want to do any
 * interaction with the database concerning event
 */

public class EventAPI {
	
	private DBConnection db;
	
	/**
	 * Constructor for EventAPI
	 * 
	 * @param db	- DBConnection from server
	 */
	public EventAPI(DBConnection db) {
		this.db = db;
	}
	
	/**
	 * Handles getting an event from database
	 * 
	 * @param id
	 * @return
	 */
	public Event getEvent(int id) {
		// Get event information
		String query = 	"SELECT event.*, leader.*, room.id AS rId, room.name AS rName, room.size AS rSize " +
				"FROM event " +
				"LEFT JOIN meeting_room AS room " +
				"ON event.meetingRoomId = room.id " +
				"LEFT JOIN user AS leader " +
				"ON event.ownerId = leader.id " +
				"WHERE event.id=" + id;
		
		Event event = null;
		try {
			ResultSet result = db.singleQuery(query);
			result.next();
			event = makeEventFromDB(result);
		} catch (SQLException e) {
			System.err.println("Error while getting event: " + e.getMessage());
		}
		return event;
	}
	
	/**
	 * Returns a status based on inputed integer
	 */
	protected Status intToStatus(int dbValue) {
		Status status = Status.WAITING;
		switch(dbValue) {
		case 0:
			status = Status.DECLINED;
			break;
		case 1:
			status = Status.WAITING;
			break;
		case 2:
			status = Status.ACCEPTED;
			break;
		}
		return status;
	}
	
	protected Event makeEventFromDB(ResultSet result) throws SQLException {
		int id = result.getInt("id");
		String name = result.getString("title");
		Calendar start = Calendar.getInstance();
		start.setTimeInMillis(result.getTimestamp("start_time").getTime());
		Calendar end = Calendar.getInstance();
		end.setTimeInMillis(result.getTimestamp("end_time").getTime());
		String info = result.getString("info");
		int rId = result.getInt("rId");
		Room room = null;
		if(rId != 0) {
			String rName = result.getString("rName");
			int rSize = result.getInt("rSize");
			room = new Room(rId, rSize, rName);			
		}
		String location = result.getString("location");
		
		// Create user object of leader
		int lId = result.getInt("leader.id");
		String lName = result.getString("leader.name");
		String lUsername = result.getString("leader.username");
		String lEmail = result.getString("leader.email");
		String lPosition = result.getString("leader.position");
		
		User leader = new User(lId, lUsername, lName, lEmail, lPosition);
		
		// Get information on participants
		String participantQuery = 	"SELECT part.*, user.* " +
									"FROM participant AS part " +
									"LEFT JOIN user " +
									"ON part.userId = user.id " +
									"WHERE part.eventId=" + id;
		
		ResultSet participants = db.singleQuery(participantQuery);
		participants.beforeFirst();
		
		Map<User, Status> userStatus = new HashMap<User, Status>();
		
		while(participants.next()) {
			int status = participants.getInt("status");
			int uId = participants.getInt("user.id");
			String uName = participants.getString("user.name");
			String uUsername = participants.getString("user.username");
			String uEmail = participants.getString("user.email");
			String uPosition = participants.getString("user.position");
			
			userStatus.put(new User(uId, uUsername, uName, uEmail, uPosition), intToStatus(status));
		}
		
		return new Event(id, name, start, end, info, leader, room, location, userStatus);
	}
	
	/**
	 * Delete event based on given id
	 * 
	 * @param id
	 * @throws Exception if the method is unsuccessful
	 */
	public void deleteEvent(int id) throws Exception {
		String query = "DELETE FROM event WHERE id=?";
		
		try {
			PreparedStatement ps = db.preparedStatement(query);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while deleting event: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * Gets list of events in period for one user
	 * 
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Event> getEventsInPeriod(User user, Calendar start, Calendar end) {
		List<User> users = new ArrayList<User>();
		users.add(user);
		
		return getEventsInPeriod(users, start, end);
	}
	
	/**
	 * Handles getting events from a period of time form 
	 * the database for list of users
	 * 
	 * @param user
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Event> getEventsInPeriod(List<User> users, Calendar start, Calendar end) {
		List<Event> events = new ArrayList<Event>();
		
		String partValue = " (";
		String leaderValue = " (";

		int userId = 0;
		
		for(int i = 0; i < users.size(); i++) {
			userId = users.get(i).getID();
			if(i == 0) {
				partValue += "part.userId=" + userId;
				leaderValue += "leader.id=" + userId;
			} else {
				partValue += " OR part.userId=" + userId;
				leaderValue += " OR leader.id=" + userId;
			}
		}
		partValue += ") ";
		leaderValue += ") ";
		
		String query = 	"SELECT DISTINCT event.*, leader.*, room.id AS rId, room.name AS rName, room.size AS rSize\n" +
						"FROM event\n" +
						"LEFT JOIN meeting_room AS room\n" +
						"ON event.meetingRoomId = room.id\n" +
						"LEFT JOIN user AS leader\n" +
						"ON event.ownerId = leader.id\n" +
						"LEFT JOIN participant AS part\n" +
						"ON event.id=part.eventId AND " + partValue + "\n" +
						"WHERE event.start_time>='"+ new Timestamp(start.getTimeInMillis()) + "' AND\n" +
						"event.end_time<='" + new Timestamp(end.getTimeInMillis()) + "' AND\n" +
						"(" + leaderValue + " OR " + partValue + ") \n" +
						"ORDER BY start_time";
		ResultSet result;
		try {
			result = db.singleQuery(query);
			while(result.next()) {
				events.add(makeEventFromDB(result));
			}
		} catch (SQLException e) {
			System.err.println("Error while getting events in period: " + e.getMessage());
		}
		return events;
	}
	
	/**
	 * Handles adding event to database
	 * 
	 * @param event
	 * @throws Exception if the method is unsuccessful
	 */
	public int addEvent(Event event) throws Exception {
		String query = "INSERT INTO event(title, start_time, end_time, info, ownerId, meetingRoomId, location) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement ps;
		try {
			ps = db.preparedStatement(query);
			
			ps.setString(1, event.getName());
			ps.setTimestamp(2, new Timestamp(event.getStart().getTimeInMillis()));
			ps.setTimestamp(3, new Timestamp(event.getEnd().getTimeInMillis()));
			ps.setString(4, event.getInfo());
			ps.setInt(5, event.getLeader().getID());
			if(event.getRoom() != null) {
				ps.setInt(6, event.getRoom().getID());
			} else {
				ps.setNull(6, java.sql.Types.INTEGER);
			}
			ps.setString(7, event.getLocation());
			
			ps.execute();
			
			ResultSet gen = ps.getGeneratedKeys();
			gen.next();
			int id = gen.getInt(1);
			
			
			ps.close();
			
			
			// Add users to participants table
			updateParticipantsInDB(event, id);
			sendInvite(event);

			return id;
		} catch (SQLException e) {
			System.err.println("Error while adding event: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	/**
	 * Adds users to participants table with status waiting
	 * 
	 * @param event
	 * @param id
	 * @throws SQLException
	 */
	protected void updateParticipantsInDB(Event event, int id) throws SQLException {
		String deleteQuery = "DELETE FROM participant WHERE eventId=?";
		
		PreparedStatement deletePs = db.preparedStatement(deleteQuery);
		deletePs.setInt(1, id);
		deletePs.execute();
		deletePs.close();
		
		String query = "INSERT INTO participant " +
				"SET userId=?, eventId=?, status=?";
		
		PreparedStatement ps = db.preparedStatement(query);
				
		Iterator<Entry<User, Status>> it = event.getUserStatuses().entrySet().iterator();

		while(it.hasNext()) {
			Entry<User, Status> pairs = it.next();
			int uId = ((User) pairs.getKey()).getID();
			ps.setInt(1, uId);
			ps.setInt(2, id);
			ps.setInt(3, 1); // 1 = WAITING
			ps.addBatch();
			it.remove();
		}
		ps.executeBatch();
		//db.commit();
		ps.close();
	}
	
	/**
	 * Handles updating the event in database
	 * 
	 * @param event
	 * @throws Exception if the method is unsuccessful
	 */
	public void updateEvent(Event event) throws Exception {
		String query = "UPDATE event SET title=?, start_time=?, end_time=?, info=?, meetingRoomId=?, location=? WHERE id=?";
		
		try {
			PreparedStatement ps = db.preparedStatement(query);
			
			ps.setString(1, event.getName());
			ps.setTimestamp(2, new Timestamp(event.getStart().getTimeInMillis()));
			ps.setTimestamp(3, new Timestamp(event.getEnd().getTimeInMillis()));
			ps.setString(4, event.getInfo());
			if(event.getRoom() != null) {
				ps.setInt(5, event.getRoom().getID());
			} else {
				ps.setNull(5, java.sql.Types.INTEGER);
			}
			ps.setString(6, event.getLocation());
			ps.setInt(7, event.getId());
			
			ps.execute();
			ps.close();
			
			// Set all participants to WAITING
			updateParticipantsInDB(event, event.getId());
			sendInvite(event);
		} catch (SQLException e) {
			System.err.println("Error while updating event: " + e.getMessage());
			throw new Exception(e);
		}
		
	}
	
	protected void sendInvite(Event event) {
		String message =	"INSERT INTO message " +
							"SET userId=?, eventId=?, type=? " +
							"ON DUPLICATE KEY UPDATE userId=?, eventId=?, type=?";
		
		Map<User, Status> users = event.getUserStatuses();
		
		Iterator<Entry<User, Status>> it = users.entrySet().iterator();
		try {
			PreparedStatement ps = db.preparedStatement(message);
			
			int countI = 0;
			while(it.hasNext()) {
				Entry<User, Status> pairs = it.next();
				int uId = ((User) pairs.getKey()).getID();
				
				ps.setInt(1, uId);
				ps.setInt(4, uId);
				ps.setInt(2, event.getId());
				ps.setInt(5, event.getId());
				ps.setInt(3, 1); // Invite status
				ps.setInt(6, 1); // Invite status
				ps.addBatch();
				++countI;
				
				if(countI % 1000 == 0 || !it.hasNext()) {
					ps.executeBatch();
				}
			}
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while creating invite: " + e.getMessage());
		}
		
		
	}
	
	/**
	 * Sets the users relation to the event to accepted / participating
	 * 
	 * @param event
	 * @param user
	 * @throws Exception if the method is unsuccessful
	 */
	public void acceptEvent(int eventId, User user) throws Exception {
		String query = "UPDATE participant SET status='2' WHERE eventId=" + eventId + " AND userId=" + user.getID();
		
		// Check if all invited users have declined
		String isAllAccepted = "SELECT COUNT(*) AS accepted FROM participant WHERE eventId=" + eventId + " AND status=2";

		Event event = getEvent(eventId);
		
		try {
			db.update(query);

			// Get number of users who have accepted
			ResultSet result = db.singleQuery(isAllAccepted);
			result.next();
			int count = result.getInt("accepted");
			
			if(count == countUsersInMap(event.getUserStatuses())) {
				// ALL_ACCEPTED
				addMessage(event.getLeader().getID(), eventId, MessageType.ALL_ACCEPTED);
			}
		} catch (SQLException e) {
			System.err.println("Error while accepting event: " + e.getMessage());
			throw new Exception(e);
		}
	}
	
	private int countUsersInMap(Map<User, Status> map) {
		Iterator<Entry<User, Status>> it = map.entrySet().iterator();
		int count = 0;
		
		while(it.hasNext()) {
			++count;
		}
		
		return count;
	}
	
	/**
	 * Adds message to database
	 * 
	 * @param userId
	 * @param eventId
	 * @param type
	 * @throws SQLException
	 */
	private void addMessage(int userId, int eventId, MessageType type) throws SQLException {
		int typeInt;
		
		switch(type) {
		case USER_DECLINED:
			typeInt = 0;
			break;
		case ALL_ACCEPTED:
			typeInt = 2;
			break;
		case CHANGE:
			typeInt = 3;
			break;
		default:
			typeInt = 1;
			break;
		}
		
		String query = 	"INSERT INTO message " +
						"SET userId=?, eventId=?, type=? " +
						"ON DUPLICATE KEY UPDATE userId=?, eventId=?, type=?";
		
		PreparedStatement ps = db.preparedStatement(query);
		ps.setInt(1, userId);
		ps.setInt(4, userId);
		ps.setInt(2, eventId);
		ps.setInt(5, eventId);
		ps.setInt(3, typeInt);
		ps.setInt(6, typeInt);
		
		ps.execute();
		ps.close();
	}
	
	/**
	 * Sets the users relation to the event to declined / not participating
	 * 
	 * @param event
	 * @param user
	 * @throws Exception if the method is unsuccessful
	 */
	public void declineEvent(int eventId, User user) throws Exception {
		// Decline event
		String query = "UPDATE participant SET status='0' WHERE eventId=" + eventId + " AND userId=" + user.getID();
		
		Event event = getEvent(eventId);
		
		try {
			db.update(query);
			
			// Add message about decline
			addMessage(event.getLeader().getID(), eventId, MessageType.USER_DECLINED);
		} catch (SQLException e) {
			System.err.println("Error while declining event: " + e.getMessage());
			throw new Exception(e);
		}
	}
}
