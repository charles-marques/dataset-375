package com.xboxcraft.hats;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.xboxcraft.CoolStuff;

public class HatCommand implements CommandExecutor {
	
	private HashMap<String, List<Integer>> allowedBlocks;
	
	public HatCommand(CoolStuff coolStuff){
		
		File configFile = new File(coolStuff.getDataFolder(), "Hats.yml");
		
        if (configFile.exists()){
        	YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        	ConfigurationSection section = config.getConfigurationSection("allowedBlocks");
            Set<String> groupNames = section.getKeys(false);
            allowedBlocks = new HashMap<String, List<Integer>>(groupNames.size());
            if (groupNames != null){
                for (String groupName : groupNames){
                	allowedBlocks.put("xboxcraft.hats." + groupName.toLowerCase(), section.getIntegerList(groupName));
                }
            }
        }
        else{
        	try {
        		allowedBlocks = new HashMap<String, List<Integer>>();
            	configFile.createNewFile();
            	YamlConfiguration config = new YamlConfiguration();
                config.createSection("allowedBlocks");
				config.save(configFile);
			}
        	catch (IOException e) {
        		coolStuff.getLogger().severe("[HATS] Failed to create config file.");
			}
        }
	}
	
	private boolean isAllowed(Player player, Material material){
		PermissionUser permUser = PermissionsEx.getUser(player);
		if (permUser.has("xboxcraft.hats.*")){
			return true;
		}
		Integer itemId = material.getId();
		for (Entry<String, List<Integer>> groupEntry : allowedBlocks.entrySet()){
			if (permUser.has(groupEntry.getKey()) && groupEntry.getValue().contains(itemId)){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
            return false;
        }
		
		Player player = (Player)sender;
		if (!player.isOp() && !PermissionsEx.getUser(player).has("xboxcraft.hats")){
			player.sendMessage(ChatColor.RED + "You do not have permission to use this.");
			return true;
		}
		
		ItemStack itemStack = player.getItemInHand();
		
		if (itemStack == null || itemStack.getType() == Material.AIR){
			player.sendMessage(ChatColor.YELLOW + "You must hold something to put on your head!");
		}
		else if (player.isOp() || isAllowed(player, itemStack.getType())){
			PlayerInventory playerInv = player.getInventory();
            ItemStack itemHead = playerInv.getHelmet();
            playerInv.removeItem(itemStack);
            playerInv.setHelmet(itemStack);
            playerInv.setItemInHand(itemHead);
			player.sendMessage(ChatColor.YELLOW + "Item successfuly put on your head.");
		}
		else{
			player.sendMessage(ChatColor.RED + "You do not have permission to wear this item.");
		}
		
		return true;
	}
}
