package com.instapaper.net;

import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.system.EventLogger;

import com.instapaper.util.Configuration;
import com.instapaper.util.Logger;

public final class NetworkConfig
{
    private static Logger   log             = Logger.getInstance();
    public static final int MODE_AUTODETECT = 0;
    public static final int MODE_BIS        = 1;
    public static final int MODE_BES        = 2;
    public static final int MODE_TCP        = 3;
    
    public static final String[] choices = new String[]{"Auto", "BIS", "BES", "TCP"};
    
    private static String   connectionParameters;
    private static int      connectionMode;
    private static String   mdsConnection   = ";deviceside=false";
    private static String   tcpConnection   = ";deviceside=true";
    private static String   bisConnection   = ";deviceside=false;ConnectionType=mds-public";
    
    public static void init()
    {
        if (DeviceInfo.isSimulator())
        {
            setMode(MODE_TCP);
            return;
        }
        
        int m = Configuration.getInstance().getConnectionMode();
        
        if (m > MODE_AUTODETECT)
        {
            setMode(m);
        }
        else
        {
            setMode(autodetect());
        }
    }
    
    private static int autodetect()
    {
        // if you have BES service book, use it
        try
        {
            ServiceBook sb = ServiceBook.getSB();
            ServiceRecord[] records = sb.findRecordsByCid("IPPP");
            if (records != null)
            {
                for (int i = records.length - 1; i >= 0; i--)
                {
                    ServiceRecord rec = records[i];
                    if (rec.isValid() && !rec.isDisabled())
                    {
                        if (rec.getEncryptionMode() == ServiceRecord.ENCRYPT_RIM)
                        {
                            log.info("Auto Detect detected BES");
                            return MODE_BES;
                        }
                        else
                        {
                            log.info("Auto Detect detected BIS");
                            return MODE_BIS;
                        }
                    }
                }
            }
            log.info("Auto Detect detected TCP");
            return MODE_TCP;
        }
        catch (Exception e)
        {
            // no permissions to explore service book. fall to TCP, I guess
            log.log("couldn't net-detect", e, EventLogger.WARNING);
            return MODE_TCP;
        }
    }
    
    private static void setMode(int mode)
    {
        log.debug("Setting mode to " + mode);
        switch (mode)
        {
            case MODE_BES:
                connectionParameters = mdsConnection;
                break;
            case MODE_TCP:
                connectionParameters = tcpConnection;
                break;
            case MODE_BIS:
                connectionParameters = bisConnection;
                break;
        }
        
        connectionMode = mode;
    }
    
    public static String getConnectionParameters()
    {
        if (connectionParameters == null)
        {
            init();
        }
        
        return connectionParameters;
    }
    
    public static String getConnectionParameters(int mode)
    {
        switch (mode)
        {
            case MODE_BES:
                return mdsConnection;
            case MODE_TCP:
                return tcpConnection;
            case MODE_BIS:
                return bisConnection;
            case MODE_AUTODETECT:
                return getConnectionParameters(autodetect());
            default:
                return null;
        }
    }
    
    public static int getConnectionMode()
    {
        return connectionMode;
    }
}
