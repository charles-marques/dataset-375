package info.bytecraft.basiccommands.commands;

import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("god") && args.length > 0){
			int i = 0; int para = args.length;
			String s = "";
			while(i<para){
				s = s + " " + args[i];
				i++;
			}
			if(cs instanceof Player){
				PPlayer player = new PPlayer((Player)cs);
				if(player.isAdmin()){
					Bukkit.broadcastMessage(ChatColor.RED + "<GOD> " + s.trim());
					for(Player p: Bukkit.getOnlinePlayers()){
						PPlayer other = new PPlayer(p);
						if(other.isAdmin()){
							p.sendMessage(ChatColor.AQUA + "//Say used by: " + ((Player)cs).getDisplayName());
						}
					}
				}
			}
			
			
		}
		return true;
	}

}
