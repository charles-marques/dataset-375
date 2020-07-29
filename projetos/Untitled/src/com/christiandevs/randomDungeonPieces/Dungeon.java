package com.christiandevs.randomDungeonPieces;

import java.util.Random;

public class Dungeon {
	//Random number generator
	public Random random = new Random();
	//Location of player and monster spawner
	//TODO: find out what happens when mapWidth or mapHeight are odd
	public int playerX, playerY, monsterX, monsterY;
	//True if there is a tile, false if not
	public boolean tileMap[][];
	//Set of rooms
	public Room rooms[];
	//Set of paths
	public Path paths[];
	
	public Dungeon(int dungeonStartingPointX, int dungeonStartingPointY, int mapSizeX, int mapSizeY, int numberOfRoomsToMake){
		playerX = dungeonStartingPointX*64;
		playerY = dungeonStartingPointY*64;
		tileMap = new boolean[mapSizeY][mapSizeX];
		//Is below code necessary?
		for(int y=0;y<mapSizeY;y++)
			for(int x=0;x<mapSizeX;x++)
				tileMap[y][x]=false;
		//Is above code necessary?
		rooms = new Room[numberOfRoomsToMake+1];
		paths = new Path[numberOfRoomsToMake];
		//Create 1x1 room player starts in
		rooms[0] = new Room(1, 1, dungeonStartingPointX, dungeonStartingPointY, 0);
		tileMap[dungeonStartingPointY][dungeonStartingPointX] = true;
		for(int i=0;i<numberOfRoomsToMake;i++)
			buildPathAndRoom();
		placeMonsterSpawn(numberOfRoomsToMake);
		System.out.println("Dungeon created successfully.");
	}
	//Builds a path and a room it connects to
	public void buildPathAndRoom(){
		//Temporary path and room objects
		Room roomToBuildOn;
		Path tempPath;
		Room tempRoom;
		while(true){
			roomToBuildOn = pickRoom();
			tempPath = buildPathFromRoom(roomToBuildOn);
			tempRoom = buildRoom(tempPath);
			if(validatePathAndRoom(tempPath, tempRoom)){
				roomToBuildOn.removeAvailableSide(tempPath);
				addPath(tempPath);
				addRoom(tempRoom);
				break;
			}
		}
	}
	public Room pickRoom(){
		//Each room a different chance for being picked
		//Rooms with no available sides get no chance
		//Rooms with one available sides get one chance out of the total
		//Rooms with two available paths get two chances out of the total
		//Rooms with three or more available paths get three chances out of the total
		//These numbers can be adjusted
		int numberOfChances[] = new int[rooms.length];
		int totalNumberOfChances=0;
		for(int i=0; i<rooms.length;i++){
			if(rooms[i]!=null){
				switch(rooms[i].availableSides()){
					case 0: numberOfChances[i]=0;
							break;
					case 1: numberOfChances[i]=1;
							break;
					case 2: numberOfChances[i]=2;
							break;
					case 3: numberOfChances[i]=3;
							break;
					case 4: numberOfChances[i]=3;
							break;
					default: numberOfChances[i]=0;
				}
			}
			else{
				numberOfChances[i]=0;
			}
			totalNumberOfChances+=numberOfChances[i];
		}
		int numberChosen = random.nextInt(totalNumberOfChances);
		for(int i=0;i<rooms.length;i++){
			numberChosen-=numberOfChances[i];
			if(numberChosen<0)
				return rooms[i];
		}
		//An error has happened
		return null;
	}
	public Path buildPathFromRoom(Room room){
		int chosenSide = random.nextInt(room.availableSides());
		if(room.topAvailable()){
			chosenSide--;
			if(chosenSide<0){
				boolean upAndDown = true;
				int length = -(random.nextInt(5)+1);
				int abs_x = room.abs_x+random.nextInt(room.x);
				int abs_y = room.abs_y-1;
				return new Path(upAndDown, length, abs_x, abs_y);
			}
		}
		if(room.rightAvailable()){
			chosenSide--;
			if(chosenSide<0){
				boolean upAndDown = false;
				int length = random.nextInt(5)+1;
				int abs_x = room.abs_x+room.x;
				int abs_y = room.abs_y+random.nextInt(room.y);
				return new Path(upAndDown, length, abs_x, abs_y);
			}
		}
		if(room.bottomAvailable()){
			chosenSide--;
			if(chosenSide<0){
				boolean upAndDown = true;
				int length = random.nextInt(5)+1;
				int abs_x = room.abs_x+random.nextInt(room.x);
				int abs_y = room.abs_y+room.y;
				return new Path(upAndDown, length, abs_x, abs_y);
			}
		}
		if(room.leftAvailable()){
			chosenSide--;
			if(chosenSide<0){
				boolean upAndDown = false;
				int length = -(random.nextInt(5)+1);
				int abs_x = room.abs_x-1;
				int abs_y = room.abs_y+random.nextInt(room.y);
				return new Path(upAndDown, length, abs_x, abs_y);
			}
		}
		//An error has happened
		return null;
	}
	public Room buildRoom(Path path){
		int x = random.nextInt(5)+3;
		int y = random.nextInt(5)+3;
		int abs_x;
		int abs_y;
		int occupiedBy;
		switch(path.pathDirection()){
			case Path.TOP:		abs_x = path.abs_x - random.nextInt(x);
								abs_y = path.abs_y + path.length - (y-1);
								occupiedBy = Room.BOTTOM;
								break;
			case Path.RIGHT:	abs_x = path.abs_x + path.length;
								abs_y = path.abs_y - random.nextInt(y);
								occupiedBy = Room.LEFT;
								break;
			case Path.BOTTOM:	abs_x = path.abs_x - random.nextInt(x);
								abs_y = path.abs_y + path.length;
								occupiedBy = Room.TOP;
								break;
			case Path.LEFT:		abs_x = path.abs_x + path.length - (x-1);
								abs_y = path.abs_y - random.nextInt(y);
								occupiedBy = Room.RIGHT;
								break;
			default:			return null;
		}
		return new Room(x, y, abs_x, abs_y, occupiedBy);
	}
	public boolean validatePathAndRoom(Path path, Room room){
		//If the path and room don't overlap with any other objects, return true; else return false
		return (validatePath(path) && validateRoom(room));
	}
	public boolean validatePath(Path path){
		for(int i=0, j=0; i<path.length; i++){
			if(path.upAndDown){
				//If a spot is outside of the array, return false
				if(path.abs_x<0||path.abs_x>=tileMap[0].length||path.abs_y+j<0||path.abs_y+j>=tileMap.length){
					return false;
				}
				//If spot already used, return false
				else if(tileMap[path.abs_y+j][path.abs_x]){
					return false;
				}
			}
			else{
				//If a spot is outside of the array, return false
				if(path.abs_x+j<0||path.abs_x+j>=tileMap[0].length||path.abs_y<0||path.abs_y>=tileMap.length){
					return false;
				}
				//If spot already used, return false
				else if(tileMap[path.abs_y][path.abs_x+j]){
					return false;
				}
			}
			//Increment or decrement j, depending on the direction of the path
			if(path.length>0)
				j++;
			else
				j--;
		}
		return true;
	}
	public boolean validateRoom(Room room){
		//If a part of the room if out of range, return false
		if(room.abs_x<0||room.abs_x+room.x>=tileMap[0].length||room.abs_y<0||room.abs_y+room.y>=tileMap.length)
			return false;
		for(int y=0;y<room.y;y++)
			for(int x=0;x<room.x;x++)
				//If spot already used, return false
				if(tileMap[room.abs_y+y][room.abs_x+x])
					return false;
		return true;
	}
	public void addPath(Path path){
		//Add path to paths
		for(int i=0;i<paths.length;i++)
			if(paths[i]==null){
				paths[i]=path;
				break;
			}
		//Add path to tileMap
		for(int i=0, j=0; i<Math.abs(path.length); i++){
			if(path.upAndDown){
				tileMap[path.abs_y+j][path.abs_x]=true;
			}
			else{
				tileMap[path.abs_y][path.abs_x+j]=true;
			}
			//Increment or decrement j, depending on the direction of the path
			if(path.length>0)
				j++;
			else
				j--;
		}
	}
	public void addRoom(Room room){
		//Add room to rooms
		for(int i=0;i<rooms.length;i++)
			if(rooms[i]==null){
				rooms[i]=room;
				break;
			}
		for(int y=0;y<room.y;y++)
			for(int x=0;x<room.x;x++)
				tileMap[room.abs_y+y][room.abs_x+x]=true;
	}
	public void placeMonsterSpawn(int numberOfRoomsToMake){
		//Randomly select a room
		int roomId = random.nextInt(numberOfRoomsToMake) + 1;
		//Place on random spot in room
		monsterX=(rooms[roomId].abs_x+random.nextInt(rooms[roomId].x))*64;
		monsterY=(rooms[roomId].abs_y+random.nextInt(rooms[roomId].y))*64;
	}
}
