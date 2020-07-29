/*
 * LearnEnglishSystem.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */

package program;

import controller.LoginController;

/**
 * A class uses to start the program.
 * 
 * @author Eric
 * @version 1.1
 * @see controller.LoginController
 */
public class LearnEnglishSystem {

	/**
	 * start the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new LoginController();
	}
}
