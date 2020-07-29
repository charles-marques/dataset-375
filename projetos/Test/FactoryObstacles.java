package projet.jeu.bille;

public class FactoryObstacles {

	boolean isObstacleTable;
	public FactoryObstacles (boolean isObstacleTable){
		this.isObstacleTable=isObstacleTable;
	}
	
	public Obstacles getConfigurationObstacle(int width,int height){
		if(isObstacleTable){
			return new ObstacleTable(width, height);
		}else{
			return new ObstacleVector();
		}
	}
}

