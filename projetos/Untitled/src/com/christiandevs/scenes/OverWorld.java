package com.christiandevs.scenes;

import com.flume2d.Scene;
import com.christiandevs.entities.*;

public class OverWorld extends Scene
{
	private Map map;
	
	public OverWorld()
	{
		map = new Map();
		add(map);
		map.load("maps/world.tmx");
	}
}
