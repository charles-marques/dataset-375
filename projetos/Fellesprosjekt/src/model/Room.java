package model;

import java.io.Serializable;

/**
 * Class Room
 * 
 * Class to hold room data
 *
 */

public class Room implements Serializable {
	private int id;
	private int size;
	private String name;
	
	/**
	 * Constructor, initializing the room
	 * 
	 * @param id			- the room's ID in the database
	 * @param size			- how many the room may hold
	 * @param name			- name of the room
	 */
	public Room(int id, int size, String name) {
		setID(id);
		setSize(size);
		setName(name);
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return getName() + ": " + getSize();
	}
}