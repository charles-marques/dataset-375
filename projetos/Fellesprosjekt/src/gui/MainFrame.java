package gui;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.*;


/**
 * Class to handle whats on the motherfukkin screen for the Kalendersystem 
 */


public class MainFrame extends JFrame{

	public static JFrame frame = new JFrame("CalendarSystem 3.14");
	
	public static void main (String [] args){
		setFrameSpec();
		LoginWindow.createLoginWindow();
	}
	
	public static void createMainWindow(){
		frame.getContentPane().add(HomeScreen.getInstance());
	}
	
	public static void setFrameSpec(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth, screenHeight);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setMinimumSize(new Dimension(screenWidth/4, screenHeight/3));
		//frame.setLocation(screenWidth/3, screenHeight/3);
	    frame.setVisible(true);
	}
}
