package com.xboxcraft.xboxmb;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.xboxcraft.CoolStuff;

public class XMBCommand implements CommandExecutor {
	
	private static final String messagePrefix = ChatColor.YELLOW + "[XMB] ";
	private static final String apiUrl = "http://api.xboxmb.com/?yaml=1&mc=";
	private static final String memberUrl = "http://www.xboxmb.com/members/";
	
	private CoolStuff _coolStuff;
	
	public XMBCommand(CoolStuff coolStuff){
		_coolStuff = coolStuff;
	}
	
	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, String[] args){
		
		if (args.length != 1){
			return false;
		}
		
		final String playerName;
		Player player = _coolStuff.getServer().getPlayer(args[0]);
		if (player == null){
			playerName = args[0];
		}
		else{
			playerName = player.getName();
		}
		
		sender.sendMessage(messagePrefix + ChatColor.WHITE + "Starting lookup...");
		
		Bukkit.getScheduler().scheduleAsyncDelayedTask(_coolStuff, new Runnable(){

			public void run() {
				try{
					HttpURLConnection urlConn = (HttpURLConnection)new URL(apiUrl + playerName).openConnection();
					urlConn.setReadTimeout(7000);
					InputStream io = urlConn.getInputStream();
					YamlConfiguration userData = YamlConfiguration.loadConfiguration(io);
					
					if (userData.getBoolean("Valid")){
						String username = userData.getString("Username");
						sender.sendMessage(messagePrefix + ChatColor.WHITE + "Username: " + username);
						sender.sendMessage(messagePrefix + ChatColor.WHITE + memberUrl + username.replace(" ", "-"));
						
					}
					else{
						sender.sendMessage(messagePrefix + ChatColor.RED + "Player not found on XboxMB.");
					}
					
					io.close();
				}
				catch (Exception e){
					sender.sendMessage(messagePrefix + ChatColor.RED + "Failed to connect to XboxMB.");
				}
			}
			
		});
		
		return true;
	}
	
}
