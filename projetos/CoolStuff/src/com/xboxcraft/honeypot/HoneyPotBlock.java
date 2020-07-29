package com.xboxcraft.honeypot;

import org.bukkit.block.Block;

public class HoneyPotBlock {
	
	private Integer typeId;
	private Byte data;
	
	public HoneyPotBlock(Block block){
		typeId = block.getTypeId();
		data = block.getData();
	}
	
	public Integer getTypeId(){
		return this.typeId;
	}
	
	public Byte getData(){
		return this.data;
	}
}
