package com.xboxcraft.guesttp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class GuestTPCommand implements CommandExecutor {

	private HashMap<Player, String> playerMap = new HashMap<Player, String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)){
			return false;
		}
		
		Player player = (Player)sender;
		
		if (!player.isOp() && !PermissionsEx.getUser(player).has("xboxcraft.guesttp")){
			return false;
		}
		
		PermissionManager permMan = PermissionsEx.getPermissionManager();
		
		List<Player> onlineGuests = new ArrayList<Player>();
		Player[] onlinePlayers = Bukkit.getServer().getOnlinePlayers();
		for (Player onlinePlayer : onlinePlayers){
			if (permMan.getUser(onlinePlayer).inGroup("default", false)){
				onlineGuests.add(onlinePlayer);
			}
		}
		
		if (onlineGuests.size() == 0){
			player.sendMessage(ChatColor.RED + "There are no guests online!");
			return true;
		}
		
		if (!playerMap.containsKey(player)){
			String guestName = onlineGuests.get(0).getName();
			this.playerMap.put(player, guestName);
			player.teleport(Bukkit.getPlayer(guestName));
			player.sendMessage(ChatColor.GREEN + "Teleported to " + guestName);
			return true;
		}
		
		String nextPlayer = onlineGuests.get(0).getName();
		String lastPlayer = playerMap.get(player);
		for (int x = 0; x < onlineGuests.size(); x++){
			if (onlineGuests.get(x).getName().equals(lastPlayer)){
				int nextX = x + 1;
				if (nextX != onlineGuests.size()){
					nextPlayer = onlineGuests.get(nextX).getName();
					break;
				}
			}
		}
		
		this.playerMap.put(player, nextPlayer);
		
		player.teleport(Bukkit.getServer().getPlayer(nextPlayer));
		player.sendMessage(ChatColor.GREEN + "Teleported to " + nextPlayer);
		
		return true;
	}
}