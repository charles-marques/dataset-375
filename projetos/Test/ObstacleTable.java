package projet.jeu.bille;

public class ObstacleTable extends Obstacles{
	private boolean grid[][];	
	
	public ObstacleTable(int x, int y) {
		grid= new boolean [x+1][y+1]; 
	}
	public boolean[][] getGrid(){
		return grid;
	}
	
	public boolean isObstacle(int x, int y){
		return grid[x][y];
	}
	public boolean isObstacle(int x, int y,int UNITE){
		return false;
	}
	@Override
	public void setObstacles(int x, int y) {
		grid[x][y]=true;
		
	}

	@Override
	public PointSetable collisionNewDirBall(Ball ball, PointSetable pointCollide) {
		return null;
	}
	@Override
	public String toString() {
		String s="";
		for(int i=0; i<grid.length;i++){
			for(int j=0; j<grid[0].length;j++){
				if(grid[i][j]){
					s+="i: "+i+" j:"+j;
				}
			}
		}
		return s;
	}
}
