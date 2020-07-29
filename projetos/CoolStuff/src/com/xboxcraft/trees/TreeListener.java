package com.xboxcraft.trees;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class TreeListener implements Listener {
	
	public static final Material[] axes = {
			Material.WOOD_AXE,
			Material.STONE_AXE,
			Material.IRON_AXE,
			Material.GOLD_AXE,
			Material.DIAMOND_AXE
	};

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Block block = event.getBlock();
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();

		if (!block.getType().equals(Material.LOG) || !isAxe(item)){
			return;
		}

		World world = player.getWorld();
		Location blockLocation = block.getLocation();

		double x = blockLocation.getBlockX();
		double y = blockLocation.getBlockY();
		double z = blockLocation.getBlockZ();

		Location above;
		short maxDurability = item.getType().getMaxDurability();
		while ((above = new Location(world, x, ++y, z)).getBlock().getType().equals(Material.LOG)){
			above.getBlock().breakNaturally();
			short newDurability = (short)(item.getDurability() + 1);
			item.setDurability(newDurability);
			if (newDurability == maxDurability)
				break;
		}
	}

	public static boolean isAxe(ItemStack item) {
		if (item == null){
			return false;
		}
		Material itemType = item.getType();
		for (Material axe : axes) {
			if (itemType.equals(axe)) {
				return true;
			}
		}
		return false;
	}
}
