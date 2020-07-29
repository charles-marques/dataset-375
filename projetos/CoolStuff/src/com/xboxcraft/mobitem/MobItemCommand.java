package com.xboxcraft.mobitem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MobItemCommand implements CommandExecutor  {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player) || args.length == 0){
			return false;
		}
		
		Player player = (Player)sender;
		
		if (!PermissionsEx.getPermissionManager().has(player, "xboxcraft.mobitem")){
			player.sendMessage(ChatColor.RED + "You do not have permission to create mob spawners!");
			return true;
		}
		
		String entityName = args[0].toLowerCase();
		short entityId = 0;
		
		EntityType[] entityTypes = EntityType.values();
		
		for (EntityType entityType : entityTypes){
			if (entityType.getName().equalsIgnoreCase(entityName)){
				entityId = entityType.getTypeId();
				break;
			}
		}
		
		if (entityId < 50 || entityId > 120){
			player.sendMessage(ChatColor.RED + "Invalid entity name!");
			return true;
		}
		
		int amount = 1;
		if (args.length > 1){
			try{
				amount = Integer.parseInt(args[1]);
				if (amount < 1)
					amount = 1;
			}
			catch (NumberFormatException e){}
		}
		
		player.getInventory().addItem(new ItemStack(Material.MOB_SPAWNER, amount, entityId));
			
		return true; 
	}

}
