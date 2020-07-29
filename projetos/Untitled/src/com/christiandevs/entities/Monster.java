package com.christiandevs.entities;

import java.io.*;
import org.json.*;
import com.flume2d.Entity;
import com.flume2d.graphics.Spritemap;
import com.flume2d.masks.AABB;
import com.christiandevs.rpg.*;

public class Monster extends Actor
{

	private static int maxMoveSpaces = 3;
	
	private Actor target;
	
	public Monster(int x, int y, String name)
	{
		super(x, y);
		
		this.type = "monster";

		// load up the character file
		try {
			FileReader in = new FileReader("data/monsters/" + name + ".json");
			loadJSON(new JSONObject(new JSONTokener(in)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void loadJSON(JSONObject obj) throws JSONException
	{
		Race race = Race.load(obj.getString("race"));

		sprite = race.getSpritemap();
		sprite.play("down");
		setGraphic(sprite);

		setMask(new AABB(sprite.frameWidth, sprite.frameHeight));

		super.loadJSON(obj);
	}
	
	public void update()
	{
		switch (state)
		{
			case StartTurn:
				// we need to find a target and move towards it
				if (target == null)
				{
					// TODO: make this a bit smarter targetting
					target = (Actor) scene.findClosest("player", x, y);
					if (target == null)
					{
						// can't find a target, they are probably all dead
						state = PlayState.Wait;
					}
					else
					{
						getPathTo((int) target.x, (int) target.y, maxMoveSpaces);
						state = PlayState.Move;
					}
				}
				break;
			case Move:
				// move until we can't follow the path any longer
				if (followPath() == false)
				{
					state = PlayState.Attack;
				}
				break;
			case Attack:
				attack(target);
				target = null;
				state = PlayState.Wait;
				break;
		}
		super.update();
	}
	
}
