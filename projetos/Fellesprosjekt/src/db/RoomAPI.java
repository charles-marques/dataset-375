package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Room;


public class RoomAPI {
	private DBConnection db;
	
	public RoomAPI(DBConnection db) {
		this.db = db;
	}
	
	/**
	 * Get the available meeting rooms between the specified start and end times
	 * @param start the start time
	 * @param end the end time
	 * @return a list of available rooms
	 */
	public List<Room> getAvailableRooms(Calendar start, Calendar end) {
		List<Room> rooms = new ArrayList<Room>();
		Timestamp starttime = new Timestamp(start.getTimeInMillis());
		Timestamp endtime = new Timestamp(end.getTimeInMillis());
		String query =
				"SELECT * FROM meeting_room AS room " +
				"WHERE room.id NOT IN (" +
					"SELECT meetingRoomId FROM event " +
					"WHERE (start_time <= ? AND end_time >= ?) " +
					  	  "OR (start_time <= ? AND end_time >= ?) " +
					  	  "OR (start_time >= ? AND end_time <= ?))";
		
		try {
			PreparedStatement ps = db.preparedStatement(query);
			ps.setTimestamp(1, starttime);
			ps.setTimestamp(2, starttime);
			ps.setTimestamp(3, endtime);
			ps.setTimestamp(4, endtime);
			ps.setTimestamp(5, starttime);
			ps.setTimestamp(6, endtime);
			
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int size = result.getInt("size");
				rooms.add(new Room(id, size, name));
			}
			result.close();
		} catch (SQLException e) {
			System.err.println("Error while getting available rooms: " + e.getMessage());
		}
		return rooms;
	}
	
	/**
	 * Add a meeting room to the database
	 * @param room a Room
	 */
	public void addRoom(Room room) {
		String query = "INSERT INTO meeting_room(name,size) VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = db.preparedStatement(query);
			ps.setString(1, room.getName());
			ps.setInt(2, room.getSize());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while adding room: " + e.getMessage());
		}
	}
	
	/**
	 * Delete a meeting room from the database
	 * @param name the name of the room which will be deleted
	 */
	public void deleteRoom(String name) {
		String query = "DELETE FROM meeting_room WHERE name = ?";
		PreparedStatement ps;
		try {
			ps = db.preparedStatement(query);
			ps.setString(1, name);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Error while deleting room: " + e.getMessage());
		}
	}
}
