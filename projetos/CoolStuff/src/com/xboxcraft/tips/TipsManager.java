package com.xboxcraft.tips;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.ConfigurationSection;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.xboxcraft.CoolStuff;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TipsManager implements Runnable, CommandExecutor {

	private CoolStuff coolStuff;
	private static Random random = new Random();
	
	private Integer firstMessageDelay;
	private Integer nextMessageDelay;
	private String messagePrefix;
	
	private HashMap<String, List<String>> groupMessages;
	
	private File configFile;
    
	public TipsManager(CoolStuff coolStuff){
		this.coolStuff = coolStuff;
		
		this.loadConfig();
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(coolStuff, this, firstMessageDelay, nextMessageDelay);
    }
    
    public void loadConfig(){
        configFile = new File(coolStuff.getDataFolder(), "Tips.yml");
        
        if (configFile.exists()){
        	YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        	firstMessageDelay = config.getInt("firstMessageDelay", 100);
        	nextMessageDelay = config.getInt("nextMessageDelay", 100);
        	messagePrefix = config.getString("messagePrefix", "").replace("%", "§");
        	
        	ConfigurationSection section = config.getConfigurationSection("groupMessages");
            Set<String> groupNames = section.getKeys(false);
            groupMessages = new HashMap<String, List<String>>(groupNames.size());
            if (groupNames != null){
                for (String groupName : groupNames){
                	groupMessages.put("xboxcraft.tips." + groupName.toLowerCase(), section.getStringList(groupName));
                }
            }
            for (List<String> messages : groupMessages.values()){
            	for (int x = 0; x < messages.size(); x++){
            		messages.set(x, messages.get(x).replace("%", "§"));
            	}
            }
        }
        else{
        	try {
        		groupMessages = new HashMap<String, List<String>>();
            	configFile.createNewFile();
            	YamlConfiguration config = new YamlConfiguration();
                config.set("messagePrefix", "");
                config.set("firstMessageDelay", 100);
                config.set("nextMessageDelay", 100);
                config.createSection("groupMessages");
				config.save(configFile);
			}
        	catch (IOException e) {
        		coolStuff.getLogger().severe("[TIPS] Failed to create config file.");
			}
        }
    }

    public void run(){
    	Set<String> groupNames = groupMessages.keySet();
    	List<String> allowedGroups = new ArrayList<String>(groupMessages.size());
    	
    	Player[] onlinePlayers = coolStuff.getServer().getOnlinePlayers();
    	
        for (Player player : onlinePlayers){
        	allowedGroups.clear();
            for (String groupName : groupNames){
                if (PermissionsEx.getUser(player).has(groupName)){
                	allowedGroups.add(groupName);
                }
            }
            Integer allowedSize = allowedGroups.size();
            if (allowedSize > 0){
            	List<String> messages = groupMessages.get(allowedGroups.get(random.nextInt(allowedSize)));
                if (messages != null && messages.size() != 0){
                	player.sendMessage(messagePrefix + messages.get(random.nextInt(messages.size())));
                }
            }
        }
    }

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if (!sender.isOp()){
			sender.sendMessage(ChatColor.RED + "You do not have permission to do this.");
			return true;
		}
		
		if (args.length == 0){
			return true;
		}
		
		String arg1 = args[0].toLowerCase();
		switch (arg1){
			case "reload":
				this.loadConfig();
				sender.sendMessage("Tips configuration reloaded.");
				break;
		}
		
		return true;
	}
}




