package db;

public class TestBase {
	DBConnection db;
	UserAPI userapi;
	RoomAPI roomapi;
	EventAPI eventapi;
	
	public TestBase() {
		db = new DBConnection();
		userapi = new UserAPI(db);
		roomapi = new RoomAPI(db);
		eventapi = new EventAPI(db);
	}
	
	public UserAPI getUserAPI() {
		return userapi;
	}
	
	public RoomAPI getRoomAPI() {
		return roomapi;
	}
	
	public EventAPI getEventAPI() {
		return eventapi;
	}
}
