package projet.jeu.bille;

public class Formules {
	public static double distance2point(double x1, double y1,double x2,double y2){
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
	}
	public static double distance2point(Point a, Point b){
		return Math.sqrt(Math.pow(a.getX()-b.getX(),2)+Math.pow(a.getY()-b.getY(),2));
	}
	public static Point vect2Point(Point a,Point b){
		return new Point(a.getX()-b.getX(),a.getY()-b.getY());
	}
	
	public static Point vectUnitaire(Point p){
		double diviseur = Math.sqrt(Math.pow(p.x,2)+Math.pow(p.y,2));
		return new Point(p.getX()/diviseur,p.getY()/diviseur);
	}
}
