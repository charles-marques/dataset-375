package com.instapaper.util;

import net.rim.device.api.system.PersistentObject;
import net.rim.device.api.system.PersistentStore;

public final class Configuration
{
    private static Configuration    _INSTANCE;
    private ConfigurationHashtable  configurationHashtable;
    private static final long       STOREHASH               = 0xd4d4d5b9cfa3ea7aL; // com.instapaper.util.Configuration
                                                                                   
    private static final int        KEY_NETWORK             = 1;
    private static final int        KEY_LOGGED_IN           = 2;
    private static final int        KEY_WIFI                = 3;
    private static final int        KEY_LAST_UPDATE_CHECK   = 4;
    private static final int        KEY_INSTAPAPER_USERNAME = 5;
    private static final int        KEY_INSTAPAPER_PASSWORD = 6;
    
    private static PersistentObject persist;
    
    private Configuration()
    {
        persist = PersistentStore.getPersistentObject(STOREHASH);
        Object contents = persist.getContents();
        if (contents instanceof ConfigurationHashtable)
        {
            try
            {
                configurationHashtable = (ConfigurationHashtable) contents;
            }
            catch (Exception ex)
            {
                configurationHashtable = new ConfigurationHashtable();
                persist.setContents(configurationHashtable);
                persist.commit();
            }
        }
        else
        {
            configurationHashtable = new ConfigurationHashtable();
            persist.setContents(configurationHashtable);
            persist.commit();
        }
    }
    
    public static Configuration getInstance()
    {
        if (_INSTANCE == null)
            _INSTANCE = new Configuration();
        return _INSTANCE;
    }
    
    public static void save()
    {
        persist.commit();
    }
    
    public int getConnectionMode()
    {
        return configurationHashtable.getInt(KEY_NETWORK, 0);
    }
    
    public void setConnectionMode(int mode)
    {
        configurationHashtable.putInt(KEY_NETWORK, mode);
    }
    
    public boolean isLoggedIn()
    {
        return configurationHashtable.getBoolean(KEY_LOGGED_IN, false);
    }
    
    public void setLoggedIn(boolean loggedIn)
    {
        configurationHashtable.putBoolean(KEY_LOGGED_IN, loggedIn);
    }
    
    public boolean isWiFiEnabled()
    {
        return configurationHashtable.getBoolean(KEY_WIFI, false);
    }
    
    public void setWiFiEnabled(boolean enabled)
    {
        configurationHashtable.putBoolean(KEY_WIFI, enabled);
    }
    
    public String getInstapaperUsername()
    {
        return configurationHashtable.getString(KEY_INSTAPAPER_USERNAME);
    }
    
    public void setInstapaperUsername(String username)
    {
        configurationHashtable.putString(KEY_INSTAPAPER_USERNAME, username);
    }
    
    public String getInstapaperPassword()
    {
        return configurationHashtable.getString(KEY_INSTAPAPER_PASSWORD);
    }
    
    public void setInstapaperPassword(String password)
    {
        configurationHashtable.putString(KEY_INSTAPAPER_PASSWORD, password);
    }
    
    public long getLastUpdateCheck()
    {
        return configurationHashtable.getLong(KEY_LAST_UPDATE_CHECK, 0);
    }
    
    public void setLastUpdateCheck(long time)
    {
        configurationHashtable.putLong(KEY_LAST_UPDATE_CHECK, time);
    }
}
