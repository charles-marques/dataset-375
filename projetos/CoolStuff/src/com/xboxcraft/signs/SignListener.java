package com.xboxcraft.signs;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class SignListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e){
		if (e.getPlayer() == null || (!e.getPlayer().isOp() && !PermissionsEx.getUser(e.getPlayer()).has("xboxcraft.signs.color"))){
			return;
		}
		
		String currentLine;
		String[] lines = e.getLines();
		for (int x = 0; x < lines.length; x++){
			currentLine = lines[x];
			
			for (int c = 0; c < currentLine.length() - 2; c++){
				if (currentLine.charAt(c) != '&'){
					continue;
				}
				
				char nextChar = currentLine.charAt(c + 1);					
				ChatColor color = ChatColor.getByChar(nextChar);
				if (color == null){
					c++;
					continue;
				}
				
				if (currentLine.length() >= 14){
					currentLine = currentLine.substring(0, c) + color + currentLine.substring(c + 2);
				}
				else{
					currentLine = currentLine.substring(0, c) + color + " " + currentLine.substring(c + 2);
					c++;
				}
			}
			e.setLine(x, currentLine.replace("&&", "&"));
		}
	}
}
