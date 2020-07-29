package racingManager.gui;


import javax.swing.SwingUtilities;

public class Test {

	public static void main(String[] args){
		//Use the event dispatch thread to build the UI for thread-safety.
		//steht auch in Java Swing API!
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				new MainFrame();
			}
		});
	}
}
