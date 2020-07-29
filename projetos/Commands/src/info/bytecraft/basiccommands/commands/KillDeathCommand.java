package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillDeathCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kdr")){
			if(cs instanceof Player){
				if(args.length == 0){
				Player player = (Player)cs;
				Players p = BasicCommands.getPlayerInfo(player);
				player.sendMessage(ChatColor.DARK_GREEN + "Kills: " + ChatColor.BLUE + p.getKills());
				player.sendMessage(ChatColor.DARK_GREEN + "Deaths: " + ChatColor.BLUE + p.getDeaths());
				if(p.getDeaths() == 0){
					player.sendMessage(ChatColor.BLUE + "KDR: " + ChatColor.AQUA + "0");
				}else{
					player.sendMessage(ChatColor.BLUE + "KDR: " + ChatColor.AQUA + Math.round(p.getKills() / p.getDeaths()));
				}
				}else if(args.length == 1){
					Player player = (Player)cs;
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						Players p = BasicCommands.getPlayerInfo(target);
						player.sendMessage(ChatColor.YELLOW + "KDR for " + target.getDisplayName());
						player.sendMessage(ChatColor.DARK_GREEN + "Kills: " + ChatColor.BLUE + p.getKills());
						player.sendMessage(ChatColor.DARK_GREEN + "Deaths: " + ChatColor.BLUE + p.getDeaths());
						if(p.getDeaths() == 0){
							player.sendMessage(ChatColor.BLUE + "KDR: " + ChatColor.AQUA + "0");
						}else{
							player.sendMessage(ChatColor.BLUE + "KDR: " + ChatColor.AQUA + Math.round(p.getKills() / p.getDeaths()));
						}
					}
				}
			}
		}
		return true;
	}

}
