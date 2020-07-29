package com.christiandevs.entities;

import java.util.*;

import com.flume2d.*;
import com.flume2d.ai.*;
import com.flume2d.graphics.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.tiled.*;
import com.christiandevs.*;

public class Map extends Entity implements IWalkable
{
	
	private GraphicList list;
	private PathFinder pathFinder;
	private Tilemap pathMap;
	
	public List<Actor> characters;
	
	public int tileWidth, tileHeight;
	public int width, height;

	public Map()
	{
		list = new GraphicList();
		characters = new LinkedList<Actor>();
	}

	public void load(String filename)
	{
		TiledMap tmx = TiledLoader.createMap(Gdx.files.internal(filename));
		if (tmx == null) return;
		load(tmx);
	}

	public void load(TiledMap tmx)
	{
		width = tmx.width * tmx.tileWidth;
		height = tmx.height * tmx.tileHeight;
		tileWidth = tmx.tileWidth;
		tileHeight = tmx.tileHeight;
		
		pathMap = new Tilemap(null, tmx.tileWidth, tmx.tileHeight, tmx.width, tmx.height);
		pathFinder = new PathFinder(tmx.width, tmx.height, this);
		
		loadMap(tmx);
		loadObjects(tmx);
	}

	private void loadMap(TiledMap tmx)
	{
		if (list.size() > 0)
			list.clear();
		
		// get the first tileset
		TileSet set = findTileSet(tmx, 1);
		
		Iterator<TiledLayer> it = tmx.layers.iterator();
		while (it.hasNext())
		{
			TiledLayer layer = it.next();
			Tilemap map = new Tilemap("data/maps/" + set.imageName,
					tmx.tileWidth, tmx.tileHeight,
					tmx.width, tmx.height,
					set.spacing, set.margin);
			
			int tile = 0;
			for (int y = 0; y < layer.getHeight(); y++)
			{
				for (int x = 0; x < layer.getWidth(); x++)
				{
					tile = layer.tiles[y][x] - set.firstgid;
					map.setTile(x, y, tile);
					if (tile > -1)
						pathMap.setTile(x, y, tile);
				}
			}
			list.add(map);
		}
		setGraphic(list);
	}
	
	private TileSet findTileSet(TiledMap tmx, int gid)
	{
		TileSet set;
		Iterator<TileSet> it = tmx.tileSets.iterator();
		while (it.hasNext())
		{
			set = it.next();
			if (set.firstgid <= gid)
				return set;
		}
		return null;
	}
	
	private void loadObjects(TiledMap tmx)
	{
		Game game = Game.getInstance();
		Iterator<Actor> party = game.party.iterator();

		Iterator<TiledObjectGroup> groups = tmx.objectGroups.iterator();
		while (groups.hasNext())
		{
			TiledObjectGroup group = groups.next();
			
			Iterator<TiledObject> it = group.objects.iterator();
			while (it.hasNext())
			{
				TiledObject obj = it.next();
				
				if (obj.type.equals("spawn"))
				{
					Actor c = null;
					// TODO: load player from party
					if (group.name.equals("monster"))
					{
						c = new Monster(obj.x, obj.y, "wolf");
					}
					else if (party.hasNext())
					{
						c = party.next();
						c.x = obj.x;
						c.y = obj.y;
					}

					// If we actually found a character to spawn add it to the scene
					if (c != null)
					{
						characters.add(c);
						scene.add(c);
						c.setMap(this);
					}
				}
			}
		}
	}

	public List<PathNode> getPath(int x, int y, int goalx, int goaly)
	{
		pathFinder.calculateNearestPoint = true; 
		return pathFinder.findPath(
				x / tileWidth, y / tileHeight,
				goalx / tileWidth, goaly / tileHeight
			);
	}

	/**
	 * Determines if a position on the map is walkable
	 * This function is called when determining pathfinding
	 * @param x the screen value of the x-axis
	 * @param y the screen value of the y-axis
	 * @return if the area is walkable or not
	 */
	@Override
	public boolean isWalkable(int x, int y)
	{
		int tile = pathMap.getTile(x, y);
		boolean canWalk = true;
		
		// check which tiles are "solid"
		if (tile == 0)
			canWalk = false;
		
		// make the characters "solid"
		if (canWalk)
		{
			Iterator<Actor> it = characters.iterator();
			while (it.hasNext())
			{
				Actor c = it.next();
				if (x == (c.x / tileWidth) && y == (c.y / tileHeight))
				{
					canWalk = false;
					break;
				}
			}
		}
		
		return canWalk;
	}
	
}
