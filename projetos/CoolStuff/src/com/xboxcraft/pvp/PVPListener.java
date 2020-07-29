package com.xboxcraft.pvp;

import net.sacredlabyrinth.phaed.simpleclans.Clan;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.managers.ClanManager;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PVPListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamage(EntityDamageEvent e){
		
		if (e.isCancelled() || e.getEntityType() != EntityType.PLAYER || !(e instanceof EntityDamageByEntityEvent)){
			return;
		}
		
		EntityDamageByEntityEvent event = (EntityDamageByEntityEvent)e;
		
		if (event.getDamager().getType() != EntityType.PLAYER){
			return;
		}
		
		ClanManager clans = SimpleClans.getInstance().getClanManager();
		
		Player damager = (Player)event.getDamager();
		Clan damagerClan = clans.getClanByPlayerName(damager.getName());
		
		if (damagerClan == null){
			e.setCancelled(true);
			damager.sendMessage(ChatColor.RED + "You must be in a clan to enable PVP!");
			return;
		}
		
		Clan entityClan = clans.getClanByPlayerName(((Player)e.getEntity()).getName());
		
		if (entityClan == null){
			e.setCancelled(true);
			damager.sendMessage(ChatColor.RED + "This person is not in a clan!");
		}
		else if (entityClan.isAlly(damagerClan.getTag())){
			e.setCancelled(true);
			damager.sendMessage(ChatColor.RED + "This person is an ally!");
		}		
	}
}
