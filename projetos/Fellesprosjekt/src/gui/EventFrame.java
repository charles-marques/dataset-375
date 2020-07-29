package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import model.Event;

public class EventFrame {
	public JFrame frame = new JFrame();
	public String whatWindow = "";
	private ListModel listModel;
	private Event event;
	
	EventFrame(){};
	
	public EventFrame(String whatWindow, Event event2) {
		this.whatWindow = whatWindow;
		this.event = event2;
	}
	
	public EventFrame(String whatWindow, ListModel lModel){
		this.whatWindow = whatWindow;
		this.listModel = lModel;
	}
	
	
	public void showFrame(){
		
		if(whatWindow.equals("NewApointment")){
			frame.add(new NewApointment());
			frame.setTitle("Ny Avtale");
			setFrameSpec();
		}else if(whatWindow.equals("EditSharedApointment")){
			frame.add(new EditSharedApointment(event));
			frame.setTitle("Rediger Delt Avtale");
			setFrameSpec();
		}else if(whatWindow.equals("ConsiderApointment")){
			frame.add(new ConsiderApointment(event));
			frame.setTitle("Vurder Avtale");
			setFrameSpec();
		}else if(whatWindow.equals("ParticipantList")){
			frame.add(new ParticipantList(listModel));
			frame.setTitle("Liste over nadre brukere");
			setFrameSpec();
		}
		
	}
	
	public void setFrameSpec(){
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setSize(screenWidth/4, screenHeight/2);
		frame.setMinimumSize(new Dimension(screenWidth/4, screenHeight/3));
		frame.setLocation(screenWidth/3, screenHeight/3);
	    frame.setVisible(true);
	}

}
