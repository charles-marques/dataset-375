package projet.jeu.bille;

	public class Ball extends PointSetable{
		private int radius;
		private double dirX,dirY;
		
		//CONSTRUCTORS
		public Ball(){
			
			radius=10;
			dirX=1;
			dirY=0;
		}
		public Ball(int x, int y){
			super(x,y);
			radius=10;
			dirX=1;
			dirY=0;
		}
		
		//GETTERS
		public int getRadius() {
			return radius;
		}

		public double getDirX() {
			return dirX;
		}
		public double getDirY() {
			return dirY;
		}
		//SETTERS
		public void setDir(int dirx,int diry){
			dirX=dirx;
			dirY=diry;
		}
		public void setDir(Point dir){
			dirX=dir.getX();
			dirY=dir.getY();
		}
//---------------------------------------------------
		public boolean isDir(double x,double y){
			return x==dirX && y==dirY;
		}
		public boolean isDir(Point p){
			return p.getX()==dirX && p.getY()==dirY;
		}
		public void update(){
			x+=dirX;
			y+=dirY;
		}
		public PointSetable determineDir(){
			int dirXTemp = 0;
			int dirYTemp = 0;
			if(dirX==1) dirXTemp=-1;
			if(dirX==-1) dirXTemp=1;
			if(dirY==1) dirYTemp=-1;
			if(dirY==-1) dirYTemp=1;
			return new PointSetable(dirXTemp,dirYTemp);
		}
	}

