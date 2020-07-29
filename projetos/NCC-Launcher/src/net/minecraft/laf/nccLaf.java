package net.minecraft.laf;

import javax.swing.plaf.basic.BasicLookAndFeel;

public class nccLaf extends BasicLookAndFeel 
{
    // Название LaF
    public String getName() 
    {
        return "NCC_Style";
    }

    // Уникальный ID для LaF
    public String getID()
    {
        return getName ();
    }

    // Описание LaF
    public String getDescription() 
    {
        return "LaF for Next Century Craft Launcher.";
    }

    // Привязан ли данный LaF к текущей платформе
    public boolean isNativeLookAndFeel() 
    {
        return false;
    }

    // Поддерживается ли данный LaF на текущей платформе
    public boolean isSupportedLookAndFeel() 
    {
        return true;
    }
    
}
