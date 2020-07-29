package com.xboxcraft.honeypot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class HoneyPotCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		
		if (!sender.isOp() && !PermissionsEx.getUser((Player)sender).has("xboxcraft.honeypot.lookup")){
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this.");
			return true;
		}
		
		if (args.length == 0){
			return false;
		}
		
		if (args[0].equalsIgnoreCase("reload")){
			if (sender.isOp()){
				HoneyPotListener.loadConfig();
				sender.sendMessage(HoneyPotListener.messagePrefix + ChatColor.GREEN + "Configuration reloaded.");
			}
			else{
				sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
			}
			return true;
		}
		
		String playerName, playerNameLowered;
		Player player = Bukkit.getServer().getPlayer(args[0]);
		
		if (player == null){
			String argLower = args[0].toLowerCase();
			if (HoneyPotListener.playerMap.containsKey(argLower)){
				playerName = argLower;
				playerNameLowered = argLower;
			}
			else{
				sender.sendMessage(HoneyPotListener.messagePrefix + ChatColor.RED + "Player not found.");
				return true;
			}
		}
		else{
			playerName = player.getName();
			playerNameLowered = playerName.toLowerCase();
		}
		
		if (sender.isOp() && args.length == 2 && args[1].equalsIgnoreCase("reset")){
			if (HoneyPotListener.playerMap.containsKey(playerNameLowered)){
				HoneyPotListener.playerMap.remove(playerNameLowered);
			}
			sender.sendMessage(HoneyPotListener.messagePrefix + ChatColor.GREEN + playerName + "'s record has been cleared.");
		}
		else{
			Integer currentPoints = HoneyPotListener.playerMap.containsKey(playerNameLowered) ? HoneyPotListener.playerMap.get(playerNameLowered) : 0;
			sender.sendMessage(HoneyPotListener.messagePrefix + ChatColor.GREEN + playerName + " has " + currentPoints + " points on record.");
		}
		
		return true;
	}
	
}