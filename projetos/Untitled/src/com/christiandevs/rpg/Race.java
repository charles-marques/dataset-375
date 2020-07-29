package com.christiandevs.rpg;

import java.util.*;
import java.io.*;
import org.json.*;
import com.flume2d.graphics.*;

public class Race
{

	public String name;
	public String description;

	/**
	 * Creates a new spritemap
	 * @return the generated spritemap with animations preloaded
	 */
	public Spritemap getSpritemap()
	{
		Spritemap sprite = new Spritemap(spriteFile, spriteWidth, spriteHeight);

		Iterator<Animation> it = animations.iterator();
		while (it.hasNext())
		{
			Animation anim = it.next();
			sprite.add(anim.clone());
		}

		return sprite;
	}

	/**
	 * Loads the Race object from json
	 * @param obj the JSONObject to load from
	 */
	private void loadJSON(JSONObject obj) throws JSONException
	{
		name = obj.getString("name");
		description = obj.getString("desc");

		JSONObject sprite = obj.getJSONObject("sprite");
		spriteFile = sprite.getString("file");
		spriteWidth = sprite.getInt("width");
		spriteHeight = sprite.getInt("height");

		// load animations
		animations = new LinkedList<Animation>();
		JSONObject anims = sprite.getJSONObject("animations");
		String[] names = JSONObject.getNames(anims);
		for (int i = 0; i < names.length; i++)
		{
			JSONObject a = anims.getJSONObject(names[i]);
			JSONArray frames = a.getJSONArray("frames");
			Animation anim = new Animation();
			anim.name = names[i];
			anim.loop = a.optBoolean("loop", true);
			anim.delay = (float) a.optDouble("delay", 0.3);
			anim.frames = new int[frames.length()];
			for (int j = 0; j < frames.length(); j++)
			{
				anim.frames[j] = frames.getInt(j);
			}
			animations.add(anim);
		}
	}
	
	/**
	 * Loads up a race file and stores in a hashmap for quick retrieval
	 * @param raceName the name of the race file to load (data/races/[raceName].json)
	 * @return a Race object
	 */
	public static Race load(String raceName) throws JSONException
	{
		// have we already loaded this race?
		if (races.containsKey(raceName))
		{
			return races.get(raceName);
		}

		FileReader in = null;
		try
		{
			in = new FileReader("data/races/" + raceName + ".json");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}

		Race race = new Race();
		race.loadJSON(new JSONObject(new JSONTokener(in)));

		races.put(raceName, race);
		return race;
	}

	private String spriteFile;
	private int spriteWidth, spriteHeight;
	private List<Animation> animations;

	private static HashMap<String, Race> races = new HashMap<String, Race>();

}