package com.fernferret.wolfpound.commands;

import com.fernferret.wolfpound.WPWorld;
import com.fernferret.wolfpound.WolfPound;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionDefault;

import java.util.List;

enum SetProperties {
    currency, curr, type, price, limit, aggro, agro
}

public class SetPropertyCommand extends WolfPoundCommand {

    public SetPropertyCommand(WolfPound plugin) {
        super(plugin);
        this.setName("Sets a property.");
        this.setCommandUsage("/wp set" + ChatColor.GREEN + " {PROPERTY} {VALUE} " + ChatColor.GOLD + "[all | WORLD]");
        this.setArgRange(2, 3);
        this.addKey("wp set");
        this.addKey("wpset");
        this.addKey("wps");
        this.setPermission("wolfpound.admin", "Sets various properties of wolves in different worlds.", PermissionDefault.OP);
        this.addCommandExample("/wp set" + ChatColor.GREEN + " price 1.2");
        this.addCommandExample("/wp set" + ChatColor.GREEN + " curr -1 all");
        this.addCommandExample("/wp set" + ChatColor.GREEN + " limit 10 w:MyWorld");
        this.addCommandExample("/wp set" + ChatColor.GREEN + " aggro friend");
        this.addCommandExample("/wp set" + ChatColor.GREEN + " aggro angry w:MyWorld");
        this.addCommandExample("/wp set" + ChatColor.GREEN + " aggro neutral");
    }

    @Override
    public void runCommand(CommandSender sender, List<String> args) {
        WPWorld world = this.plugin.getWorldManager().getGlobalWorld();
        if (args.size() == 3 && !args.get(2).equalsIgnoreCase("all")) {
            world = this.plugin.getWorldManager().getWorld(args.get(2));
        }

        if (validateCommand(args.get(0), args.get(1), world)) {
            if (args.size() < 3 && !(sender instanceof Player)) {
                sender.sendMessage("You must enter a world or all from the command line.");
                return;
            }
            String worldName = args.get(2);
            world = this.plugin.getWorldManager().getWorld(worldName);
            if (world.equals(this.plugin.getWorldManager().getGlobalWorld())) {
                worldName = "all worlds";
            }
            sender.sendMessage(ChatColor.GREEN + "SUCCESS! " + ChatColor.WHITE + args.get(0) + " was successfully set to " + args.get(1) + " in " + worldName + "!");
        } else {
            sender.sendMessage("Sorry you can't set " + ChatColor.AQUA + args.get(0) + ChatColor.WHITE + " to " + ChatColor.GREEN + args.get(1));
        }
    }

    private boolean validateCommand(String cmd, String value, WPWorld world) {
        try {
            SetProperties prop = SetProperties.valueOf(cmd);
            if (checkCurrency(value, world, prop)) {
                return true;
            }
            if (checkPrice(value, world, prop)) {
                return true;
            }
            if (checkLimit(value, world, prop)) {
                return true;
            }
            if (checkAggro(value, world, prop)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkPrice(String value, WPWorld world, SetProperties prop) {
        if (prop == SetProperties.price) {
            try {
                double price = Double.parseDouble(value);
                return world.setPrice(price);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private boolean checkLimit(String value, WPWorld world, SetProperties prop) {
        if (prop == SetProperties.limit) {
            try {
                int limit = Integer.parseInt(value);
                return world.setLimit(limit);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private boolean checkAggro(String value, WPWorld world, SetProperties prop) {
        if (prop == SetProperties.aggro || prop == SetProperties.agro) {
            try {
                return world.setAggro(value);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    private boolean checkCurrency(String value, WPWorld world, SetProperties prop) {
        if (prop == SetProperties.curr || prop == SetProperties.currency || prop == SetProperties.type) {
            try {
                int curr = Integer.parseInt(value);
                return world.setCurrency(curr);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

}
