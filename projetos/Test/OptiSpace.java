package projet.jeu.bille;

public class OptiSpace extends Space{

	//ObstacleVector obstacles;
	public OptiSpace(int largeur, int hauteur,Ball bille,
            Point depart, WinnerPoint arrivee,Obstacles obs) {
		super(largeur,hauteur,bille,depart,arrivee,obs);
	}
	@Override
	public boolean collideBallObstacles() {
		return false;
	}
	@Override
	public Point newDir() {
		return null;
	}
}
