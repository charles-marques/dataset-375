/*
 * InvalidFileNameException.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */
package program;

/**
 * A exception when the file name is invalid
 * 
 * @author Eric
 * @version 1.1
 */
public class InvalidFileNameException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new instance of InvalidFileNameException
	 */
	public InvalidFileNameException() {
		super(
				"A filename cannot contain any of the following characters: \\ / : * ? \" < > |");
	}
}
