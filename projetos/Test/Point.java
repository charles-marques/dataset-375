package projet.jeu.bille;

public class Point {
	protected double x, y;
	
	Point(){
		x=0;
		y=0;
	}
	
	Point(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
}
