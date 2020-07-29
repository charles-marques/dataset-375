package projet.jeu.bille;

public class WinnerPoint extends Point{
	private int radius;

	public WinnerPoint(int x, int y) {
		super(x,y);
		radius=4;
	}
	
	public int getRadius(){
		return radius;
	}
}
