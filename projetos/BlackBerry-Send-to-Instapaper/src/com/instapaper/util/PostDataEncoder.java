package com.instapaper.util;

public class PostDataEncoder
{
    private StringBuffer dataBuffer = null;
    
    public PostDataEncoder()
    {
        dataBuffer = new StringBuffer();
    }
    
    public void append(String name, String value)
    {
        if (dataBuffer.length() > 0)
            dataBuffer.append('&');
        dataBuffer.append(name);
        dataBuffer.append('=');
        dataBuffer.append(URLUTF8Encoder.encode(value));
    }
    
    public byte[] getBytes()
    {
        return dataBuffer.toString().getBytes();
    }
    
    public void setData(String data)
    {
        dataBuffer = new StringBuffer(data);
    }
    
    public int size()
    {
        return dataBuffer.length();
    }
    
    public String toString()
    {
        return dataBuffer.toString();
    }
    
}
