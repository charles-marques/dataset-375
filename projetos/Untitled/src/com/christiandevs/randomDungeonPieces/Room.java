package com.christiandevs.randomDungeonPieces;

import java.lang.Math;

public class Room {
	//Numbers saying how big a room is
	public int x, y;
	//Where the top right corner of room is place in tileMap
	public int abs_x, abs_y;
	//Constants used for telling paths what sides of the room are already occupied
	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 4;
	public static final int LEFT = 8;
	//Says what sides of the room are occupied; for example, if the left and top sides are occupied, then occupiedBy = TOP + LEFT
	public int occupiedBy;
	
	public Room(int x, int y, int abs_x, int abs_y, int occupiedBy){
		this.x = x;
		this.y = y;
		this.abs_x = abs_x;
		this.abs_y = abs_y;
		this.occupiedBy = occupiedBy;
	}
	public int availableSides(){
		int numberOfAvailableSides=0;
		int tempOccupiedBy = occupiedBy;
		for(int i=1;i<=4;i++)
			if(tempOccupiedBy % Math.pow(2,i)==0){
				numberOfAvailableSides++;
			}
			else{
				tempOccupiedBy-=Math.pow(2,i-1);
			}
		return numberOfAvailableSides;
	}
	public boolean topAvailable(){
		return (occupiedBy%2==0);
	}
	public boolean rightAvailable(){
		int tempOccupiedBy=occupiedBy-(occupiedBy%2);
		return (tempOccupiedBy%4==0);
	}
	public boolean bottomAvailable(){
		int tempOccupiedBy=occupiedBy-(occupiedBy%4);
		return (tempOccupiedBy%8==0);
	}
	public boolean leftAvailable(){
		int tempOccupiedBy=occupiedBy-(occupiedBy%8);
		return (tempOccupiedBy==0);
	}
	public void removeAvailableSide(Path path){
		switch(path.pathDirection()){
			case Path.TOP:		if(topAvailable()) occupiedBy+=TOP;
								break;
			case Path.RIGHT:	if(rightAvailable()) occupiedBy+=RIGHT;
								break;
			case Path.BOTTOM:	if(bottomAvailable()) occupiedBy+=BOTTOM;
								break;
			case Path.LEFT:		if(leftAvailable()) occupiedBy+=LEFT;
		}
	}
}
