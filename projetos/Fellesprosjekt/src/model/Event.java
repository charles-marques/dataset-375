package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for holding events.
 * @author knuterikrosmo
 *
 */

public class Event implements Serializable {
	//fields
	private int id;
	private String name;
	private Calendar start;
	private Calendar end;
	private String info;
	private String location;
	private User leader;
	private Room room;
	private Map<User,Status> userStatuses;
	
	//constructors
	/**
	 * Empty constructer for Events.
	 */
	public Event(){
		userStatuses = new HashMap<User, Status>();
		id = 0;
	}
	
	/**
	 * 
	 * @param id	Unique identifier for event.
	 * @param name	Name of the event.
	 * @param start	Start time for the event.
	 * @param end	End time for the event.
	 * @param info	User specified information about the event.
	 * @param location	Location of the event.
	 * @param userStatus	Map of what response invited users have given to invitation.
	 */
	public Event(int id, String name, Calendar start, Calendar end, String info, User leader, Room room, String location, Map<User,Status> userStatus){
		this.id = id;
		this.name = name;
		this.start = start;
		this.end = end;
		this.info = info;
		this.leader = leader;
		this.room = room;
		this.location = location;
		this.userStatuses = userStatus;
	}
	
	/**
	 * Method for updating user statuses. Statuses concern if the user has accepted, declined or not responded to event invitations.
	 * @param user A user that has been invited to this event.
	 * @param status The status of the invited user.
	 */
	public void setStatus(User user, Status status){
		if(userStatuses.containsKey(user))
			userStatuses.put(user, status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public Map<User, Status> getUserStatuses() {
		return userStatuses;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}
	
	public void setUserStatuses(Map<User, Status> statuses) {
		userStatuses = statuses;
	}
}
