package info.bytecraft.basiccommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(args.length == 0){
				if(cs instanceof Player){
					Player player = (Player)cs;
					Location loc = Bukkit.getWorld("world").getSpawnLocation();
					Location newloc = new Location(Bukkit.getWorld("world"), (loc.getX() + 0.5), loc.getY(), (loc.getZ() + 0.5), -269.85F, 2.54F);
					player.teleport(newloc);
				}
			}
		}
		return true;
	}

}
