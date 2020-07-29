package db;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Event;
import model.Room;
import model.Status;
import model.User;

public class TestEventAPI extends TestBase {

	private User user;
	
	public void addUser(UserAPI api) throws Exception {
		String username = "tester";
		String name = "Test Tester Testesen";
		String email = "test@testen.no";
		String position = "Testleder";
		
		api.addUser(new User(0, username, name, email, position), "adjfakødjfkaøsdjfkaøjsdkf");
		
		user = api.getUser(username);
	}
	
	public void removeUser(UserAPI api) throws Exception {
		api.deleteUser("tester");
		
		user = null;
	}
	
	public void testGetEvent(EventAPI api) throws Exception {
		Event event = api.getEvent(testAddEvent(api));
		
		System.out.println("ID: " + event.getId());
		System.out.println("Leader: " + event.getLeader().getName());
		System.out.println("I fetched an event successfully");
	}
	
	public void testDeleteEvent(EventAPI api) throws Exception {
		api.deleteEvent(testAddEvent(api));
		System.out.println("I deleted an event successfully");
	}
	
	public void testGetEventInPeriod(EventAPI api) throws Exception {
		List<Event> events;
		
		int id = testAddEvent(api);
		Event event = api.getEvent(id);
		
		events = api.getEventsInPeriod(user, event.getStart(), event.getEnd());
		System.out.println("I got " + events.size() + " events from a period");
	}
	
	@SuppressWarnings("deprecation")
	public int testAddEvent(EventAPI api) throws Exception {
		String name = "Skolefritidsordning";
		Calendar start = Calendar.getInstance();
		start.setTime(new Date(2012 - 1900, 8, 10, 13, 15, 00));
		Calendar end = Calendar.getInstance();
		end.setTime(new Date(2012 - 1900, 8, 10, 13, 45, 00));
		String info = "A har litt info";
		User leader = user;
		Room room = null;
		String location = "";
		Map<User, Status> userStatus = new HashMap<User, Status>();
		
		User trond = new User(90, "testmannen", "Trond Testmann", "trondtest@hotmail.com", "Brukerstøtte");
		userStatus.put(trond, Status.WAITING);
		
		Event event = new Event(0, name, start, end, info, leader, room, location, userStatus);

		int id = api.addEvent(event);
		if(id != 0) {
			Event e = api.getEvent(id);
				System.out.println("Trond is participating: " + e.getUserStatuses().size());
			System.out.println("I created an event successfully");			
		} else {
			System.out.println("I could not create an event");
		}
		
		return id;
	}
	
	public void testUpdateEvent(EventAPI api) throws Exception {
		int id = testAddEvent(api);
		
		Event event = api.getEvent(id);
		System.out.println(event.getName());
		System.out.println(event.getStart().getTimeInMillis());
		
		event.setName("allers");
		event.setLocation("Utenfor");
		api.updateEvent(event);
		
		event = api.getEvent(id);
		System.out.println(event.getName());
		System.out.println(event.getStart().getTimeInMillis());
		
		System.out.println("I updated an event successfully");
	}
	
	public void testAcceptEvent(EventAPI api) throws Exception {
		int id = testAddEvent(api);
		
		api.acceptEvent(id, user);
		System.out.println("I accepted an event!");
	}
	
	public void testDeclineEvent(EventAPI api) throws Exception {
		int id = testAddEvent(api);
		
		api.declineEvent(id, user);
		System.out.println("I declined an event!");
	}
	
	public static void main(String[] args) throws Exception {
		TestEventAPI test = new TestEventAPI();
		
		try {
			test.addUser(test.getUserAPI());
			
			// Start test
			test.testAddEvent(test.getEventAPI());
			// End 
			
			test.removeUser(test.getUserAPI());
		} catch (Exception e) {
			// the exception is handled in the API
		}
	}
}
