package projet.jeu.bille;

public abstract class Obstacles{
	
	public abstract void setObstacles(int x, int y);
	public abstract boolean isObstacle(int x, int y);
	public abstract boolean isObstacle(int x, int y,int UNITE);	
	public abstract PointSetable collisionNewDirBall(Ball ball, PointSetable pointCollide);
	
}
