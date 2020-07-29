import java.awt.*;
import javax.swing.JFrame;

public class Screen {
	
	private GraphicsDevice vc;
	
	public Screen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	// to make a window screen, remove the setFullScreen method and 
	// give the window a size by calling window.setSize()
	// afterwards, call window.setVisible(true) to make the window appear
	public void setFullScreen(DisplayMode dm, JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if (dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			}catch(Exception ex) {}
		}
	}
	
	// not sure why we need this yet
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	// to close a windowed screen, remove the getFullScreen methods and add 
	// an argument to this method of type JFrame.
	// by doing this, we are able to manipulate the windowed screen
	// if the window is not null, dispose of it
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if(w != null) {
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}
	
}
