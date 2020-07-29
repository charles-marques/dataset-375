package info.bytecraft.basiccommands.commands;

import java.util.ArrayList;
import java.util.List;

import info.bytecraft.permissions.PPlayer;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class MobSpawn implements CommandExecutor {
	int x = 0;
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("cmob") && args.length >= 1){
			EntityType type = EntityType.valueOf(args[0].toUpperCase());
			if(type != null){
			if(cs instanceof Player){
				Player player = (Player)cs;
					if(new PPlayer(player).isAdmin()){
						if(args.length == 1){
							player.getWorld().spawnCreature(player.getLocation(), type);
							player.sendMessage(ChatColor.AQUA + "Spawning " + type.getName().toLowerCase().replace("_", " "));
							return true;
						}else if(args.length == 2){
							int value = Integer.parseInt(args[1]);
							while(x<value){
								player.getWorld().spawnCreature(player.getLocation(), type);
								x++;
							}
							player.sendMessage(ChatColor.AQUA + "Spawning " + type.getName().toLowerCase().replace("_", " "));
							return true;
						}else{
							return true;
						}
					}
				}
			}
		}else if(cmd.getName().equalsIgnoreCase("nuke") && args.length == 0){
			List<LivingEntity> list = new ArrayList<LivingEntity>();
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(new PPlayer(player).isAdmin()){
					for(Entity ent: player.getWorld().getEntities()){
						if(!(ent instanceof Player)){
							if(ent instanceof LivingEntity){
								if(!(ent instanceof Item)){
									LivingEntity entity = (LivingEntity)ent;
									if(!entity.isDead()){
										list.add(entity);
										if(entity.getLocation().distance(player.getLocation()) <= 30){
											//entity.damage(1000);
											entity.remove();
										}
									}
								}
							}
						}
					}
					player.sendMessage(ChatColor.YELLOW + "Killed " + list.size() + " mobs in your world");
				}
			}
		}
		return true;
	}
}
