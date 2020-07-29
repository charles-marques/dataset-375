package com.christiandevs.rpg.item;

import com.christiandevs.entities.Actor;

public abstract class Item
{
	public String name;
	public int cost;
	public boolean destroyOnUse;
	
	public Item(String name)
	{
		this(name, 0, false);
	}

	public Item(String name, boolean destroyOnUse)
	{
		this(name, 0, destroyOnUse);
	}

	public Item(String name, int cost)
	{
		this(name, cost, false);
	}

	public Item(String name, int cost, boolean destroyOnUse)
	{
		this.name = name;
		this.cost = cost;
		this.destroyOnUse = destroyOnUse;
	}
	
	public abstract void use(Actor character);

	public boolean ableToEquip()
	{
		// By default, the player can't equip an item
		return false;
	}

	public void whenEquipped()
	{
		// By default, it does nothing when equip because, by default, it can't be equip
	}

}