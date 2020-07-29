package com.christiandevs.scenes;

import java.io.*;
import java.util.Random;
import com.christiandevs.entities.Map;
import com.christiandevs.randomDungeonPieces.*;
import com.flume2d.Scene;

public class RandomDungeon extends Scene {
	Random random = new Random();
	private String file_name;
	private Map map;
	//Map height and width
	private int mapWidth=32, mapHeight=32;
	Dungeon dungeon;
	//The inputs are the minimum and maximum number of rooms to generate
	private int minNumRooms=3, maxNumRooms=9;
	
	public RandomDungeon()
	{
		//Location of file to create and load
		file_name = "data/maps/randomDungeon.tmx";
		//Random
		int numberOfRooms = random.nextInt(maxNumRooms-minNumRooms+1)+minNumRooms;
		//Create the dungeon
		dungeon = new Dungeon(mapWidth/2, mapHeight/2, mapWidth, mapHeight, numberOfRooms);
		//Converts the dungeon into tmx code
		generateTmxCode();
		map = new Map();
		add(map);
		map.load(file_name);
	}
	public void generateTmxCode(){
		//Create tmx file
		/*Are these these two lines needed?
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		File file = new File(file_name);
		*/
		FileWriter fstream;
		try {
			fstream = new FileWriter(file_name);
			BufferedWriter out = new BufferedWriter(fstream);
			String tmxCode = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
					"<map version=\"1.0\" orientation=\"orthogonal\" width=\""+mapWidth+"\" height=\""+mapHeight+"\" tilewidth=\"64\" tileheight=\"64\">\n" +
					" <tileset firstgid=\"1\" name=\"tileset\" tilewidth=\"64\" tileheight=\"64\" spacing=\"1\" margin=\"1\">\n" +
					"  <image source=\"../gfx/owtile1.png\" width=\"2048\" height=\"1024\"/>\n" +
					" </tileset>" +
					" <layer name=\"ground\" width=\""+mapWidth+"\" height=\""+mapHeight+"\">/n" +
					"  <data>";
			int tileGid=0;
			for(int y=0;y<mapHeight;y++)
				for(int x=0;x<mapWidth;x++){
					if(dungeon.tileMap[y][x])
						tileGid=33;
					else
						tileGid=0;
					tmxCode+="   <tile gid=\""+tileGid+"\"/>";
				}
			/* Add this when it's needed
			tmxCode+="  </data>\n" +
					" </layer>\n" +
					" <layer name=\"tiles\" width=\"128\" height=\"128\">" +
					"  <data>";
			for(int i=0;i<mapHeight*mapWidth;i++)
				tmxCode+="   <tile gid=\"1\"/>";
			*/
			tmxCode+="  </data>\n" +
					" </layer>\n" +
					" <objectgroup name=\"objects\" width=\""+mapWidth+"\" height=\""+mapHeight+"\">\n" +
					"  <object name=\"Spawn\" type=\"spawn\" x=\""+dungeon.playerX+"\" y=\""+dungeon.playerY+"\" width=\"64\" height=\"64\"/>\n" +
					" </objectgroup>\n" +
					" <objectgroup name=\"monster\" width=\""+mapWidth+"\" height=\""+mapHeight+"\">\n" +
					"  <object name=\"Spawn\" type=\"spawn\" x=\""+dungeon.monsterX+"\" y=\""+dungeon.monsterY+"\" width=\"64\" height=\"64\"/>\n" +
					" </objectgroup>\n" +
					"</map>";
			out.write(tmxCode);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File created successfully.");
	}
}
