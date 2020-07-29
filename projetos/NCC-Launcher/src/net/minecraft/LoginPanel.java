package net.minecraft;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.UIManager;

import net.minecraft.laf.nccLaf;

public class LoginPanel extends JDialog
{
  public LoginPanel(Frame parent)
  {
      
   super(parent);
   setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
   
   setLocationRelativeTo(getParent());
   
   setTitle("Вход");
   setSize(320,240);
   
   try 
   {
       UIManager.setLookAndFeel(nccLaf.class.getCanonicalName());
   } 
   catch (Exception e){}
   
  }
}
