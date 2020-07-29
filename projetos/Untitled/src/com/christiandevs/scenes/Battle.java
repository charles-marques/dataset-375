package com.christiandevs.scenes;

import java.util.*;
import java.util.concurrent.*;

import com.christiandevs.entities.Map;
import com.christiandevs.entities.Actor;
import com.flume2d.Engine;
import com.flume2d.Scene;

public class Battle extends Scene
{
	
	private Map map;
	private Actor character;
	private Queue<Actor> turnQueue;

	public Battle()
	{
		map = new Map();
		add(map);
		map.load("data/maps/world.tmx");
		
		// Initialize character turn order
		Iterator<Actor> it = map.characters.iterator();
		// TODO: look into making this a DelayedQueue for turn times
		turnQueue = new LinkedBlockingQueue<Actor>();
		while (it.hasNext())
		{
			turnQueue.offer(it.next());
		}

		if (turnQueue.size() > 0)
		{
			getNextActor();
			character.focusCamera(false);
		}
	}
	
	/**
	 * Makes sure the camera is within the bounds of the map
	 */
	private void clampCameraToBounds()
	{
		if (camera.x < 0)
			camera.x = 0;
		if (camera.x > map.width - Engine.width)
			camera.x = map.width - Engine.width;
		
		if (camera.y < 0)
			camera.y = 0;
		if (camera.y > map.height - Engine.height)
			camera.y = map.height - Engine.height;
	}
	
	/**
	 * Gets the next character in turn order
	 */
	private void getNextActor()
	{
		// Remove characters from the queue until we find someone who isn't dead
		do {
			// TODO: do something if the queue is empty
			character = turnQueue.remove();
		} while (character.isDead());
		character.startTurn();
	}
	
	/**
	 * If all enemies are dead, win.
	 * If all player characters are dead, lose.
	 * This could also check for variations (defeat the leader, capture a point, etc...)
	 */
	private void checkWinCondition()
	{
		List<String> counts = new LinkedList<String>();
		
		// the turn queue has all characters that are still alive
		Iterator<Actor> it = turnQueue.iterator();
		while (it.hasNext())
		{
			Actor c = it.next();
			if (c.isDead())
				continue;
			
			// count up living characters
			counts.add(c.type);
		}

		if (counts.contains("monster") == false)
		{
			// monsters are all dead
			System.out.println("You won!");
		}
		else if (counts.contains("player") == false)
		{
			// game over, load from an earlier point
			System.out.println("You lost!");
		}
	}
	
	@Override
	public void update()
	{
		super.update();
		if (character == null)
		{
			return;
		}

		// Check if the character is done with their turn
		if (character.isDone())
		{
			// add the character back onto the queue
			turnQueue.offer(character);
			checkWinCondition();
			getNextActor();
		}
		
		// smooth adjust camera to the character playing
		character.focusCamera(true);
		clampCameraToBounds();
	}
	
}
