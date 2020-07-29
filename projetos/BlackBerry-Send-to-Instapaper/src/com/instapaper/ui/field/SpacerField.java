package com.instapaper.ui.field;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class SpacerField extends Field
{
    private int height = 0;
    
    public SpacerField(int height)
    {
        super();
        this.height = height;
    }
    
    protected void layout(int w, int h)
    {
        setExtent(w, this.height);
        
    }

    protected void paint(Graphics graphics)
    {
        // nothing to paint here...        
    }
    
}
