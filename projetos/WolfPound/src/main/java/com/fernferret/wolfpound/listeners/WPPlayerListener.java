package com.fernferret.wolfpound.listeners;

import com.fernferret.wolfpound.AnimalAge;
import com.fernferret.wolfpound.WolfAggro;
import com.fernferret.wolfpound.WolfPound;
import com.fernferret.wolfpound.utils.SignTools;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class WPPlayerListener implements Listener {
    private final WolfPound plugin;

    public WPPlayerListener(final WolfPound plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.hasBlock() && event.getClickedBlock().getState() instanceof Sign && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Sign s = (Sign) event.getClickedBlock().getState();
            if (plugin.blockIsValidWolfSign(s) && plugin.getPermissions().hasPermission(p, WolfPound.PERM_USE, true)) {
                // We have a valid pound!
                if (checkSignParams(s, 1, p)) {
                    // We have valid pound params!
                    double price = getPrice(s, 1, p);
                    int item = getType(s, 1, p);
                    WolfAggro aggro = getAggro(s, 2, p);
                    if (plugin.bank.hasEnough(p, price, item)) {
                        if (plugin.spawnWolf(p, aggro, AnimalAge.Adult)) {
                            plugin.bank.take(p, price, item);
                        }
                    }
                }
            }
        }
    }

    private WolfAggro getAggro(Sign s, int l, Player p) {

        String line = s.getLine(l);
        if (line.matches("(?i)(.*friend.*)")) {
            return WolfAggro.FRIEND;
        }
        if (line.matches("(?i)(.*angry.*)")) {
            return WolfAggro.ANGRY;
        }
        // Default to neutral.
        return WolfAggro.NEUTRAL;
    }

    private boolean checkSignParams(Sign s, int l, Player p) {
        String line = s.getLine(l);
        String[] items = line.split(":");
        if (items.length == 0) {
            return true;
        }
        if (items.length == 1) {
            return SignTools.checkLeftSide(p, items[0]);
        }
        if (items.length == 2) {
            return SignTools.checkLeftSide(p, items[0]) && SignTools.checkRightSide(p, items[1]);
        }
        return false;
    }

    private Double getPrice(Sign s, int l, Player p) {
        String line = s.getLine(l);
        String[] items = line.split(":");
        if (items.length > 0) {
            return SignTools.getLeftSide(items[0]);
        }
        return 0.0;
    }

    private int getType(Sign s, int l, Player p) {

        String line = s.getLine(l);
        String[] items = line.split(":");
        if (items.length > 1) {
            return SignTools.getRightSide(items[1]);
        }
        return WolfPound.MONEY_ITEM_FOUND;

    }
}
