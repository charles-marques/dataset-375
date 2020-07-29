package info.bytecraft.basiccommands.listeners;

import info.bytecraft.basiccommands.BasicCommands;
import info.bytecraft.basiccommands.Players;
import info.bytecraft.permissions.PPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class LPlayerJoin implements Listener {
	private static BasicCommands plugin;
	public LPlayerJoin(BasicCommands instance){
		plugin = instance;
	}

	ChatColor white = ChatColor.WHITE;
	ChatColor gold = ChatColor.GOLD;
	ChatColor blue = ChatColor.BLUE;
	ChatColor yellow = ChatColor.YELLOW;
	ChatColor red = ChatColor.RED;
	ChatColor dgreen = ChatColor.DARK_GREEN;
	ChatColor green = ChatColor.GREEN;
	ChatColor purple = ChatColor.DARK_PURPLE;

	@EventHandler
	public void onJoin(PlayerLoginEvent event)
	{
		Player p = event.getPlayer();
		String name = p.getName();
		PPlayer player = new PPlayer(p);
		if(name.length() >= 16){
			name = name.substring(0, name.length());
		}

		if(p.getName().equalsIgnoreCase("Lilkiw")){
			p.setDisplayName(ChatColor.AQUA + p.getName() + ChatColor.WHITE);
			p.setPlayerListName(ChatColor.AQUA + p.getName());
		}

		if (player.isAdmin()) {
			if(player.isSeniorAdmin()){
				p.setDisplayName(gold + name + white);
				p.setPlayerListName(gold + name);
				return;
			}else if(player.isCoder()){
				p.setDisplayName(ChatColor.DARK_RED + name + this.white);
				p.setPlayerListName(ChatColor.DARK_RED + name);
			}else{
				p.setDisplayName(this.red + name + this.white);
				p.setPlayerListName(this.red + name);
				return;
			}
		}

		if (player.isGuardian()) {
			p.setDisplayName(this.blue + name + this.white);
			p.setPlayerListName(this.blue + name);
			return;
		}

		if(player.isWarned() || player.isHardWarned()){
			p.setDisplayName(ChatColor.GRAY + name + this.white);
			p.setPlayerListName(ChatColor.GRAY + name);
		}

		if (player.isBuilder()) {
			p.setDisplayName(this.purple + name + this.white);
			p.setPlayerListName(this.purple + name);
			return;
		}

		if (player.isDonor()) {
			p.setDisplayName(this.yellow + name + this.white);
			p.setPlayerListName(this.yellow + name);
			return;
		}

		if (player.isSettler()) {
			p.setDisplayName(this.green + name + this.white);
			p.setPlayerListName(this.green + name);
			return;
		}

		if(player.isMember()){
			p.setDisplayName(this.dgreen + name + this.white);
			p.setPlayerListName(this.green + name);
			return;
		}

		if (player.isChild()) {
			p.setDisplayName(ChatColor.AQUA + name + this.white);
			p.setPlayerListName(ChatColor.AQUA + name);
			return;
		}

		if(player.isTourist()){
			p.setDisplayName(ChatColor.WHITE + name + this.white);
			p.setPlayerListName(ChatColor.WHITE + name);
			return;
		}
	}

	@EventHandler(priority=EventPriority.LOWEST)
	public void onEnter(PlayerJoinEvent event){
		if(Bukkit.getPluginManager().getPlugin("Basic commands") == null)return;
		Player p = event.getPlayer();
		Players players = plugin.getServer().getPluginManager().getPlugin("Basic commands").getDatabase().find(Players.class).where().ieq("playerName", p.getName()).findUnique();
		if(players != null && players.isVanished()){
			event.setJoinMessage(null);
			return;
		}
		event.setJoinMessage(ChatColor.DARK_AQUA + "Please welcome " + event.getPlayer().getDisplayName() + ChatColor.DARK_AQUA + " to bytecraft");

	}

}
