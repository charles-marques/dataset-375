package com.christiandevs.randomDungeonPieces;

public class Path {
	//True if path go up or down, false if path go left or right
	public boolean upAndDown;
	//Path length
	public int length;
	//Where the top right corner of path is place in tileMap
	public int abs_x, abs_y;
	//Direction of the path
	public static final int NONE = 0;
	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BOTTOM = 3;
	public static final int LEFT = 4;
	
	public Path(boolean upAndDown, int length, int abs_x, int abs_y){
		this.upAndDown = upAndDown;
		this.length = length;
		this.abs_x = abs_x;
		this.abs_y = abs_y;
	}
	public int pathDirection(){
		if(upAndDown){
			if(length<0)
				return TOP;
			else if(length>0)
				return BOTTOM;
			else
				return NONE;
		}
		else{
			if(length<0)
				return LEFT;
			else if(length>0)
				return RIGHT;
			else
				return NONE;
		}
		
	}
}
