/**************************************************************************
 * This file is part of MCbb.                                              
 * MCbb is free software: you can redistribute it and/or modify            
 * it under the terms of the GNU General Public License as published by    
 * the Free Software Foundation, either version 3 of the License, or       
 * (at your option) any later version.                                     
 * MCbb is distributed in the hope that it will be useful,                 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           
 * GNU General Public License for more details.                            
 * You should have received a copy of the GNU General Public License       
 * along with MCbb.  If not, see <http://www.gnu.org/licenses/>.           
 *************************************************************************/

package de.javakara.manf.listeners;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import de.javakara.manf.api.Config;
import de.javakara.manf.api.ConfigItems;
import de.javakara.manf.api.ForumSoftware;
import de.javakara.manf.api.Software;
import de.javakara.manf.api.User;
import de.javakara.manf.mcbb.MCbb;
import de.javakara.manf.mcbb.State;


	public class RegisteredPlayerListener implements Listener {
		public MCbb plugin;
		
		public RegisteredPlayerListener(MCbb instance) {
			plugin = instance;
		}
		
		//@ToDo(task = "Change EconomyManager to Correct values")
		@EventHandler(priority = EventPriority.NORMAL)
		public void onPlayerJoin(PlayerJoinEvent event) {
			Software u = ForumSoftware.getSoftwareObject();
			User user = ForumSoftware.getUser(event.getPlayer().getName());
			if(u.getRegistrationValue(user)){
				if(plugin.ac == State.On)
					if(MCbb.permission != null)
						System.out.println("Starting SQL-GroupSync");
						if(Config.getBoolean(ConfigItems.SYNCGROUPS_ENABLED)){
							String forumGroup = u.getForumGroup(user);
							if(!(MCbb.permission.playerInGroup((String)null, event.getPlayer().getName(), forumGroup))){
								p(event.getPlayer(),forumGroup);
								event.getPlayer().sendMessage("[MCbb] Your Group was set to: " +  forumGroup);
							}
						}
					//if(EconomyManager.isInitialised())
					//	EconomyManager.modifyPlayer(event.getPlayer().getName(), (u.getNewPosts() * plugin.getConfig().getInt("economy.moneyPerPost")));
				info(event.getPlayer());
			}
			if(!(event.getPlayer().hasPermission("mcbb.user.join") || event.getPlayer().hasPermission("mcbb.vip.override"))){
				event.getPlayer().kickPlayer(MCbb.lang.get("System.error.noPerm"));
			}
		}
		public  void info(Player p){
			if (p.hasPermission("mcbb.maintainer.update"))
				if(plugin.isOutdated())
					p.sendMessage(MCbb.lang.get("System.info.newUpdate"));
		}
		
		public void p(Player p,String o)
		{
			MCbb.permission.playerAddGroup((String)null,p.getName(),o);
			for(String s : MCbb.permission.getGroups())
			{
				if(!o.equals(s))
					if(MCbb.permission.playerInGroup((String)null,p.getName(), s)){
						System.out.println("|" + s + "|");
						MCbb.permission.playerRemoveGroup((String)null,p.getName(), s);
					}
					
			}
		}
}
