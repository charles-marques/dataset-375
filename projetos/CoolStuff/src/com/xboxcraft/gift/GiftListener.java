package com.xboxcraft.gift;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import com.xboxcraft.CoolStuff;

public class GiftListener implements Listener {
	
	private static Map<Player, Long> timeMap = new HashMap<Player, Long>();
	private static final Long giftDelay = 7200L;
	
	public GiftListener(final CoolStuff plugin){
		plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				Long currentTime = getServerTime();
				Iterator<Entry<Player, Long>> playerIterator = timeMap.entrySet().iterator();
				while (playerIterator.hasNext()){
					Entry<Player, Long> currentEntry = playerIterator.next();
					Player currentPlayer = currentEntry.getKey();
					if (!currentPlayer.isOnline()){
						playerIterator.remove();
					}
					else if(currentEntry.getValue() + giftDelay < currentTime && !CoolStuff.getEssentialsPlugin().getUser(currentPlayer).isAfk()){
						if (currentPlayer.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1)).size() == 0){
							currentPlayer.sendMessage(ChatColor.GREEN + "You have been awarded one gold ingot for playing!");
						}
						currentEntry.setValue(currentTime);
					}
				}
			}
		}, 100, 6000);
	}
	
	private Long getServerTime(){
		return System.currentTimeMillis() / 1000;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		
		if (timeMap.containsKey(e.getPlayer())){
			return;
		}
		
		timeMap.put(e.getPlayer(), getServerTime());
	}
	
}
