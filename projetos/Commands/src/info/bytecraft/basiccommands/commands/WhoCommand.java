package info.bytecraft.basiccommands.commands;


import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;
import info.bytecraft.bytes.Account;
import info.bytecraft.bytes.Bytes;
import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoCommand implements CommandExecutor{
	private static BasicCommands plugin;
	public WhoCommand(BasicCommands instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("who")){
			if(args.length == 0){
				if(sender instanceof Player){
					Player player = (Player)sender;
				    StringBuilder players = new StringBuilder();

				    for (Player player2 : Bukkit.getOnlinePlayers())
				    {
				      if (((sender instanceof Player)) && (!((Player)sender).canSee(player2))) {
				        continue;
				      }
				      if (players.length() > 0) {
				        players.append(", ");
				      }

				      players.append(player2.getDisplayName());
				    }
				player.sendMessage(ChatColor.DARK_AQUA + "~*~*~*~* " + ChatColor.GOLD + BasicCommands.getVisiblePlayers(player) + " player(s) online" + ChatColor.DARK_AQUA + " ~*~*~*~*");
				player.sendMessage(players.toString());
			}
			}else if(args.length == 1){
				Player target = Bukkit.getPlayer(args[0]);
				if(target != null){
					Players info = plugin.getDatabase().find(Players.class).where().ieq("playerName", target.getName()).findUnique();
					Account account = Bytes.getAccount(target);
					if(sender instanceof Player){
						Player player = (Player)sender;
						if(new PPlayer(player).isAdmin()){
							player.sendMessage(ChatColor.GREEN + "Name: " + target.getDisplayName());
							player.sendMessage(ChatColor.GREEN + "IP: " + ChatColor.WHITE + target.getAddress().getAddress().getHostAddress());
							player.sendMessage(ChatColor.GREEN + "World: " + ChatColor.WHITE + target.getWorld().getName());
							player.sendMessage(ChatColor.GREEN + "Bytes: " + ChatColor.WHITE + account.getAmount());
							player.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + info.getId());
						}
					}else{
						sender.sendMessage(ChatColor.GREEN + "Name: " + target.getDisplayName());
						sender.sendMessage(ChatColor.GREEN + "IP: " + ChatColor.WHITE + target.getAddress().getAddress().getHostAddress());
						sender.sendMessage(ChatColor.GREEN + "World: " + ChatColor.WHITE + target.getWorld().getName());
						sender.sendMessage(ChatColor.GREEN + "Bytes: " + ChatColor.WHITE + account.getAmount());
						sender.sendMessage(ChatColor.GREEN + "Id: " + ChatColor.WHITE + info.getId());
					}
				}
			}
		}
		return true;
	}

}
