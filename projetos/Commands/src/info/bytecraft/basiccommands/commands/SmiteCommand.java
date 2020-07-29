package info.bytecraft.basiccommands.commands;

import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("t")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(new PPlayer(player).isAdmin()){
					if(args.length == 1){
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null){
							target.getWorld().strikeLightningEffect(target.getLocation());
						}
					}
				}
			}else{
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						target.getWorld().strikeLightningEffect(target.getLocation());
					}
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("killp")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(new PPlayer(player).isAdmin()){
					if(args.length == 1){
						Player target = Bukkit.getPlayer(args[0]);
						if(target != null){
							target.getWorld().strikeLightning(target.getLocation());
							target.setHealth(0);
						}
					}
				}
			}else{
				if(args.length == 1){
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null){
						target.getWorld().strikeLightning(target.getLocation());
						target.setHealth(0);
					}
				}
			}
		}
		return true;
	}
}
