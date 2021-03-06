package com.bignerdranch.franklin.roger.pair;

import java.io.Serializable;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.net.Uri;

import android.util.Log;

public class ServerDescription implements Serializable {
    public static final String TAG = "ServerDescription";

    public static final long serialVersionUID = 0l;
    
    protected String hostAddress;
    protected String name;

    public String getHostAddress() {
        return this.hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getInetAddress() {
        try {
            return InetAddress.getByName(getHostAddress());
        } catch (UnknownHostException uhe) {
            Log.e(TAG, "do not want", uhe);
            return null;
        }
    }

    public String getIntentServerAddress(String deviceId) {
        return "http://" + getHostAddress() + ":8081/streamIntents?deviceId=" + Uri.encode(deviceId);
    }

    public String getServerAddress() {
        return "http://" + getHostAddress() + ":8082/";
    }

    public String getApkAddress() {
        return "http://" + getHostAddress() + ":8081/get?hash=%1$s";
    }

    @Override
    public String toString() {
        return getName();
    }
    
    @Override
    public int hashCode() {
    	return hostAddress.hashCode() + name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
    	
    	if (o instanceof ServerDescription) {
    		ServerDescription other = (ServerDescription) o;
    		if (other.getHostAddress().compareTo(hostAddress) == 0
    				&& other.getName().compareTo(name) == 0) {
    			return true;
    		}
    	}
    	
    	return false;
    }
}
