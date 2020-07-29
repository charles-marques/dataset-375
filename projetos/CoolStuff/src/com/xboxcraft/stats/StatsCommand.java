package com.xboxcraft.stats;

import java.io.File;
import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StatsCommand implements CommandExecutor {
	
	private void sendMessage(CommandSender sender, String message){
		sender.sendMessage(ChatColor.DARK_PURPLE + message);
	}
	
	private static final double bytesToGb(double num){
        return Double.valueOf(new DecimalFormat("#.00").format(num / 1024 / 1024/ 1024));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		
		if (!sender.isOp()){
			sender.sendMessage(ChatColor.RED + "You do not have permission to access this command.");
			return true;
		}
		
		Runtime run = Runtime.getRuntime();
		
		sendMessage(sender, "Processing Cores: " + run.availableProcessors());

		long maxMemory = run.maxMemory();
		sendMessage(sender, "Max Memory: " + (maxMemory == Long.MAX_VALUE ? "No limit" : (int)(maxMemory / 1024 / 1024) + " MB"));
		sendMessage(sender, "Allocated Memory: " + (int)(Runtime.getRuntime().totalMemory() / 1024 / 1024) + " MB");

	    File[] roots = File.listRoots();
	    if (roots.length == 0){
	    	return true;
	    }
	    
	    File root = roots[0];
	    sendMessage(sender, "Total Space: " + bytesToGb(root.getTotalSpace()) + " GB");
	    sendMessage(sender, "Free Space: " + bytesToGb(root.getUsableSpace()) + " GB");
	    
		return true;
	}
	
}
