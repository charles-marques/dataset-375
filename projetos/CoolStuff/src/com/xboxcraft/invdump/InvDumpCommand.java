package com.xboxcraft.invdump;

import java.util.HashMap;
import java.util.ListIterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.griefcraft.lwc.LWC;

public class InvDumpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)){
			return false;
		}
		
		Player player = (Player)sender;
		Block targetBlock = player.getTargetBlock(null, 5);
		
		if (targetBlock == null || targetBlock.getType() != Material.CHEST){
			player.sendMessage(ChatColor.RED + "You must be looking at a chest!");
			return true;
		}
		
		LWC lwc = LWC.getInstance();
		if (lwc.findProtection(targetBlock) != null && !lwc.canAccessProtection(player, targetBlock)){
			player.sendMessage(ChatColor.RED + "You do not have permission to access this chest!");
			return true;
		}
		
		PlayerInventory playerInventory = player.getInventory();
		
		boolean fitsAll = true;
		Inventory chestInventory = ((Chest)targetBlock.getState()).getInventory();
		ListIterator<ItemStack> invIter = playerInventory.iterator((args.length != 0 && args[0].equalsIgnoreCase("all")) ? 0 : 9);
		
		while (invIter.hasNext()){
			ItemStack currentStack = invIter.next();
			if (currentStack != null){
				HashMap<Integer, ItemStack> failedStacks = chestInventory.addItem(currentStack);
				if (failedStacks.size() != 0){
					invIter.set(failedStacks.get(0));
					fitsAll = false;
				}
				else{
					invIter.set(null);
				}
			}
		}
		
		if (fitsAll){
			player.sendMessage(ChatColor.GREEN + "Your inventory has been moved to the chest!");
		}
		else{
			player.sendMessage(ChatColor.GREEN + "All possible items were transferred to the chest.");
		}
		
		return true;
	}

}
