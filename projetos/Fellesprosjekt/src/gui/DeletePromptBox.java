package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class DeletePromptBox extends JLabel{
	
	private JLabel message;
	private JButton cancelButton, yesButton;
	
	public static void main(String[]args){
		JFrame frame = new JFrame("Bekreftelse av sletting");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new DeletePromptBox());
		frame.pack();

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth/4, screenHeight/6);
		frame.setMinimumSize(new Dimension(screenWidth/10, screenHeight/10));
		frame.setLocation(screenWidth/3, screenHeight/3);
		
		frame.setVisible(true);
	}
	
	public static void createDeletePromptBox(){
		JFrame frame = new JFrame("Bekreftelse av sletting");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(new DeletePromptBox());
		frame.pack();

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth/4, screenHeight/6);
		frame.setMinimumSize(new Dimension(screenWidth/10, screenHeight/10));
		frame.setLocation(screenWidth/3, screenHeight/3);
		
		frame.setVisible(true);
	}
	
	public DeletePromptBox(){
		setLayout(new MigLayout("wrap 2, fill"));
		message = new JLabel("Er du sikker på at du vil slette denne hendelsen?");
		message.setForeground(Color.BLUE.darker());
		
		cancelButton = new JButton("Avbryt");
		yesButton = new JButton("Ja");
		
		cancelButton.addActionListener(new cancelAction());
		yesButton.addActionListener(new agreeAction());
		
		add(message,"span");
		add(cancelButton,"split 2, span, align right");
		add(yesButton);
	}
	
	class cancelAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Container frame = cancelButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
	
	class agreeAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//TODO add deletefunction 
			Container frame = yesButton.getParent();
			do frame = frame.getParent();
			while (!(frame instanceof JFrame));
			((JFrame) frame).dispose();
		}
	}
}
