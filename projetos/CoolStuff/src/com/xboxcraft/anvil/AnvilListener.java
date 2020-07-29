package com.xboxcraft.anvil;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.inventory.CraftShapedRecipe;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;

public class AnvilListener implements Listener {
	
	private static final Material[] fixableItems = {
		Material.WOOD_AXE,
		Material.WOOD_PICKAXE,
		Material.WOOD_SPADE,
		Material.WOOD_SWORD,
		Material.WOOD_HOE,
		
		Material.LEATHER_HELMET,
		Material.LEATHER_CHESTPLATE,
		Material.LEATHER_LEGGINGS,
		Material.LEATHER_BOOTS,
		
		Material.STONE_AXE,
		Material.STONE_PICKAXE,
		Material.STONE_SPADE,
		Material.STONE_SWORD,
		Material.STONE_HOE,
		
		Material.IRON_AXE,
		Material.IRON_PICKAXE,
		Material.IRON_SPADE,
		Material.IRON_SWORD,
		Material.IRON_HOE,
		
		Material.IRON_HELMET,
		Material.IRON_CHESTPLATE,
		Material.IRON_LEGGINGS,
		Material.IRON_BOOTS,
		
		Material.GOLD_AXE,
		Material.GOLD_PICKAXE,
		Material.GOLD_SPADE,
		Material.GOLD_SWORD,
		Material.GOLD_HOE,
		
		Material.GOLD_HELMET,
		Material.GOLD_CHESTPLATE,
		Material.GOLD_LEGGINGS,
		Material.GOLD_BOOTS,
		
		Material.DIAMOND_AXE,
		Material.DIAMOND_PICKAXE,
		Material.DIAMOND_SPADE,
		Material.DIAMOND_SWORD,
		Material.DIAMOND_HOE,
		
		Material.DIAMOND_HELMET,
		Material.DIAMOND_CHESTPLATE,
		Material.DIAMOND_LEGGINGS,
		Material.DIAMOND_BOOTS,
		
		Material.BOW,
		Material.FISHING_ROD
	};
	
	private static final Material[] repairableMaterials = {
		Material.WOOD,
		Material.LEATHER,
		Material.COBBLESTONE,
		Material.GOLD_INGOT,
		Material.IRON_INGOT,
		Material.DIAMOND,
		Material.STRING
	};
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK || e.getClickedBlock().getType() != Material.IRON_BLOCK || !e.hasItem()){
			return;
		}
		
		CraftPlayer player = (CraftPlayer)e.getPlayer();
		ItemStack item = e.getItem();
		
		if (!isFixable(item.getType())){
			return;
		}
		
		if (item.getDurability() == 0){
			player.sendMessage(ChatColor.GREEN + "This item is already at 100% health!");
			return;
		}
		
		Map<Character, ItemStack> itemMap = getCraftShapedRecipeFromMaterial(item.getType()).getIngredientMap();
		Material repairableMaterial = getRepairableMaterial(itemMap);
		
		if (repairableMaterial == null){
			// This should never happen.
			player.sendMessage(ChatColor.RED + "An internal error has occured.");
			return;
		}
		
		PlayerInventory inv = player.getInventory();
		
		if (!inv.contains(repairableMaterial)){
			player.sendMessage(ChatColor.RED + "You need the following material to repair this item: " + repairableMaterial);
			return;
		}
		
		int totalAmount = getMaterialAmountInRecipe(itemMap, repairableMaterial);
		int maxDurability = item.getType().getMaxDurability();
		int currentDurability = item.getDurability();
		
		int amountInInventory = getMaterialAmountInInventory(inv, repairableMaterial);
		int amountToTake = (int)Math.ceil((double)totalAmount * currentDurability / maxDurability);
		
		int finalDurability = 0;
		if (amountInInventory < amountToTake){
			amountToTake = amountInInventory;
			finalDurability = currentDurability - (maxDurability / totalAmount * amountToTake);
		}
		
		item.setDurability((short)finalDurability);
		inv.removeItem(new ItemStack(repairableMaterial, amountToTake));
		player.updateInventory();
		player.sendMessage(ChatColor.GREEN + "Your item has been " + (int)(100 - (100 * finalDurability / maxDurability))  + "% repaired!");
	}
	
	private static CraftShapedRecipe getCraftShapedRecipeFromMaterial(Material m){
		Recipe r;
		Iterator<Recipe> iter = Bukkit.recipeIterator();
		while (iter.hasNext()){
			r = iter.next();
			if (r.getResult().getType() == m){
				return (CraftShapedRecipe)r;
			}
		}
		return null;
	}
	
	private static boolean isFixable(Material m){
		for (Material fixableItem : fixableItems){
			if (fixableItem == m){
				return true;
			}
		}
		return false;
	}
	
	private static Material getRepairableMaterial(Map<Character, ItemStack> itemMap){
		Material currentMaterial;
		for (ItemStack itemStack : itemMap.values()) {
			if (itemStack != null){
	        	currentMaterial = itemStack.getType();
	        	for (Material repairableMaterial : repairableMaterials){
	        		if (currentMaterial == repairableMaterial){
	        			return currentMaterial;
	        		}
	        	}
			}
        }
		return null;
	}
	
	private static int getMaterialAmountInRecipe(Map<Character, ItemStack> itemMap, Material m){
		int x = 0;
		for (ItemStack itemStack : itemMap.values()) {
			if (itemStack != null && itemStack.getType() == m){
				x++;
			}
		}
		return x;
	}
	
	private static int getMaterialAmountInInventory(PlayerInventory inv, Material m){
		int x = 0;
		for (ItemStack itemStack : inv.all(m).values()){
			x += itemStack.getAmount();
		}
		return x;
	}
	
}














