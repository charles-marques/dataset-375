package com.christiandevs.rpg.item;

import com.christiandevs.entities.Actor;
import com.christiandevs.rpg.SkillType;

public class Armor extends Item
{
	
	public SkillType skill;
	public int defense;
	
	public Armor()
	{
		super("Pauper Clothing");
		skill = SkillType.ArmorLight;
		defense = 1;
	}

	@Override
	public void use(Actor c)
	{
		
	}
	
}
