package info.bytecraft.basiccommands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn implements Listener {

	@EventHandler
	public void onSpawn(PlayerRespawnEvent event){
		event.setRespawnLocation(new Location(Bukkit.getWorld("world"), -92.5, 74, 235.5,  -269.85F, 2.54F));
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event){
		event.setDeathMessage(null);
	}
	
    @EventHandler
    public void onKick(PlayerKickEvent event){
        event.setLeaveMessage(null);
    }
}
