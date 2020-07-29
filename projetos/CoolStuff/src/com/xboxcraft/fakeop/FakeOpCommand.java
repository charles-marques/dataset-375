package com.xboxcraft.fakeop;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class FakeOpCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		
		if (!sender.isOp() && !PermissionsEx.getUser((Player)sender).has("xboxcraft.fakeop")){
			sender.sendMessage(ChatColor.RED + "You do not have permission to do that.");
			return true;
		}
		
		if (args.length != 1){
			return false;
		}
		
		Player player = Bukkit.getServer().getPlayer(args[0]);
		if (player == null){
			sender.sendMessage(ChatColor.RED + "Player not found!");
			return true;
		}
		
		sender.sendMessage(Color.YELLOW + "Faked Op'd " + sender.getName() + ".");
		player.sendMessage(ChatColor.YELLOW + "You are now op!");
		
		return true;
	}
	
}
