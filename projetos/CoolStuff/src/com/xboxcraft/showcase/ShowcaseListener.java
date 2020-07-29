package com.xboxcraft.showcase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.xboxcraft.CoolStuff;

public class ShowcaseListener implements Listener {
	
	private static CoolStuff _coolStuff;
	private static List<ShowcaseItem> items;
	public static List<Integer> ItemIDs = new ArrayList<Integer>();
	public static List<Location> BlockLocations = new ArrayList<Location>();
	public static boolean FullyLoaded = false;
	
	private File itemFile;
	
	public ShowcaseListener(CoolStuff coolStuff){
		_coolStuff = coolStuff;
		
		itemFile = new File(coolStuff.getDataFolder(), "Showcase.csv");
		
		if (itemFile.exists()){
			this.loadFromFile();
		}
		else{
			items = new ArrayList<ShowcaseItem>();
			this.saveToFile();
		}
		
		_coolStuff.getServer().getScheduler().scheduleSyncDelayedTask(_coolStuff, new Runnable(){
			public void run() {
				for (ShowcaseItem item : items){
					if (item.isMissing() && item.isChunkLoaded()){
						item.spawn();
					}
				}
				FullyLoaded = true;
			}
		});
	}
	
	public static void removeAllEntities(){
		for (ShowcaseItem item : items){
			item.remove();
		}
	}
	
	private ShowcaseItem getShowcaseItemByBlockLocation(Location blockLocation){
		for (ShowcaseItem item : items){
			if (item.getBlockLocation().equals(blockLocation)){
				return item;
			}
		}
		return null;
	}
	
	private void respawnItemsOnChunk(Chunk chunk){
		for (ShowcaseItem item : items){
			if (item.isChunkLoaded() && item.getBlock().getChunk().equals(chunk)){
				item.spawn();
			}
		}
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e){
		respawnItemsOnChunk(e.getPlayer().getLocation().getChunk());
	}
	
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent e){
		int entId = e.getEntity().getEntityId();
		for (ShowcaseItem item : items){
			if (item.getId() == entId){
				item.spawn();
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		if (!player.isOp() || !player.isSneaking() || !e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || !e.hasItem()){
			return;
		}
		
		e.setCancelled(true);
		
		Location clickedBlockLocation = e.getClickedBlock().getLocation();
		if (BlockLocations.contains(clickedBlockLocation)){
			e.getPlayer().sendMessage(ChatColor.RED + "There is already an item on this block!");
			return;
		}
		
		Material itemMaterial = e.getItem().getType();
		short data = e.getItem().getDurability();
		
		items.add(new ShowcaseItem(itemMaterial, data, clickedBlockLocation));
		this.saveToFile();
		
		e.getPlayer().sendMessage(ChatColor.GREEN + "Showcase item registered!");
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		if (!BlockLocations.contains(e.getBlock().getLocation())){
			return;
		}
		
		e.setCancelled(true);
		
		if (!e.getPlayer().isOp()){
			e.getPlayer().sendMessage(ChatColor.RED + "This block is locked!");
			return;
		}
		
		ShowcaseItem showcaseItem = getShowcaseItemByBlockLocation(e.getBlock().getLocation());
		
		showcaseItem.destroy();
		items.remove(showcaseItem);
		this.saveToFile();
		
		e.getPlayer().sendMessage(ChatColor.GREEN + "Showcase item unregistered!");
	}
	
	@EventHandler
	public void onChunkLoad(ChunkLoadEvent e){
		for (ShowcaseItem item : items) {
			if (!item.isBlockNull() && e.getChunk().equals(item.getBlock().getChunk())) {
				item.setChunkLoaded(true);
				item.spawn();
			}
		}
	}
	
	@EventHandler
	public void onChunkUnload(ChunkUnloadEvent e){
		for (ShowcaseItem item : items){
			if (e.getChunk().equals(item.getBlock().getChunk())){
				ItemIDs.remove(item.getId());
				BlockLocations.remove(item.getBlockLocation());
				item.setChunkLoaded(false);
				item.remove();
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onPlayerPickupItem(PlayerPickupItemEvent e){
		if (ItemIDs.contains(e.getItem().getEntityId())){
			e.setCancelled(true);
		}
	}

	public void loadFromFile(){
		items = new ArrayList<ShowcaseItem>();
		try{
			String currentLine;
			BufferedReader io = new BufferedReader(new FileReader(itemFile));
			while ((currentLine = io.readLine()) != null){
				String[] s = currentLine.split(",");
				items.add(new ShowcaseItem(
					Material.getMaterial(Integer.parseInt(s[0])),
					Short.parseShort(s[1]),
					new Location(
						_coolStuff.getServer().getWorld(s[2]),
						Integer.parseInt(s[3]),
						Integer.parseInt(s[4]),
						Integer.parseInt(s[5])
					)
				));
			}
			io.close();
		} catch (Exception e) {
			items.clear();
			_coolStuff.getLogger().severe("[CASE] Failed to read item list!");
		}
	}
	
	public void saveToFile(){
		
		if (itemFile.exists()){
			itemFile.delete();
		}
		try {
			BufferedWriter io = new BufferedWriter(new FileWriter(itemFile));
			for (ShowcaseItem item : items){
				io.write(item.toString());
				io.newLine();
			}
			io.close();
		} catch (Exception e) {
			_coolStuff.getLogger().severe("[CASE] Failed to write item list!");
		}
		
	}
}
