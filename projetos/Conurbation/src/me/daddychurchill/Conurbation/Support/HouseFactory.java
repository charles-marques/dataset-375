package me.daddychurchill.Conurbation.Support;

import java.util.Random;

import me.daddychurchill.Conurbation.Generator;

import org.bukkit.Material;

public class HouseFactory {

	protected static byte byteAir = (byte) Material.AIR.getId();
	protected static byte byteGlass = (byte) Material.THIN_GLASS.getId();
	
	// the description of a single room
	private static class Room {
		public static final int MinSize = 5;
		public static final int MaxSize = 7;
		public static final int MissingRoomOdds = 12; // 1/n of the time a room is missing
		
		public boolean Missing;
		public int SizeX;
		public int SizeZ;
		
		public Room(Random random) {
			super();
			
			Missing = random.nextInt(MissingRoomOdds) == 0;
			SizeX = random.nextInt(MaxSize - MinSize) + MinSize;
			SizeZ = random.nextInt(MaxSize - MinSize) + MinSize;
		}
	}
	
	public static void generateColonial(ByteChunk chunk, Random random, int chunkX, int chunkZ, int baseY, 
			byte matFloor, byte matWall, byte matRoof) {
		generateColonial(chunk, random, chunkX, chunkZ, baseY, matFloor, matWall, matRoof, 1);
	}

	public static void generateColonial(ByteChunk chunk, Random random, int chunkX, int chunkZ, int baseY, byte matFloor, byte matWall, byte matRoof, int floors) {

		// what are the rooms like?
		boolean missingRoom = false;
		Room[][][] rooms = new Room[floors][2][2];
		for (int f = 0; f < floors; f++) {
			missingRoom = false;
			for (int x = 0; x < 2; x++) {
				for (int z = 0; z < 2; z++) {
					rooms[f][x][z] = new Room(random);
					
					// single floor is a little different
					if (floors == 1) {
						if (rooms[f][x][z].Missing) {
							if (!missingRoom)
								missingRoom = true;
							else
								rooms[f][x][z].Missing = false;
						}
					} else {
						
						// first floor must be complete
						if (f == 0)
							rooms[f][x][z].Missing = false;
						
						// each additional floors must include any missing rooms from below
						else if (rooms[f - 1][x][z].Missing)
							rooms[f][x][z].Missing = true;
						
						// only one new missing room per floor
						else if (rooms[f][x][z].Missing) {
							if (!missingRoom)
								missingRoom = true;
							else
								rooms[f][x][z].Missing = false;
						}
						
						// all rooms must be the same size (or smaller) than the one below it
						if (f > 0) {
							rooms[f][x][z].SizeX = Math.min(rooms[f][x][z].SizeX, rooms[f - 1][x][z].SizeX);
							rooms[f][x][z].SizeZ = Math.min(rooms[f][x][z].SizeZ, rooms[f - 1][x][z].SizeZ);
						}
					}
				}
			}
		}
		
		// where is the center of the house?
		int roomOffsetX = chunk.width / 2 + random.nextInt(2) - 1;
		int roomOffsetZ = chunk.width / 2 + random.nextInt(2) - 1;
		
		// draw the individual rooms
		for (int f = 0; f < floors; f++) {
			for (int x = 0; x < 2; x++) {
				for (int z = 0; z < 2; z++) {
					drawRoom(chunk, rooms, f, f == floors - 1, x, z, roomOffsetX, roomOffsetZ, baseY, matFloor, matWall, matRoof);
				}
			}
		}
		
		// extrude roof
		int roofY = baseY + floors * Generator.floorHeight - 1;
		for (int y = 0; y < Generator.floorHeight - 1; y++) {
			for (int x = 1; x < chunk.width - 1; x++) {
				for (int z = 1; z < chunk.width - 1; z++) {
					int yAt = y + roofY;
					if (chunk.getBlock(x - 1, yAt, z) != byteAir && chunk.getBlock(x + 1, yAt, z) != byteAir &&
						chunk.getBlock(x, yAt, z - 1) != byteAir && chunk.getBlock(x, yAt, z + 1) != byteAir) {
						chunk.setBlock(x, yAt + 1, z, matRoof);
					}
				}
			}
		}
		
		// carve out the attic
		for (int y = 1; y < Generator.floorHeight - 1; y++) {
			for (int x = 1; x < chunk.width - 1; x++) {
				for (int z = 1; z < chunk.width - 1; z++) {
					int yAt = y + roofY;
					if (chunk.getBlock(x, yAt + 1, z) != byteAir) {
						chunk.setBlock(x, yAt, z, byteAir);
					}
				}
			}
		}
	}
	
	protected static void drawRoom(ByteChunk chunk, Room[][][] rooms, int floor, boolean topFloor, int x, int z, 
			int roomOffsetX, int roomOffsetZ, int baseY, byte matFloor, byte matWall, byte matRoof) {
		//TODO I think this function suffers from the North = West problem
		// which room?
		Room room = rooms[floor][x][z];
		boolean northRoom = x != 0;
		boolean eastRoom = z != 0;
		
		// is there really something here?
		if (!room.Missing) {
			int x1 = roomOffsetX - (northRoom ? 0 : room.SizeX);
			int x2 = roomOffsetX + (northRoom ? room.SizeX : 0);
			int z1 = roomOffsetZ - (eastRoom ? 0 : room.SizeZ);
			int z2 = roomOffsetZ + (eastRoom ? room.SizeZ : 0);
			int y1 = baseY + floor * Generator.floorHeight;
			int y2 = y1 + Generator.floorHeight - 1;

			// draw the walls
			chunk.setBlocks(x1, x1 + 1, y1, y2, z1, z2 + 1, matWall);
			chunk.setBlocks(x2, x2 + 1, y1, y2, z1, z2 + 1, matWall);
			chunk.setBlocks(x1, x2 + 1, y1, y2, z1, z1 + 1, matWall);
			chunk.setBlocks(x1, x2 + 1, y1, y2, z2, z2 + 1, matWall);
			
			// add rug and roof
			chunk.setBlocks(x1, x2 + 1, y1 - 1, y1, z1, z2 + 1, matFloor);
			if (!topFloor) {
				chunk.setBlocks(x1, x2 + 1, y2, y2 + 1, z1, z2 + 1, matRoof);
				chunk.setBlocks(x1 + 1, x2 + 1 - 1, y2, y2 + 1, z1 + 1, z2 + 1 - 1, matRoof);
			} else
				chunk.setBlocks(x1 - 1, x2 + 2, y2, y2 + 1, z1 - 1, z2 + 2, matRoof);
			
			if (northRoom) {
				if (eastRoom) {
					chunk.setBlocks(x1 + 2, y1, 	y2 - 1, z1, 	byteAir); // west
					chunk.setBlocks(x1, 	y1, 	y2 - 1, z1 + 2, byteAir); // south
					chunk.setBlocks(x1 + 2, x2 - 1, y1 + 1, y2 - 1, z2, 	z2 + 1, byteGlass); // east
					chunk.setBlocks(x2, 	x2 + 1, y1 + 1, y2 - 1, z1 + 2, z2 - 1, byteGlass); // north
				} else {
					chunk.setBlocks(x1 + 2, y1, 	y2 - 1, z2, 	byteAir); // east
					chunk.setBlocks(x1, 	y1, 	y2 - 1, z2 - 2, byteAir); // south
					chunk.setBlocks(x1 + 2, x2 - 1, y1 + 1, y2 - 1, z1, 	z1 + 1, byteGlass); // west
					chunk.setBlocks(x2, 	x2 + 1,	y1 + 1, y2 - 1, z1 + 2, z2 - 1, byteGlass); // north
				}
			} else {
				if (eastRoom) {
					chunk.setBlocks(x2 - 2, y1, 	y2 - 1, z1, 	byteAir); // west
					chunk.setBlocks(x2, 	y1, 	y2 - 1, z1 + 2, byteAir); // north
					chunk.setBlocks(x1 + 2, x2 - 1, y1 + 1, y2 - 1, z2, 	z2 + 1, byteGlass); // east
					chunk.setBlocks(x1, 	x1 + 1, y1 + 1, y2 - 1, z1 + 2, z2 - 1, byteGlass); // south
				} else {
					chunk.setBlocks(x2 - 2, y1, 	y2 - 1, z2, 	byteAir); // east
					chunk.setBlocks(x2, 	y1, 	y2 - 1, z2 - 2, byteAir); // north
					chunk.setBlocks(x1 + 2, x2 - 1, y1 + 1, y2 - 1, z1, 	z1 + 1, byteGlass); // west
					chunk.setBlocks(x1, 	x1 + 1, y1 + 1, y2 - 1, z1 + 2, z2 - 1, byteGlass); // south
				}
			}
		}
	}
	
}
