package com.christiandevs.rpg.item;

import java.util.*;
import com.christiandevs.entities.Actor;

public class Inventory 
{

	public enum SortMethod
	{
		NAME,
		COST,
		TYPE
	}
	
	private Actor owner;
	public List<Item> items;

	public Inventory(Actor owner)
	{
		this.owner = owner;
		items = new Vector<Item>();
	}

	public void addItem(Item item)
	{
		items.add(item);
	}

	public void useItem(String name)
	{
		Iterator<Item> it = items.iterator();
		while (it.hasNext())
		{
			Item item = it.next();
			if (item.name.compareTo(name) == 0)
			{
				item.use(owner);
				if (item.destroyOnUse)
					it.remove();
			}
		}
	}

	public void sortBy(SortMethod method)
	{
		Collections.sort(items, new ItemSorter(method));
	}

	private class ItemSorter implements Comparator<Item>
	{

		private SortMethod method;

		public ItemSorter(SortMethod method)
		{
			this.method = method;
		}

		@Override
		public int compare(Item a, Item b)
		{
			switch (method)
			{
				case NAME:
					return b.name.compareTo(a.name);
				case COST:
					return b.cost - a.cost;
				case TYPE:
					return 0;
			}
			// don't have a method to compare so it return equal
			return 0;
		}
	}
	
}