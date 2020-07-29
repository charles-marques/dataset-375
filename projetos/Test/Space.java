package projet.jeu.bille;

import android.widget.Chronometer;

public abstract class Space {
	int largeur;
	int hauteur;
	protected Ball ball;
    
    protected Point depart;
    
    protected WinnerPoint arrivee;

    protected Obstacles obstacles;
    
//	protected Chronometer chronometer;

    public Space(int largeur, int hauteur,Ball bille,Point depart,WinnerPoint arrivee,Obstacles obs) {
    	this.ball = bille;
        this.depart = depart;
        this.arrivee = arrivee;
        this.ball.setXY(depart);
        this.obstacles=obs;
            this.largeur = largeur;
            this.hauteur = hauteur;
    }
	public abstract boolean collideBallObstacles();
	
	public abstract Point newDir();
	
	///GETTERS
    public Ball getBall() {
            return ball;
    }

    public Point getDepart() {
            return depart;
    }

    public WinnerPoint getArrivee() {
            return arrivee;
    }
    public Obstacles getObstacle() {
        return obstacles;
    }
}
