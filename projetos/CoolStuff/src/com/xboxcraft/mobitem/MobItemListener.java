package com.xboxcraft.mobitem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.block.CraftCreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.xboxcraft.CoolStuff;

public class MobItemListener implements Listener {
	
	private CoolStuff plugin;
	public MobItemListener(CoolStuff pluginObj){
		this.plugin = pluginObj;
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		
		final Block block = e.getBlockPlaced();
		
		if (block.getType() != Material.MOB_SPAWNER)
			return;
		
		final short entityId = e.getItemInHand().getDurability();
		
		if (entityId < 50 || entityId > 120)
			return;
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				((CraftCreatureSpawner)block.getState()).setSpawnedType(EntityType.fromId(entityId));
			} 
		}, 0);
	}
}
