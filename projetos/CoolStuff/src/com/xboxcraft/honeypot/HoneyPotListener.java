package com.xboxcraft.honeypot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import com.earth2me.essentials.Util;
import com.xboxcraft.CoolStuff;

public class HoneyPotListener implements Listener {

	private static Essentials essPlugin;
	public static final String messagePrefix = ChatColor.YELLOW + "[HoneyPot] ";
	
	private static String jailName;
	private static Integer pointsToJail;
	private static Integer jailVisitsToBan;
	private static String timeInJail;
	
	private static Integer defaultPoints;
	public static HashMap<String, Integer> playerMap;
	private static HashMap<Integer, Integer> pointMap;
	
	private static Long blockResetTicks;
	private static HashMap<Location, HoneyPotBlock> blockQueue;
	
	private static File configFile;
	private static File honeyPotFile;
	private static CoolStuff _coolStuff;
	
	private static Integer _taskId = -1;
	
	public HoneyPotListener(CoolStuff coolStuff){
		
		_coolStuff = coolStuff;
		
		FlagPermissions.addResidenceOnlyFlag("honeypot");
		essPlugin = CoolStuff.getEssentialsPlugin();
		
		configFile = new File(coolStuff.getDataFolder(), "HoneyPot.yml");
		loadConfig();
		
		honeyPotFile = new File(coolStuff.getDataFolder(), "HoneyPotPlayers.csv");
		
		if (honeyPotFile.exists()){
			this.loadPlayerList();
		}
		else{
			playerMap = new HashMap<String, Integer>();
			savePlayerList();
		}
		
		blockQueue = new HashMap<Location, HoneyPotBlock>();
	}
	
    public static void loadConfig(){
        if (configFile.exists()){
        	YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        	pointsToJail = config.getInt("pointsToJail", 1);
        	jailVisitsToBan = config.getInt("jailVisitsToBan", 5);
        	timeInJail = config.getString("timeInJail", "10m");
        	jailName = config.getString("jailName", null);
        	defaultPoints = config.getInt("defaultPoints", 1);
        	blockResetTicks = config.getLong("blockResetTicks", 3600);
        	ConfigurationSection section = config.getConfigurationSection("pointMap");
            Set<String> itemIds = section.getKeys(false);
            pointMap = new HashMap<Integer, Integer>(itemIds.size());
            for (String itemId : itemIds){
            	pointMap.put(Integer.parseInt(itemId), section.getInt(itemId));
            }
            if (_taskId != -1){
            	_coolStuff.getServer().getScheduler().cancelTask(_taskId);
            }
    		_taskId = _coolStuff.getServer().getScheduler().scheduleAsyncRepeatingTask(_coolStuff, new Runnable(){
    			public void run() {
    				for (Entry<Location, HoneyPotBlock> entry : blockQueue.entrySet()){
    					entry.getKey().getBlock().setTypeId(entry.getValue().getTypeId());
    					entry.getKey().getBlock().setData(entry.getValue().getData());
    				}
    				blockQueue.clear();
    			}
    		}, 20, blockResetTicks);
        }
        else{
        	try {
        		pointMap = new HashMap<Integer, Integer>();
            	configFile.createNewFile();
            	YamlConfiguration config = new YamlConfiguration();
            	config.set("pointsToJail", 1);
            	config.set("jailVisitsToBan", 5);
            	config.set("timeInJail", "10m");
            	config.set("jailName", null);
                config.set("defaultPoints", 1);
                config.set("blockResetTicks", 3600);
                config.createSection("pointMap");
				config.save(configFile);
				loadConfig();
			}
        	catch (IOException e) {
        		_coolStuff.getLogger().severe("[POT] Failed to create config file.");
        	}
        }
    }
	
	private void loadPlayerList(){
		playerMap = new HashMap<String, Integer>();
		try{
			String currentLine;
			BufferedReader io = new BufferedReader(new FileReader(honeyPotFile));
			while ((currentLine = io.readLine()) != null){
				String[] s = currentLine.split(",");
				playerMap.put(s[0], Integer.parseInt(s[1]));
			}
			io.close();
		}
		catch (Exception e) {
			playerMap.clear();
			_coolStuff.getLogger().severe("[POT] Failed to read player list!");
		}
	}
	
	public static void savePlayerList(){
		
		if (honeyPotFile.exists()){
			honeyPotFile.delete();
		}
		try {
			BufferedWriter io = new BufferedWriter(new FileWriter(honeyPotFile));
			for (Entry<String, Integer> entry : playerMap.entrySet()){
				io.write(entry.getKey() + "," + Integer.toString(entry.getValue()));
				io.newLine();
			}
			io.close();
		} catch (Exception e) {
			_coolStuff.getLogger().severe("[POT] Failed to write player list!");
		}
		
	}
	
	private void messageModerators(String message){
		Player[] onlinePlayers = _coolStuff.getServer().getOnlinePlayers();
		for (Player onlinePlayer : onlinePlayers){
			if (PermissionsEx.getUser(onlinePlayer).has("xboxcraft.honeypot.notice") || onlinePlayer.isOp()){
				onlinePlayer.sendMessage(messagePrefix + ChatColor.RED + message);
			}
		}
	}
	
	private void addBlockToQueue(Player player, Block block){
		blockQueue.put(block.getLocation(), new HoneyPotBlock(block));
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(BlockBreakEvent e) throws Exception{
		if (!Residence.getPermsByLoc(e.getBlock().getLocation()).isSet("honeypot") || PermissionsEx.getUser(e.getPlayer()).has("xboxcraft.honeypot.exempt") || e.getPlayer().isOp()){
			return;
		}
		
		Integer typeId = e.getBlock().getTypeId();
		
		if (typeId == 0){
			return;
		}
		
		Player player = e.getPlayer();
		String playerName = player.getName().toLowerCase();
		
		Integer blockPoints = pointMap.containsKey(typeId) ? pointMap.get(typeId) : defaultPoints;
		
		e.setCancelled(true);
		
		if (blockPoints == 0){
			addBlockToQueue(player, e.getBlock());
			return;
		}
		
		Integer currentPoints, toNextJail;
		if (playerMap.containsKey(playerName)){
			currentPoints = playerMap.get(playerName);
			toNextJail = pointsToJail - (currentPoints % pointsToJail);
		}
		else {
			currentPoints = 0;
			toNextJail = pointsToJail;
		}
		
		if (blockPoints >= toNextJail){
			currentPoints += toNextJail;
			
			Integer timesJailed = currentPoints / pointsToJail;
			
			if (timesJailed >= jailVisitsToBan){
				messageModerators(player.getName() + " banned for griefing [" + currentPoints + " Points].");
				player.setBanned(true);
				player.kickPlayer("Griefing.");
				playerMap.remove(playerName);
			}
			else{
				messageModerators(player.getName() + " jailed for griefing [" + currentPoints + " Points].");
				User essUser = essPlugin.getUser(player);
				essPlugin.getJails().sendToJail(essUser, jailName);
				essUser.setJailed(true);
				essUser.setJail(null);
				essUser.setJail(jailName);
				essUser.setJailTimeout(Util.parseDateDiff(timeInJail, true));
				player.getInventory().clear();
				player.sendMessage(ChatColor.RED + "You have been temporarily jailed for griefing!");
			}
		}
		else{
			currentPoints += blockPoints;
			addBlockToQueue(player, e.getBlock());
			messageModerators(player.getName() + " broke a honey pot! [" + currentPoints + " Points].");
		}
		
		playerMap.put(playerName, currentPoints);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreakHigh(BlockBreakEvent e){
		if (blockQueue.containsKey(e.getBlock().getLocation())){
			e.setCancelled(false);
		}
	}
}
