import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList scenes;
	private int sceneIndex;
	private long movieTime;
	private long totalTime;
	
	//Constructor
	public Animation() {
		scenes = new ArrayList();
		totalTime = 0;
		start();
		
	}
	
	//add scene to array list and set time for each scene
	//synchronized - only run a method once at a time, regardless of how many threads can run it
	public synchronized void addScene(Image i, long t) {
		totalTime += t;
		scenes.add(new OneScene(i, totalTime));
	}
	
	//start animation from the beginning
	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}
	
	//change scenes
	public synchronized void update(long timePassed) {
		if (scenes.size()>1) {
			movieTime += timePassed;
			if (movieTime >= totalTime) {
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime) {
				sceneIndex++;
			}
		}
	}
	
	//get animations current scene(aka image)
	public synchronized Image getImage() {
		if(scenes.size() == 0) {
			return null;
		}else {
			return getScene(sceneIndex).pic;
		}
	}
	
	//get scene
	private OneScene getScene(int x) {
		return (OneScene)scenes.get(x);
	}
	
	//private inner class//
	
	private class OneScene{
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime) {
			this.pic = pic;
			this.endTime = endTime;
		}
		
	}
	
	
	

}
