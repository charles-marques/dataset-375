package com.xboxcraft.vote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.vexsoftware.votifier.Votifier;
import com.xboxcraft.CoolStuff;

public class VoteManager implements Listener {

	private final int goldPerVote = 2;
	private final Server server;
	
	private File playerFile;
	
	// [0] Pending Gold
	// [1] Next Vote
	private final HashMap<String, Integer[]> players;
	
	private static VoteManager instance;
	
	public VoteManager(CoolStuff sender){
		
		instance = this;
		server = Bukkit.getServer();
		
		players = new HashMap<String, Integer[]>();
		playerFile = new File(Votifier.getInstance().getDataFolder(), "players.txt");
		
		server.getScheduler().scheduleSyncRepeatingTask(sender, new Runnable() {
			@Override
			public void run() {
				Integer serverTime = getServerTime();
				for (Player onlinePlayer : server.getOnlinePlayers()){
					String nameLowered = onlinePlayer.getName().toLowerCase();
					if (!players.containsKey(nameLowered) || players.get(nameLowered)[1] <= serverTime){
						onlinePlayer.sendMessage(ChatColor.GOLD + "Vote for XboxCraft and get free gold! /vote for more info.");
					}
				}
			}
		}, 420, 18000);
		
		if (!playerFile.exists()){
			writePlayers();
			return;
		}
		
		try {
			String currentLine;
			BufferedReader io = new BufferedReader(new FileReader(playerFile));
			while ((currentLine = io.readLine()) != null){
				String[] s = currentLine.split(":");
				players.put(s[0], new Integer[] { Integer.parseInt(s[1]), Integer.parseInt(s[2]) });
			}
			io.close();
		} catch (Exception e) {
			players.clear();
			server.getLogger().severe("[Vote] Failed to read player list!");
		}
	}
	
	public static VoteManager getInstance(){
		return instance;
	}

	public void voteMade(String username) {
		String nameLowered = username.toLowerCase();
		int goldToGive = goldPerVote;
		if (players.containsKey(nameLowered)){
			goldToGive += players.get(nameLowered)[0];
		}
		setData(nameLowered, giveGold(nameLowered, goldToGive), getServerTime() + 86400);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		String nameLowered = e.getPlayer().getName().toLowerCase();
		if (players.containsKey(nameLowered) && players.get(nameLowered)[0] != 0){
			setData(nameLowered, giveGold(nameLowered, players.get(nameLowered)[0]), players.get(nameLowered)[1]);
		}
	}
	
	private int giveGold(String nameLowered, int amount){
		Player player = server.getPlayerExact(nameLowered);
		if (player == null){
			return amount;
		}
		
		HashMap<Integer, ItemStack> failed = player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, amount));
		
		int pendingGold = 0;
		for (ItemStack failedItem : failed.values()){
			pendingGold += failedItem.getAmount();
		}
		
		amount -= pendingGold;
		
		if (amount != 0){
			player.sendMessage(ChatColor.GREEN + "You have been given " + amount + " gold for voting for us!");
			String globalMessage = ChatColor.GOLD + player.getName() + " has received free gold by voting for XboxCraft! Type /vote for more info.";
			for (Player onlinePlayer : server.getOnlinePlayers()){
				if (onlinePlayer != player){
					onlinePlayer.sendMessage(globalMessage);
				}
			}
		}
		
		return pendingGold;
	}
	
	private void setData(String nameLowered, int pendingGold, int nextVote){
		players.put(nameLowered, new Integer[] { pendingGold, nextVote});
		this.writePlayers();
	}
	
	private void writePlayers(){
		if (playerFile.exists()){
			playerFile.delete();
		}
		try {
			BufferedWriter io = new BufferedWriter(new FileWriter(playerFile));
			for (Entry<String, Integer[]> entry : players.entrySet()){
				io.write(entry.getKey() + ":" + Integer.toString(entry.getValue()[0]) + ":" + Integer.toString(entry.getValue()[1]));
				io.newLine();
			}
			io.close();
		} catch (Exception e) {
			server.getLogger().severe("[Vote] Failed to write player list!");
		}
	}
	
	private static Integer getServerTime(){
		return (int)(System.currentTimeMillis() / 1000);
	}
	
}
