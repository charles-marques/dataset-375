package com.christiandevs.rpg;

import java.util.*;
import org.json.*;
import com.christiandevs.entities.*;

public class Party extends Vector<Actor> implements JSONString
{
	
	public Party()
	{
	}
	
	public void loadJSON(JSONArray members) throws JSONException
	{
		for (int i = 0; i < members.length(); i++)
		{
			String playerFile = members.getString(i);
			add(new Player(playerFile));
		}
	}

	@Override
	public String toJSONString()
	{
		String json = "";
		Iterator<Actor> it = iterator();
		while (it.hasNext())
		{
			Actor actor = it.next();
			json += actor.name + ",";
		}
		return "[" + json + "]";
	}
	
}
