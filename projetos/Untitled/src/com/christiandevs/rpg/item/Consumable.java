package com.christiandevs.rpg.item;

import com.christiandevs.entities.Actor;

public class Consumable extends Item
{

	public Consumable()
	{
		super("Potion", true);
	}

	@Override
	public void use(Actor c)
	{
		c.health.raise(10);
	}
	
}
