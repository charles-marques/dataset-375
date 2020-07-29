package com.instapaper;

import net.rim.blackberry.api.menuitem.ApplicationMenuItemRepository;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.util.DateTimeUtilities;

import com.instapaper.google.analytics.CustomVariable;
import com.instapaper.google.analytics.Event;
import com.instapaper.google.analytics.GoogleAnalyticsTracker;
import com.instapaper.menuitem.BrowserMenuItem;
import com.instapaper.ui.screen.AddScreen;
import com.instapaper.ui.screen.SettingsScreen;
import com.instapaper.util.CheckUpdateThread;
import com.instapaper.util.Configuration;
import com.instapaper.util.DeviceUtil;
import com.instapaper.util.Logger;

public class Instapaper extends UiApplication
{
    
    private static Logger log = Logger.getInstance();
    private static BrowserMenuItem browserMenuItem = null;
    
    public static void main(String[] args)
    {
        if(args != null && args.length == 1 && args[0].equals("auto"))
        {
            log.debug("auto start");
            addMenuItem();
        }
        else
        {
            log.debug("gui start");
            Instapaper instapaper = new Instapaper();
            instapaper.enterEventDispatcher();
        }
        
    }
    
    public Instapaper()
    {
        super();
        
        Configuration config = Configuration.getInstance();
        if (config.getInstapaperUsername() != null && config.getInstapaperUsername().length() > 0)
            pushScreen(new AddScreen());
        else
            pushScreen(new SettingsScreen());
        
        GoogleAnalyticsTracker tracker = GoogleAnalyticsTracker.getInstance();
        Event event = tracker.createPageView("/app_loaded");
        event.setCustomVariable(new CustomVariable(1, "OS", DeviceUtil.getDeviceOS(), CustomVariable.SCOPE_SESSION));
        event.setCustomVariable(new CustomVariable(2, "Application Version", DeviceUtil.getAppVersion(), CustomVariable.SCOPE_SESSION));
        tracker.addEvent(event);
        
        long last = config.getLastUpdateCheck();
        long now = System.currentTimeMillis();
        long diff = now - last;
        
        log.debug("last update: " + last + "\ndiff: " + diff);
        if (diff > DateTimeUtilities.ONEDAY || DeviceInfo.isSimulator())
        {
            config.setLastUpdateCheck(now);
            Configuration.save();
            CheckUpdateThread checkUpdateThread = new CheckUpdateThread();
            checkUpdateThread.start();
        }
    }

    private static void addMenuItem()
    {
        log.debug("creating menu item");
        browserMenuItem = new BrowserMenuItem(300000);
        
        ApplicationMenuItemRepository repository = ApplicationMenuItemRepository.getInstance();
        log.debug("adding menu item");
        repository.addMenuItem(ApplicationMenuItemRepository.MENUITEM_BROWSER, browserMenuItem);
    }
    
}
