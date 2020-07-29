package projet.jeu.bille;
import java.util.Vector;


public class ObstacleVector extends Obstacles{
	
	Vector <PointSetable> vectObs;
	
	@Override
	public void setObstacles(int x, int y) {
		
	}

	@Override
	public boolean isObstacle(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PointSetable collisionNewDirBall(Ball ball, PointSetable pointCollide) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isObstacle(int x, int y, int UNITE) {
		// TODO Auto-generated method stub
		return false;
	}
}
