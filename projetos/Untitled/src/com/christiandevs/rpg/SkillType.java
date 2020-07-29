package com.christiandevs.rpg;

public enum SkillType
{
	ArmorNone     (1),
	ArmorLight    (1),
	ArmorHeavy    (1),
	WeaponBow     (5),
	WeaponBlade   (5),
	WeaponBlunt   (5),
	WeaponPole    (5),
	WeaponUnarmed (5);

	private int value;

	SkillType(int defaultValue)
	{
		value = defaultValue;
	}

	public int defaultValue()
	{
		return value;
	}
}
