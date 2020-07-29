/*
 * SoundEngine.java 1.1 2012/6/1
 * 
 * Copyright (c) 2012 Northeastern University Software Engineering College
 * Software International 1001 Group Three
 * 
 * All rights reserved.
 */
package program;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

/**
 * A class uses to play sound according to the audio file name.
 * 
 * @author Eric
 * @version 1.1
 */

public class SoundEngine {

	/**
	 * lay sound according to the audio file name
	 * 
	 * @param filename the audio file name.
	 */
	public static void playSound(String filename) {
		Player player = null;
		try {
			FileInputStream fis = new FileInputStream("sounds/" + filename
					+ ".mp3");
			BufferedInputStream bis = new BufferedInputStream(fis);
			player = new Player(bis);
		} catch (Exception e) {
			System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}
		final Player soundPlayer = player;

		// run in new thread to play in background
		new Thread() {
			public void run() {
				try {
					soundPlayer.play();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}
}
