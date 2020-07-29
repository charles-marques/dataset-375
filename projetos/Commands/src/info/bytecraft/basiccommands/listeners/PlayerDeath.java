package info.bytecraft.basiccommands.listeners;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PlayerDeath implements Listener{
	
	@EventHandler
	public void onDeath(EntityDeathEvent event){
		if(event.getEntity() instanceof Player){
			Player player = (Player)event.getEntity();
			Players p = BasicCommands.getPlayerInfo(player);
			p.setDeaths(p.getDeaths() + 1);
			if(player.getKiller() instanceof Player){
				Players kp = BasicCommands.getPlayerInfo((Player)player.getKiller());
				kp.setKills(kp.getKills() + 1);
				BasicCommands.plugin.getDatabase().save(kp);
			}
			BasicCommands.plugin.getDatabase().save(p);
		}
	}
}
