package com.xboxcraft.showcase;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ShowcaseItem {
	
	private Material _itemMaterial;
	private Location _blockLocation;
	private Location _upperBlockLocation;
	private Location _itemLocation;
	private short _data;
	
	private Item _item;
	private boolean _chunkLoaded;
	
	public Block getBlock(){
		return _blockLocation.getBlock();
	}
	
	public Block getUpperBlock(){
		return _upperBlockLocation.getBlock();
	}
	
	public Location getBlockLocation(){
		return _blockLocation;
	}
	
	public boolean isBlockNull(){
		return _blockLocation.getBlock() == null;
	}
	
	public Integer getId(){
		return this._item.getEntityId();
	}
	
	public boolean isMissing(){
		return _item == null || _item.isDead();
	}
	
	public boolean isChunkLoaded(){
		return this._chunkLoaded;
	}
	
	public void destroy(){
		if (_item != null){
			ShowcaseListener.ItemIDs.remove((Object)_item.getEntityId());
			ShowcaseListener.BlockLocations.remove(_blockLocation);
			_item.remove();
		}
	}
	
	public void setChunkLoaded(boolean chunkLoaded){
		this._chunkLoaded = chunkLoaded;
	}

	public ShowcaseItem(Material itemMaterial, short data, Location blockLocation){
		this._itemMaterial = itemMaterial;
		this._data = data;
		
		Block itemBlock;
		try{
			itemBlock = blockLocation.getBlock();
			this.setChunkLoaded(itemBlock.getWorld().isChunkLoaded(itemBlock.getChunk()));
		}
		catch (Exception e){
			itemBlock = null;
			this.setChunkLoaded(false);
		}
		
		if (isChunkLoaded()) {
			setBlockLocation(blockLocation);
			if (ShowcaseListener.FullyLoaded){
				this.spawn();
			}
			removeDupes();
		} else {
			_blockLocation = blockLocation;
			_upperBlockLocation = blockLocation.clone();
			_upperBlockLocation.setY(_upperBlockLocation.getY() + 1);
			this.updateItemLocation();
		}
	}
	
	public void removeDupes(){
		Chunk c = _blockLocation.getChunk();
		for (Entity e : c.getEntities()) {
			Block entBlock = e.getLocation().getBlock();
			if ((entBlock.equals(getBlock()) || entBlock.equals(getUpperBlock())) && (e instanceof Item) && !e.equals(_item)){
				e.remove();
			}
		}
	}
	
	private void updateItemLocation(){
		Vector vector = _blockLocation.toVector();
		vector.add(new Vector(0.5, 0.6, 0.5));
		this._itemLocation = vector.toLocation(_blockLocation.getWorld());
	}
	
	public void setBlockLocation(Location blockLocation){
		blockLocation = blockLocation.getBlock().getLocation();
		
		this._blockLocation = blockLocation;
		this._upperBlockLocation = blockLocation.clone();
		this._upperBlockLocation.setY(_upperBlockLocation.getY() + 1);
		this.updateItemLocation();
		
		if (_item != null){
			_item.teleport(this._itemLocation);
		}
	}
	
	public void spawn(){
		if (!_chunkLoaded){
			return;
		}
		
		if (_item != null){
			ShowcaseListener.ItemIDs.remove((Object)_item.getEntityId());
			ShowcaseListener.BlockLocations.remove(_blockLocation);
			_item.remove();
		}
		
		_item = _blockLocation.getWorld().dropItemNaturally(_itemLocation, new ItemStack(_itemMaterial, 1, _data));
		_item.setVelocity(new Vector(0, 0.1, 0));
		_item.teleport(_itemLocation);
		ShowcaseListener.ItemIDs.add(_item.getEntityId());
		ShowcaseListener.BlockLocations.add(_blockLocation);
	}
	
	public void remove(){
		this.removeDupes();
		if (_item != null){
			_item.remove();
		}
	}
	
	@Override
	public String toString(){
		return Integer.toString(_itemMaterial.getId())
			+ "," + Short.toString(_data)
			+ "," + _blockLocation.getWorld().getName()
			+ "," + _blockLocation.getBlockX()
			+ "," + _blockLocation.getBlockY()
			+ "," + _blockLocation.getBlockZ();
	}
}
