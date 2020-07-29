package info.bytecraft.basiccommands.commands;

import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("kick") && args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if (target != null) {
				if (cs instanceof Player) {
					Player player = (Player) cs;
					if (new PPlayer(player).isAdmin()
							|| new PPlayer(player).isGuardian()) {
						if (!target.getName().equalsIgnoreCase("sabersamus")) {
							target.kickPlayer(null);
							Bukkit.broadcastMessage(target.getDisplayName() + ChatColor.DARK_AQUA + " was kicked by " + player.getDisplayName());
						} else {
							player.kickPlayer(ChatColor.RED + "Do not kick your god!");
							Bukkit.broadcastMessage(player.getDisplayName() + ChatColor.DARK_AQUA + " was kicked");
							return true;
						}
					}
				} else {
					target.kickPlayer(ChatColor.RED + "You were kicked by " + ChatColor.DARK_RED + "GOD");
					Bukkit.broadcastMessage(target.getDisplayName() + ChatColor.DARK_AQUA + " was kicked by "+ ChatColor.RED + "GOD");
					return true;
				}
			}
			return true;
		}
		return true;
	}
}
