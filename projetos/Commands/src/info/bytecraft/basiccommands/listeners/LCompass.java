package info.bytecraft.basiccommands.listeners;

import info.bytecraft.permissions.PPlayer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LCompass implements Listener{
	
	@EventHandler
	public void onCompass(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(new PPlayer(player).isDonor()){
			if(event.getPlayer().getItemInHand().getType() == Material.COMPASS){
				if(event.getAction() == Action.LEFT_CLICK_BLOCK){
					Location loc = event.getClickedBlock().getLocation();
					loc.setPitch(player.getLocation().getPitch());
					loc.setYaw(player.getLocation().getYaw());
					player.teleport(loc);
					event.setCancelled(true);
				}else if(event.getAction() == Action.LEFT_CLICK_AIR){
					Location loc = player.getTargetBlock(null, 1000).getLocation();
					loc.setPitch(player.getLocation().getPitch());
					loc.setYaw(player.getLocation().getYaw());
					player.teleport(loc.add(1, 0, 0));
					event.setCancelled(true);
				}
			}
		}
	}
}
