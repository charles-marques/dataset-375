package info.bytecraft.basiccommands.commands;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;
import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Vanish implements CommandExecutor, Listener {
	private static BasicCommands plugin;

	public Vanish(BasicCommands instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("poof")) {
			if (cs instanceof Player) {
				Player player = (Player) cs;
				Players players = BasicCommands.getPlayerInfo(player);
				if(new PPlayer(player).isSeniorAdmin() || new PPlayer(player).isCoder()){
					if(players.isVanished()){
						players.setVanished(false);
						player.sendMessage(ChatColor.AQUA + "You have re-appeared");
						for(Player other: Bukkit.getOnlinePlayers()){
								other.showPlayer(player);
						}
					}else{
						players.setVanished(true);
						player.sendMessage(ChatColor.AQUA + "poof!");
						for(Player other: Bukkit.getOnlinePlayers()){
							if(!new PPlayer(other).isSeniorAdmin()|| !new PPlayer(player).isCoder()){
								other.hidePlayer(player);
							}
						}
					}
					BasicCommands.plugin.getDatabase().save(players);
				}
			}
		}
		return true;
	}


	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		Players database = BasicCommands.getPlayerInfo(player);
		if (database != null && database.isVanished()) {
			for (Player other : Bukkit.getOnlinePlayers()) {
				if (!new PPlayer(other).isSeniorAdmin() || !new PPlayer(player).isCoder()) {
					other.hidePlayer(player);
					//Hides the invisible player logging in from non senior admins.
				} else {
					//so that the user doesn't this message
					if(!other.getName().equalsIgnoreCase(player.getName())){
						other.sendMessage(player.getDisplayName() + ChatColor.AQUA + " has joined vanished");
					}
				}
				event.setJoinMessage(null);
				//lets the player know they are vanished
			}
			player.sendMessage(ChatColor.AQUA + "You have joined vanished");
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void moreJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		for (Player other : Bukkit.getOnlinePlayers()) {
			Players database = plugin.getDatabase().find(Players.class).where().ieq("playerName", other.getName()).findUnique();
			if (database != null && database.isVanished()) {
				if (!new PPlayer(player).isSeniorAdmin() || !new PPlayer(player).isCoder()) {
					player.hidePlayer(other); //Hides invisible people from people logging in if they aren't senior admins
				}
			}
		}
	}

}
