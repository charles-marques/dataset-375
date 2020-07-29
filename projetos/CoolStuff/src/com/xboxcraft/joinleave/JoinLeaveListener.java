package com.xboxcraft.joinleave;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.earth2me.essentials.User;
import com.xboxcraft.CoolStuff;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class JoinLeaveListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent e){
		User user = CoolStuff.getEssentialsPlugin().getUser(e.getPlayer());
		if (!canBroadcastMessage(e.getPlayer())){
			e.setJoinMessage(null);
			broadcastToBypassGroups(ChatColor.YELLOW + "*" + user.getName() + " joined the game.");
		}
		else if (!PermissionsEx.getUser(e.getPlayer()).has("vanish.silentjoin")){
			e.setJoinMessage(ChatColor.YELLOW + ChatColor.stripColor(user.getNick(false)) + " joined the game.");
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerQuit(PlayerQuitEvent e){
		if (!canBroadcastMessage(e.getPlayer())){
			e.setQuitMessage(null);
			broadcastToBypassGroups(ChatColor.YELLOW + "*" + e.getPlayer().getName() + " left the game.");
		}
		else if(!PermissionsEx.getUser(e.getPlayer()).has("vanish.silentquit")){
			e.setQuitMessage(ChatColor.YELLOW + ChatColor.stripColor(e.getPlayer().getDisplayName()) + " left the game.");
		}
	}
	
	private static void broadcastToBypassGroups(String message){
		for (Player player : Bukkit.getOnlinePlayers()){
			if (PermissionsEx.getUser(player).has("xboxcraft.joinleave.bypass") || player.isOp()){
				player.sendMessage(message);
			}
		}
	}
	
	private static boolean canBroadcastMessage(Player player){
		return PermissionsEx.getUser(player).has("xboxcraft.joinleave.show") || player.isOp();
	}
}
