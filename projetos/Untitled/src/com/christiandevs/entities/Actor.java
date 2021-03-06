package com.christiandevs.entities;

import java.util.*;
import org.json.*;

import com.christiandevs.rpg.*;
import com.christiandevs.rpg.item.*;
import com.flume2d.Engine;
import com.flume2d.Entity;
import com.flume2d.ai.PathNode;
import com.flume2d.graphics.Spritemap;

public abstract class Actor extends Entity
{
	
	protected enum PlayState
	{
		Wait,
		StartTurn,
		Move,
		Attack
	}
	
	public String name;
	
	protected String classType;
	
	public Stat health;
	public Stat energy;
	public Stat fatigue;

	protected int evade;
	protected int strength;
	protected int stamina;
	protected int accuracy;

	protected int experience;
	protected int level;

	protected Weapon weapon;
	protected Armor armor;
	protected HashMap<SkillType, Integer> skills;

	protected Spritemap sprite;
	protected Map map;

	private int moveSpaces;
	private List<PathNode> pathNodes;
	private PathNode pathTarget;

	protected PlayState state;

	public Actor()
	{
		this(0, 0);
	}

	public Actor(int x, int y)
	{
		super(x, y);

		state = PlayState.Wait;
		skills = new HashMap<SkillType, Integer>();

		name = "";
		health = new Stat(50);
		energy = new Stat(20);
		fatigue = new Stat(20);

		evade = 1;
		accuracy = 1;

		strength = 1;
		stamina = 2;
		level = 1;
		moveSpaces = 0;
	}

	protected void loadJSON(JSONObject obj) throws JSONException
	{
		name = obj.optString("name");
		
		experience = obj.optInt("experience");
		level = obj.optInt("level");
		
		if (obj.has("health"))
			health.loadJSON(obj.getJSONObject("health"));
		if (obj.has("energy"))
			energy.loadJSON(obj.getJSONObject("energy"));
		if (obj.has("fatigue"))
			fatigue.loadJSON(obj.getJSONObject("fatigue"));

		evade    = obj.optInt("evade");
		strength = obj.optInt("strength");
		accuracy = obj.optInt("accuracy");
		stamina  = obj.optInt("stamina");
	}

	protected void saveJSON(JSONWriter out) throws JSONException
	{
		out.object();
		out.key("name").value(name);
		out.key("level").value(level);
		out.key("experience").value(experience);

		out.key("health").value(health);
		out.key("energy").value(energy);
		out.key("fatigue").value(fatigue);
		
		out.key("strength").value(strength);
		out.key("evade").value(evade);
		out.key("accuracy").value(accuracy);
		out.key("stamina").value(stamina);
		out.key("skills").object();
		
		Iterator<SkillType> it = skills.keySet().iterator();
		while (it.hasNext())
		{
			SkillType type = it.next();
			out.key(type.toString());
			out.value(skills.get(type));
		}
		out.endObject();
		out.endObject();
	}

	/**
	 * Called when a character levels up
	 */
	public void levelUp()
	{
		level += 1;
		health.boost(level * 5);
		health.refill();
	}

	/**
	 * Actor takes a hit
	 * @param value the amount of damage taken
	 */
	public void takeDamage(int value)
	{
		health.drain(value);
		if (health.isDepleted())
			kill();
	}

	/**
	 * Check if the character is dead
	 * @return Am I Dead?
	 */
	public boolean isDead()
	{
		return health.isDepleted();
	}

	/**
	 * Kills off the character
	 */
	public void kill()
	{
		scene.remove(this);
	}

	/**
	 * Starts the character turn by setting the state
	 */
	public void startTurn()
	{
		state = PlayState.StartTurn;
		// recover some fatigue
		fatigue.raise(stamina);
	}

	/**
	 * Check if the character is done with it's turn
	 * @return if the character is waiting, it is done
	 */
	public boolean isDone()
	{
		return (state == PlayState.Wait);
	}

	/**
	 * A dumb easing function that moves the camera
	 * @param start the start point
	 * @param target the end point
	 * @return the amount to ease
	 */
	private float ease(float start, float target)
	{
		if (Math.abs(target - start) < 4)
			return target;
		
		if (target < start)
			return start - 2;
		
		return start + 2;
	}

	/**
	 * Moves or snaps the camera to focus on the character
	 * @param smooth moves gradually over time
	 */
	public void focusCamera(boolean smooth)
	{
		float targetX = x - Engine.width / 2 + sprite.frameWidth / 2;
		float targetY = y - Engine.height / 2 + sprite.frameHeight / 2;
		if (smooth)
		{
			// TODO: use a tween or something instead of this hacked method
			scene.camera.x = ease(scene.camera.x, targetX);
			scene.camera.y = ease(scene.camera.y, targetY);
		}
		else
		{
			scene.camera.x = targetX;
			scene.camera.y = targetY;
		}
	}

	/**
	 * How aware is this character of another
	 * @param c The character to check against
	 * @return a value between 0 and 1 on how aware we are of the character
	 */
	protected float awareness(Actor c)
	{
		// back facing should be something like 0.25
		return 0.5f;
	}
	
	protected SkillType getArmorSkill()
	{
		if (armor == null)
			return SkillType.ArmorNone;
		
		return armor.skill;
	}
	
	protected SkillType getWeaponSkill()
	{
		if (weapon == null)
			return SkillType.WeaponUnarmed;
		
		return weapon.skill;
	}
	
	protected void increaseSkill(SkillType skill, int value)
	{
		int current = 0;
		if (skills.containsKey(skill))
			current = skills.get(skill);
		else
			current = skill.defaultValue();
		skills.put(skill, current + value);
		// TODO: gained experience should be based on skill value/level
		experience += 10;
	}
	
	/**
	 * Roll dice
	 * @param sides number of sides
	 * @param count the number of dice to roll
	 * @return the value of the die
	 */
	protected int rollDice(int sides, int count)
	{
		int value = 0;
		while (count-- > 0)
		{
			value += (int) (Math.random() * sides) + 1;
		}
		return value;
	}

	protected float attackRoll(int sides)
	{
		int power = 1;
		SkillType skill = getWeaponSkill();

		if (weapon != null)
			power = weapon.power;
		
		int rating = power + skills.get(skill);
		increaseSkill(skill, 1);
		fatigue.drain(rating);

		return rollDice(sides, strength) * rating * (fatigue.getPercent() + 1);
	}

	protected float defenseRoll(int sides)
	{
		int defense = 0;
		SkillType skill = getArmorSkill();

		if (armor != null)
			defense = armor.defense;
		
		int rating = defense + skills.get(armor.skill);
		increaseSkill(skill, 1);
		fatigue.drain(rating);

		return rollDice(sides, strength) * rating * (fatigue.getPercent() + 1);
	}
	
	/**
	 * Attacks an enemy character
	 * @param enemy the character to attack
	 * @return always true because we attempted to attack an enemy
	 */
	protected int attack(Actor enemy)
	{
		// number of die sides
		int sides = 10;
		int damage = 0;
		
		float save = rollDice(sides, accuracy) - rollDice(sides, enemy.evade) * enemy.awareness(this);

		if (save < 10)
		{
			System.out.println("miss...");
		}
		else
		{
			float attack = attackRoll(sides);
			float defense = enemy.defenseRoll(sides);
			damage = (int) Math.floor(attack - defense);
			if (damage < 0) damage = 0;
			enemy.takeDamage(damage);

			System.out.println("hit for " + damage);
		}
		return damage;
	}
	
	/**
	 * Sets the map value
	 * @param map the map object this character exists on
	 */
	public void setMap(Map map)
	{
		this.map = map;
	}
	
	/**
	 * Check if we can move to a destination
	 * @param dx the x-axis destination
	 * @param dy the y-axis destination
	 * @return if we can move to that point
	 */
	protected boolean canMoveTo(int dx, int dy)
	{
		LinkedList<PathNode> nodes = new LinkedList<PathNode>();
		getWalkableArea(nodes, (int) x / map.tileWidth, (int) y / map.tileHeight, moveSpaces);
		return pathContains(nodes, dx / map.tileWidth, dy / map.tileHeight);
	}
	
	/**
	 * Check if a path contains a point
	 * @param path the path to check
	 * @param x
	 * @param y
	 * @return if the path contains the point
	 */
	private boolean pathContains(List<PathNode> path, int x, int y)
	{
		Iterator<PathNode> it = path.iterator();
		while (it.hasNext())
		{
			PathNode node = it.next();
			if (node.x == x && node.y == y)
				return true;
		}
		return false;
	}
	
	/**
	 * Determine the walkable area of the character (recursive)
	 * @param nodes a list of nodes to populate with the walkable area
	 * @param x the starting x-axis value
	 * @param y the starting y-axis value
	 * @param spaces the number of spaces away from the starting point
	 */
	protected void getWalkableArea(List<PathNode> nodes, int x, int y, int spaces)
	{
		if (spaces <= 0) return;
		
		int[] xvals = new int[]{x - 1, x + 1, x,     x    };
		int[] yvals = new int[]{y,     y,     y - 1, y + 1};
		
		for (int i = 0; i < xvals.length; i++)
		{
			x = xvals[i]; y = yvals[i];
			if (map.isWalkable(x, y) && !pathContains(nodes, x, y))
			{
				nodes.add(new PathNode(x, y));
				getWalkableArea(nodes, x, y, spaces - 1);
			}
		}
	}
	
	/**
	 * Get a path to the destination based on current position
	 * @param dx the x-axis destination value
	 * @param dy the y-axis destination value
	 */
	protected void getPathTo(int dx, int dy, int spaces)
	{
		pathNodes = map.getPath((int) x, (int) y, dx, dy);
		pathTarget = getNextPathNode();
		moveSpaces = spaces * map.tileWidth;
	}
	
	/**
	 * Gets the next path node
	 * @return the next path node
	 */
	private PathNode getNextPathNode()
	{
		if (pathNodes != null && pathNodes.size() > 0)
			return pathNodes.remove(0);
		return null;
	}
	
	/**
	 * Follows along the given path, if there is one
	 * @return still following the path?
	 */
	protected boolean followPath()
	{
		if (pathTarget == null || moveSpaces < 0)
			return false;
		
		int destX = pathTarget.x * map.tileWidth;
		int destY = pathTarget.y * map.tileHeight;
		int moveSpeed = 2;
		if (x == destX && y == destY)
		{
			// we arrived at the target destination, get the next target
			pathTarget = getNextPathNode();
		}
		else
		{
			if (x < destX)
			{
				x += moveSpeed;
				sprite.play("right");
			}
			else if (x > destX)
			{
				x -= moveSpeed;
				sprite.play("left");
			}
			
			if (y < destY)
			{
				y += moveSpeed;
				sprite.play("down");
			}
			else if (y > destY)
			{
				y -= moveSpeed;
				sprite.play("up");
			}

			moveSpaces -= moveSpeed;
		}
		return true;
	}

}
