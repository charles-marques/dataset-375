package com.xboxcraft.bed;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {
	
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent e){
		e.setCancelled(true);
	}
	
}
