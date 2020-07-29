package com.instapaper.menuitem;

import java.util.Hashtable;

import net.rim.blackberry.api.menuitem.ApplicationMenuItem;
import net.rim.device.api.i18n.ResourceBundle;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.util.DateTimeUtilities;

import com.instapaper.InstapaperResource;
import com.instapaper.google.analytics.GoogleAnalyticsTracker;
import com.instapaper.net.Network;
import com.instapaper.net.NetworkCallback;
import com.instapaper.ui.screen.SettingsScreen;
import com.instapaper.util.CheckUpdateThread;
import com.instapaper.util.Configuration;
import com.instapaper.util.Logger;
import com.instapaper.util.PostDataEncoder;

public class BrowserMenuItem extends ApplicationMenuItem
{
    private static Logger         log      = Logger.getInstance();
    private static ResourceBundle resource = null;
    static
    {
        resource = ResourceBundle.getBundle(InstapaperResource.BUNDLE_ID, InstapaperResource.BUNDLE_NAME);
    }
    
    public BrowserMenuItem(int order)
    {
        super(order);
    }
    
    public Object run(Object browserUrl)
    {
        if (browserUrl == null)
        {
            log.error("url is null");
            return null;
        }
        
        log.info("adding url: " + browserUrl);
        String url = "https://www.instapaper.com/api/add";
        Configuration config = Configuration.getInstance();
        
        String username = config.getInstapaperUsername();
        String password = config.getInstapaperPassword();
        
        if (username == null || username.length() == 0)
        {
            SettingsScreen screen = new SettingsScreen();
            UiApplication.getUiApplication().pushModalScreen(screen);
        }
        
        username = config.getInstapaperUsername();
        password = config.getInstapaperPassword();
        
        PostDataEncoder postdata = new PostDataEncoder();
        postdata.append("username", username);
        if (password != null && password.length() > 0)
            postdata.append("password", password);
        postdata.append("url", browserUrl.toString());
        
        Network network = new Network();
        network.doPost(url, postdata.getBytes(), new NetworkCallback()
        {
            public void onNetworkRequestSuccessful(String reply)
            {
                log.info("successfully added");
                log.debug(reply);
                
                GoogleAnalyticsTracker.getInstance().trackEvent("add", "success", null, -1);
                
                // NetworkResponse response = (NetworkResponse) reply;
                UiApplication.getUiApplication().invokeLater(new Runnable()
                {
                    public void run()
                    {
                        Dialog.inform(resource.getString(InstapaperResource.INSTAPAPER_ADDED));
                    }
                });
                
            }
            
            public void onNetworkRequestFailed(String message, Exception e)
            {
                log.exception("error adding url.\n\n" + message, e);
                GoogleAnalyticsTracker.getInstance().trackEvent("add", "failure", null, -1);
                UiApplication.getUiApplication().invokeLater(new Runnable()
                {
                    public void run()
                    {
                        int choice = Dialog.ask(resource.getString(InstapaperResource.INSTAPAPER_FAILED), new String[] { resource.getString(InstapaperResource.INSTAPAPER_FAILED_CHECK_SETTINGS),
                                resource.getString(InstapaperResource.CANCEL) }, 0);
                        if (choice == 0)
                        {
                            SettingsScreen screen = new SettingsScreen();
                            UiApplication.getUiApplication().pushScreen(screen);
                        }
                    }
                });
            }
        });
        
        return null;
    }
    
    public String toString()
    {
        return resource.getString(InstapaperResource.MENUITEM_SEND_TO_INSTAPAPER);
    }
    
}
