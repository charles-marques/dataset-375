package com.christiandevs;

import java.io.*;
import org.json.*;
import com.badlogic.gdx.backends.jogl.JoglApplication;
import com.christiandevs.scenes.*;
import com.flume2d.Engine;

public class Untitled extends Engine
{
	private static final String frameTitle = "Game";
	private static int width = 640;
	private static int height = 480;
	private static boolean fullscreen = false;
	
	@Override
	public void create()
	{
		super.create();
		Engine.setScene(new Battle());
	}

	public static void loadSettings() throws JSONException
	{
		FileReader in = null;
		try
		{
			in = new FileReader("data/settings.json");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		JSONObject obj = new JSONObject(new JSONTokener(in));
		width = obj.getInt("width");
		height = obj.getInt("height");
		fullscreen = obj.getBoolean("fullscreen");
	}
	
	public static void main(String[] args)
	{
		try
		{
			loadSettings();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		new JoglApplication(new Untitled(), frameTitle, width, height, true);
	}
}