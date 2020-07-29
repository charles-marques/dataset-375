package com.christiandevs;

import java.io.*;
import org.json.*;
import com.christiandevs.rpg.*;

/**
 * Global game data class
 */
public class Game
{

	public Party party;

	private Game()
	{
		party = new Party();

		try
		{
			FileReader in = new FileReader("data/save.json");
			loadJSON(new JSONObject(new JSONTokener(in)));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public void loadJSON(JSONObject obj) throws JSONException
	{
		party.loadJSON(obj.getJSONArray("party"));
	}

	public static Game getInstance()
	{
		if (instance == null)
		{
			instance = new Game();
		}
		return instance;
	}

	private static Game instance;

}