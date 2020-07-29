package com.thezorro266.simpleregionmarket.signs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.thezorro266.simpleregionmarket.SimpleRegionMarket;
import com.thezorro266.simpleregionmarket.TokenManager;
import com.thezorro266.simpleregionmarket.Utils;
import com.thezorro266.simpleregionmarket.handlers.LanguageHandler;

/**
 * @author theZorro266
 * 
 */
public class TemplateLet extends TemplateMain {
	public TemplateLet(SimpleRegionMarket plugin, LanguageHandler langHandler, TokenManager tokenManager, String tplId) {
		super(plugin, langHandler, tokenManager);
		id = tplId;

		canLiveWithoutSigns = false;

		load();
	}

	@Override
	public void takeRegion(Player newOwner, String world, String region) {
		final ProtectedRegion protectedRegion = SimpleRegionMarket.wgManager.getProtectedRegion(Bukkit.getWorld(world), region);

		if (Utils.getEntryBoolean(this, world, region, "taken")) {
			final Player oldOwner = Bukkit.getPlayer(Utils.getEntryString(this, world, region, "owner"));
			final ArrayList<String> list = new ArrayList<String>();
			list.add(region);
			list.add(newOwner.getName());
			langHandler.playerNormalOut(oldOwner, "PLAYER.REGION.JUST_TAKEN_BY", list);
			releaseRegion(world, region);
		} else {
			// Clear Members and Owners
			protectedRegion.setMembers(new DefaultDomain());
			protectedRegion.setOwners(new DefaultDomain());
		}

		protectedRegion.getMembers().addPlayer(SimpleRegionMarket.wgManager.wrapPlayer(newOwner));

		Utils.setEntry(this, world, region, "taken", true);
		Utils.setEntry(this, world, region, "owner", newOwner.getName());
		Utils.setEntry(this, world, region, "expiredate", System.currentTimeMillis() + Utils.getEntryLong(this, world, region, "renttime"));

		final ArrayList<String> list = new ArrayList<String>();
		list.add(region);
		langHandler.playerNormalOut(newOwner, "PLAYER.REGION.RENT", list);

		tokenManager.updateSigns(this, world, region);
	}

	@Override
	public void releaseRegion(String world, String region) {
		final ProtectedRegion protectedRegion = SimpleRegionMarket.wgManager.getProtectedRegion(Bukkit.getWorld(world), region);

		// Clear Members and Owners
		protectedRegion.setMembers(new DefaultDomain());
		protectedRegion.setOwners(new DefaultDomain());

		Utils.setEntry(this, world, region, "taken", false);
		Utils.removeEntry(this, world, region, "owner");
		Utils.removeEntry(this, world, region, "expiredate");

		tokenManager.updateSigns(this, world, region);
	}

	@Override
	public boolean signCreated(Player player, String world, ProtectedRegion protectedRegion, Location signLocation, HashMap<String, String> input,
			String[] lines) {
		final String region = protectedRegion.getId();

		if (!entries.containsKey(world) || !entries.get(world).containsKey(region)) {
			final double priceMin = Utils.getOptionDouble(this, "price.min");
			final double priceMax = Utils.getOptionDouble(this, "price.max");
			double price;
			if (SimpleRegionMarket.econManager.isEconomy()) {
				if (input.get("price") != null) {
					try {
						price = Double.parseDouble(input.get("price"));
					} catch (final Exception e) {
						langHandler.playerErrorOut(player, "PLAYER.ERROR.NO_PRICE", null);
						return false;
					}
				} else {
					price = priceMin;
				}
			} else {
				price = 0;
			}

			if (priceMin > price && (priceMax == -1 || price < priceMax)) {
				final ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(priceMin));
				list.add(String.valueOf(priceMax));
				langHandler.playerErrorOut(player, "PLAYER.ERROR.PRICE_LIMIT", list);
				return false;
			}

			final long renttimeMin = Utils.getOptionLong(this, "renttime.min");
			final long renttimeMax = Utils.getOptionLong(this, "renttime.max");
			long renttime;
			if (!input.get("time").isEmpty()) {
				try {
					renttime = Utils.parseSignTime(input.get("time"));
				} catch (final Exception e) {
					langHandler.playerErrorOut(player, "PLAYER.ERROR.NO_RENTTIME", null);
					return false;
				}
			} else {
				langHandler.playerErrorOut(player, "PLAYER.ERROR.NO_RENTTIME", null);
				return false;
			}

			if (renttimeMin > renttime && (renttimeMax == -1 || renttime < renttimeMax)) {
				final ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(renttimeMin));
				list.add(String.valueOf(renttimeMax));
				langHandler.playerErrorOut(player, "PLAYER.ERROR.RENTTIME_LIMIT", null);
				return false;
			}

			Utils.setEntry(this, world, region, "price", price);
			Utils.setEntry(this, world, region, "renttime", renttime);
			Utils.setEntry(this, world, region, "account", player.getName());
			Utils.setEntry(this, world, region, "taken", false);
			Utils.removeEntry(this, world, region, "owner");
		}

		final ArrayList<Location> signLocations = Utils.getSignLocations(this, world, region);
		signLocations.add(signLocation);
		if (signLocations.size() == 1) {
			Utils.setEntry(this, world, region, "signs", signLocations);
		}

		tokenManager.updateSigns(this, world, region);
		return true;
	}

	@Override
	public Map<String, String> getReplacementMap(String world, String region) {
		final HashMap<String, String> replacementMap = (HashMap<String, String>) super.getReplacementMap(world, region);
		if (replacementMap != null) {
			replacementMap.put("time", Utils.getSignTime(Utils.getEntryLong(this, world, region, "renttime")));
			if (Utils.getEntry(this, world, region, "expiredate") != null) {
				replacementMap.put("timeleft", Utils.getSignTime(Utils.getEntryLong(this, world, region, "expiredate") - System.currentTimeMillis()));
			}
		}
		return replacementMap;
	}

	@Override
	public boolean canAddOwner() {
		return false;
	}

	@Override
	public void schedule(String world, String region) {
		if (Utils.getEntryBoolean(this, world, region, "taken")) {
			if (Utils.getEntryLong(this, world, region, "expiredate") < System.currentTimeMillis()) {
				if (Utils.getEntry(this, world, region, "owner") != null) {
					final String owner = Utils.getEntryString(this, world, region, "owner");
					final String account = Utils.getEntryString(this, world, region, "account");
					final Double price = Utils.getEntryDouble(this, world, region, "price");
					final Player player = Bukkit.getPlayer(owner);
					if (SimpleRegionMarket.econManager.econHasEnough(owner, price)) {
						if (SimpleRegionMarket.econManager.moneyTransaction(owner, account, price)) {
							if (player != null) {
								final ArrayList<String> list = new ArrayList<String>();
								list.add(region);
								langHandler.playerNormalOut(player, "PLAYER.REGION.AUTO_EXPANDED", list);
							}
							return;
						}
					}
					if (player != null) {
						final ArrayList<String> list = new ArrayList<String>();
						list.add(region);
						langHandler.playerNormalOut(player, "PLAYER.REGION.EXPIRED", list);
					}
				}
				releaseRegion(world, region);
			}
		}
	}
}
