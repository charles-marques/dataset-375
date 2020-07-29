package com.xboxcraft.blacklist;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Boat;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.PlayerInventory;

public class BlacklistListener implements Listener {
	
	private static final Material[] blockedMaterials = new Material[] {
		Material.GOLD_INGOT,
		Material.IRON_INGOT,
		Material.EXP_BOTTLE
	};
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e){
		if (e.getRightClicked() instanceof Boat){
			Location boatLocation = e.getRightClicked().getLocation();
			boatLocation.setY(boatLocation.getY() - 1);
			Block underBlock = boatLocation.getBlock();
			if (underBlock == null || !underBlock.isLiquid()){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(PlayerChatEvent e){
		String message = e.getMessage();
		for (int x = 0; x < message.length(); x++){
			char c = message.charAt(x);
			if (c < 32 || c > 254){
				e.setCancelled(true);
				break;
			}
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e){
		if (e.getPlayer().getVehicle() != null && e.getCause() == TeleportCause.PLUGIN){
			e.getPlayer().getVehicle().eject();
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().getType() == Material.ENDER_PEARL){
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Ender Pearl teleportation is disabled!");
		}
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
		removeItems(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		removeItems(e.getPlayer());
	}
	
	@EventHandler
	public void onPlayerInventoryClose(InventoryCloseEvent e){
		removeItems(e.getPlayer());
	}
	
	private void removeItems(HumanEntity player){
		if (!isCreativeNonOp(player)){
			return;
		}
		
		PlayerInventory inv = player.getInventory();
		
		for (Material blockedMaterial : blockedMaterials){
			inv.remove(blockedMaterial);
		}
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		
		HumanEntity player = e.getPlayer();
		
		if (e.isCancelled() || !isCreativeNonOp(player)){
			return;
		}
		
		Material stackMaterial = e.getItem().getItemStack().getType();
		
		for (Material blockedMaterial : blockedMaterials){
			if (stackMaterial == blockedMaterial){
				e.setCancelled(true);
				e.getItem().remove();
				break;
			}
		}
	}
	
	private static boolean isCreativeNonOp(HumanEntity entity){
		return (entity.getGameMode() == GameMode.CREATIVE || entity.getWorld().getName() == "creative") && !entity.isOp();
	}

}