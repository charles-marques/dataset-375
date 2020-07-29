package projet.jeu.bille;

import java.util.Vector;

import android.util.Log;

public class NaiveSpace extends Space{
	PointSetable lastCollide;
	public NaiveSpace(int largeur, int hauteur,Ball bille,
            Point depart, WinnerPoint arrivee,Obstacles obs) {
		super(largeur,hauteur,bille,depart,arrivee,obs);
		lastCollide= new PointSetable(0,0);
	}

	@Override
	public boolean collideBallObstacles() {
		int unite =ViewSpace.UNITE;
		int i_init = (int)(ball.getX()-ball.getRadius());
		int j_init = (int)(ball.getY()-ball.getRadius());
		if(i_init<0) i_init=0;
		if(j_init<0) j_init=0;

		int i_final = (int)(ball.getX()+ball.getRadius());
		int j_final = (int)(ball.getY()+ball.getRadius());
		if(i_final>largeur*unite) i_final=largeur*unite;
		if(j_final>hauteur*unite) j_final=hauteur*unite;
		
		for(int i=i_init ; i<i_final ; i++){
			for(int j=j_init ; j<j_final ; j++){
				if(Formules.distance2point(ball.getX(),ball.getY(),i,j)<=ball.getRadius()){
					if( obstacles.isObstacle(i/(unite), j/(unite))){
						if(lastCollide.getX()==i/unite && lastCollide.getY()==j/unite)
							return false;
						lastCollide.setXY(i/unite,j/unite);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public Vector<Point> collidePoint() {
		//Use it , after collide() ->true
		boolean exist;
		int unite =ViewSpace.UNITE;
		Vector<Point> vectCollide = new Vector<Point>();
		int i_init = (int)(ball.getX()-ball.getRadius());
		int j_init = (int)(ball.getY()-ball.getRadius());
		if(i_init<0) i_init=0;
		if(j_init<0) j_init=0;

		int i_final = (int)(ball.getX()+ball.getRadius());
		int j_final = (int)(ball.getY()+ball.getRadius());
		if(i_final>largeur*unite) i_final=largeur*unite;
		if(j_final>hauteur*unite) j_final=hauteur*unite;
		
		for(int i=i_init ; i<i_final ; i++){
			for(int j=j_init ; j<j_final ; j++){
				exist=false;
				if(Formules.distance2point(ball.getX(),ball.getY(),i,j)<=ball.getRadius()){
					if( obstacles.isObstacle(i/(unite), j/(unite))){
						for(int r=0; r<vectCollide.size();r++){
							Log.d("IF",""+vectCollide.elementAt(r).getX()/unite+"=="+i/unite+","+vectCollide.elementAt(r).getY()/unite+"=="+j/unite);
							if(vectCollide.elementAt(r).getX()/unite==i/unite && vectCollide.elementAt(r).getY()/unite ==j/unite){
								Log.d("exist","TRUE");
								exist=true;
							}
						}
						if(!exist){
							Log.d("jajoute",""+i/unite+","+j/unite);
							vectCollide.add(new Point(i,j));
						}
					}
				}
			}
		}
		return vectCollide;
	}
	
	@Override
	public Point newDir() {
		Point vect = new Point(0,0);
	//	int unite =ViewSpace.UNITE;
		double XTemp, YTemp;
		Vector<Point> vectCollide = collidePoint();
		Log.d("newDir","taille : "+vectCollide.size());
		for(int i=0; i<vectCollide.size();i++){
			Log.d("vect["+i+"]",""+vectCollide.elementAt(i).getX()+","+vectCollide.elementAt(i).getY());
		}
		for(int i=0; i<vectCollide.size();i++){
			XTemp = ball.getX()-vectCollide.elementAt(i).getX();
			YTemp = ball.getY()-vectCollide.elementAt(i).getY();
			vect = Formules.vect2Point(new Point(XTemp,YTemp),vect);
			vect = Formules.vectUnitaire(vect);
		}
		return vect;
	}
}
