package com.instapaper.net;

public interface NetworkCallback
{
    public void onNetworkRequestSuccessful(final String reply);
    
    public void onNetworkRequestFailed(final String message, Exception e);
}
