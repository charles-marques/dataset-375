package db;

import java.util.Calendar;
import java.util.List;

import model.Room;

public class TestRoomAPI {
	public static void main(String[] args) {
		TestBase test = new TestBase();
		RoomAPI api = test.getRoomAPI();
		
		Room room = new Room(0, 24, "The Big One");
		api.addRoom(room);
		
		Calendar start = Calendar.getInstance();
		start.set(2012, 6, 23, 11, 0);
		Calendar end = Calendar.getInstance();
		end.set(2012, 6, 23, 16, 0);
		
		List<Room> rooms = api.getAvailableRooms(start, end);
		for (Room r : rooms) {
			System.out.println(r.getID() + " " + r.getName() + " " + r.getSize());
		}
		
		api.deleteRoom("The Big One");
	}
}
