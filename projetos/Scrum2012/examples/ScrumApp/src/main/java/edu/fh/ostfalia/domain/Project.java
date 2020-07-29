package edu.fh.ostfalia.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
	@Id
	private String id = UUID.randomUUID().toString();
	private String name;
	private String owner;
	private String description;
	private Date timestamp = new Date();

	public Project(String name, String owner, String description) {
		this.name = name;
		this.owner = owner;
		this.description = description;
	}

	public Project() {
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public String getDescription() {
		return description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", owner=" + owner + ", description=" + description + ", timestamp=" + timestamp + "]";
	}

}
