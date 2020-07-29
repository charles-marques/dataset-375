package com.xboxcraft;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.earth2me.essentials.Essentials;
import com.xboxcraft.anvil.AnvilListener;
import com.xboxcraft.bed.BedListener;
import com.xboxcraft.blacklist.BlacklistListener;
import com.xboxcraft.derp.DerpCommand;
import com.xboxcraft.derp.HerpCommand;
import com.xboxcraft.fakeop.FakeOpCommand;
import com.xboxcraft.gift.GiftListener;
import com.xboxcraft.guesttp.GuestTPCommand;
import com.xboxcraft.hats.HatCommand;
import com.xboxcraft.honeypot.HoneyPotListener;
import com.xboxcraft.honeypot.HoneyPotCommand;
import com.xboxcraft.invdump.InvDumpCommand;
import com.xboxcraft.joinleave.JoinLeaveListener;
import com.xboxcraft.mobitem.MobItemCommand;
import com.xboxcraft.mobitem.MobItemListener;
import com.xboxcraft.residence.ResidenceExtender;
import com.xboxcraft.showcase.ShowcaseListener;
import com.xboxcraft.signs.SignListener;
import com.xboxcraft.stats.StatsCommand;
import com.xboxcraft.tips.TipsManager;
import com.xboxcraft.vote.VoteCommand;
import com.xboxcraft.vote.VoteManager;
import com.xboxcraft.xboxmb.XMBCommand;


public class CoolStuff extends JavaPlugin {
	
	private static Essentials essentialsPlugin;
	private TipsManager tipsManager;
	
	public static Essentials getEssentialsPlugin(){
		return essentialsPlugin;
	}
	
	public void onEnable(){
		
		// Fetch required plugins
		essentialsPlugin = (Essentials)this.getServer().getPluginManager().getPlugin("Essentials");
		tipsManager = new TipsManager(this);
		
		// Register Commands
		this.getCommand("mi").setExecutor(new MobItemCommand());
		this.getCommand("vote").setExecutor(new VoteCommand());
		this.getCommand("invdump").setExecutor(new InvDumpCommand());
		this.getCommand("g").setExecutor(new GuestTPCommand());
		this.getCommand("tips").setExecutor(tipsManager);
		this.getCommand("hat").setExecutor(new HatCommand(this));
		this.getCommand("herp").setExecutor(new HerpCommand());
		this.getCommand("derp").setExecutor(new DerpCommand());
		this.getCommand("hp").setExecutor(new HoneyPotCommand());
		this.getCommand("xmb").setExecutor(new XMBCommand(this));
		this.getCommand("fakeop").setExecutor(new FakeOpCommand());
		this.getCommand("stats").setExecutor(new StatsCommand());
		
		// Register Events
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(new MobItemListener(this), this);
		pluginManager.registerEvents(new BedListener(), this);
		pluginManager.registerEvents(new BlacklistListener(), this);
		pluginManager.registerEvents(new AnvilListener(), this);
		pluginManager.registerEvents(new GiftListener(this), this);
		pluginManager.registerEvents(new JoinLeaveListener(), this);
		//pluginManager.registerEvents(new PVPListener(), this);
		pluginManager.registerEvents(new VoteManager(this), this);
		pluginManager.registerEvents(new SignListener(), this);
		pluginManager.registerEvents(new ShowcaseListener(this), this);
		pluginManager.registerEvents(new ResidenceExtender(this), this);
		pluginManager.registerEvents(new HoneyPotListener(this), this);
		//pluginManager.registerEvents(new TreeListener(), this);
		
		this.startCreativeTimeTask();
		
		this.getLogger().info("Initialized");
	}
	
	public void onDisable(){
		ShowcaseListener.removeAllEntities();
		HoneyPotListener.savePlayerList();
	}
	
	private void startCreativeTimeTask(){
		if (this.getServer().getWorld("creative") != null){
			this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					getServer().getWorld("creative").setTime(3000);
				}
			}, 0, 6000);
		}
	}
}
