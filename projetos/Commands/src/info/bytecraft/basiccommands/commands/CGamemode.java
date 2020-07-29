package info.bytecraft.basiccommands.commands;


import info.bytecraft.permissions.PPlayer;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CGamemode implements CommandExecutor{
	Player player;

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("creative") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				PPlayer pp = new PPlayer(player);
				if(pp.isAdmin() || pp.isBuilder()){
					player.setGameMode(GameMode.CREATIVE);
					return true;
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("survival") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				PPlayer pp = new PPlayer(player);
				if(pp.isAdmin() || pp.isBuilder()){
					player.setGameMode(GameMode.SURVIVAL);
					return true;
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("gamemode") && args.length == 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				PPlayer pp = new PPlayer(player);
				if(pp.isAdmin() || pp.isBuilder()){
					if(player.getGameMode() == GameMode.CREATIVE){
						player.setGameMode(GameMode.SURVIVAL);
					}else{
						player.setGameMode(GameMode.CREATIVE);
					}
					return true;
				}
			}
		}
		return true;
	}

}
