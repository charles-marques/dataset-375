package dataStructures;

import java.util.Vector;

import utils.Utils;

import interfaces.IShortestPath;

public class ShortestPath extends Trip implements IShortestPath {
	
	private Vector<Integer> speedLimit;

	public ShortestPath(GPSSignal signal) {
		super(signal);
		this.speedLimit = new Vector<Integer>();
		this.speedLimit.add(0);
	}	

	public ShortestPath(String format) {
		super(format);
		this.speedLimit = new Vector<Integer>();
	}
	
	public Vector<GPSSignal> getPath() {	
		return super.getPath();
	}

	public void addInstance(GPSSignal s) {
		super.addInstance(s);
	}

	public void setInstance(Integer i, GPSSignal s) {
		super.setInstance(i, s);
	}

	public GPSSignal getInstance(Integer i) {
		return super.getInstance(i);
	}

	public Integer size() {
		return super.size();
	}
	
	public void addInstance(GPSSignal s, Integer speedLimit) {			
		if(super.size() > 0 && super.getLast().equals(s))
			return;
		
		super.addInstance(s);
		this.speedLimit.add(speedLimit);
		assert(this.speedLimit.size() == super.size());
	}
	
	public Vector<Integer> getSpeedLimits() {
		return this.speedLimit;
	}

	public double getSpeedLimitAt(Integer i) { // km/h
		return Utils.convertKmh2ms(this.speedLimit.get(i)); // m/s
	}
	
	public String getFormat(){
		return super.getFormat();
	}
	
	public boolean isEmpty(){
		return super.isEmpty();
	}
	
	public String toString(){
		return "ShortestPath: "+super.toString();
	}

}
