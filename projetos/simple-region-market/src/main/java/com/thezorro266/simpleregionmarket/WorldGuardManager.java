package com.thezorro266.simpleregionmarket;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.thezorro266.simpleregionmarket.handlers.LanguageHandler;

public class WorldGuardManager {
	private final LanguageHandler langHandler;

	public WorldGuardManager(LanguageHandler langHandler) {
		this.langHandler = langHandler;
	}

	public WorldGuardPlugin getWorldGuard() {
		final Plugin wgPlugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

		if (wgPlugin == null || !(wgPlugin instanceof WorldGuardPlugin) || !wgPlugin.isEnabled()) {
			langHandler.consoleOut("MAIN.ERROR.NO_WORLDGUARD");
			return null;
		}

		return (WorldGuardPlugin) wgPlugin;
	}

	public void addMember(ProtectedRegion protectedRegion, Player player) {
		if (protectedRegion != null && player != null) {
			protectedRegion.getMembers().addPlayer(getWorldGuard().wrapPlayer(player));
		}
	}

	public void addOwner(ProtectedRegion protectedRegion, Player player) {
		if (protectedRegion != null && player != null) {
			protectedRegion.getOwners().addPlayer(getWorldGuard().wrapPlayer(player));
		}
	}

	public void removeMember(ProtectedRegion protectedRegion, Player player) {
		if (protectedRegion != null && player != null) {
			protectedRegion.getMembers().removePlayer(getWorldGuard().wrapPlayer(player));
		}
	}

	public void removeOwner(ProtectedRegion protectedRegion, Player player) {
		if (protectedRegion != null && player != null) {
			protectedRegion.getOwners().removePlayer(getWorldGuard().wrapPlayer(player));
		}
	}

	public ProtectedRegion getProtectedRegion(World worldWorld, String region) {
		if (worldWorld != null) {
			final WorldGuardPlugin wgPlugin = getWorldGuard();
			if (wgPlugin != null) {
				return wgPlugin.getRegionManager(worldWorld).getRegion(region);
			}
		}
		return null;
	}

	public LocalPlayer wrapPlayer(Player player) {
		final WorldGuardPlugin wgPlugin = getWorldGuard();
		if (wgPlugin != null) {
			return wgPlugin.wrapPlayer(player);
		}
		return null;
	}
}
