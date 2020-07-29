package projet.jeu.bille;

public class PointSetable extends Point {
	
	PointSetable(){
		super();
	}
	
	PointSetable(int x, int y){
		super(x,y);
	}
	
	public void setXY(Point p){
		x=p.getX();
		y=p.getY();
	}
	public void setXY(int i, int j){
		x=i;
		y=j;
	}
}
