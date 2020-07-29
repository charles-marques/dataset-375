package com.xboxcraft.residence;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.bekvon.bukkit.residence.event.ResidenceEnterEvent;
import com.bekvon.bukkit.residence.event.ResidenceLeaveEvent;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.earth2me.essentials.Essentials;
import com.xboxcraft.CoolStuff;

public class ResidenceExtender implements Listener {

	private static Essentials essPlugin;
	
	public ResidenceExtender(CoolStuff coolStuff){
		FlagPermissions.addResidenceOnlyFlag("powertools");
		essPlugin = CoolStuff.getEssentialsPlugin();
	}
	
	@EventHandler
	public void onResidenceEnter(ResidenceEnterEvent e){
		if (!e.getResidence().getPermissions().has("powertools", true)){
			essPlugin.getUser(e.getPlayer()).setPowerToolsEnabled(false);
		}
	}
	
	@EventHandler
	public void onResidenceLeave(ResidenceLeaveEvent e){
		if (!e.getResidence().getPermissions().has("powertools", true) && PermissionsEx.getUser(e.getPlayer()).has("essentials.powertool")){
			essPlugin.getUser(e.getPlayer()).setPowerToolsEnabled(true);
		}
	}
}
