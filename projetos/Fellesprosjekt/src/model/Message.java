package model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * An immutable class for holding event messages for specific users
 * @author larskinn
 */
public class Message implements Serializable {
	private final int eventId;
	private final MessageType type;
	private final Timestamp timestamp;
	
	/**
	 * Create a new message
	 * @param event The id of the Event this message is about
	 * @param type The MessageType
	 */
	public Message(int event, MessageType type, Timestamp timestamp) {
		this.eventId = event;
		this.type = type;
		this.timestamp = timestamp;
	}
	
	public int getEventID() {
		return eventId;
	}
	
	public MessageType getType() {
		return type;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public String toString() {
		String text = "";
		switch(type) {
		case ALL_ACCEPTED:
			text = "Alle har akseptert";
			break;
		case CHANGE:
			text = "Avtale endret";
			break;
		case INVITE:
			text = "Invitasjon";
			break;
		case USER_DECLINED:
			text = "Bruker avslo";
			break;
		}
		
		return text;
	}
}
