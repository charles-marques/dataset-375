package com.instapaper.ui.screen;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class StatusScreen extends PopupScreen
{
    private LabelField statusField = null;
    public StatusScreen(String message)
    {
        super(new VerticalFieldManager());
        
        statusField = new LabelField(message);
        add(statusField);
    }
    
    protected boolean keyChar(char c, int status, int time)
    {
        return true;
    }
    
    protected boolean navigationClick(int status, int time)
    {
        return true;
    }
}
