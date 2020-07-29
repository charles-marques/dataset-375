package projet.jeu.bille;

public class GameRules {

	/**
	 * Renvoie false quand la balle est totalement plus visible de l'espace
	 * @param ball
	 * @param largeur
	 * @param longueur
	 * @return
	 */
	public static boolean inSpace(Ball ball , int largeur , int longueur){
		return ( ball.getX() <= largeur*ViewSpace.UNITE + ball.getRadius()
				&& ball.getY() <= longueur*ViewSpace.UNITE + ball.getRadius()
				&& ball.getX() >= -ball.getRadius()
				&& ball.getY() >= -ball.getRadius()
			);
	}
	/**
	 * Renvoie false quand la balle n'est plus visible entierement dans l'espace
	 * @param ball
	 * @param largeur
	 * @param longueur
	 * @return
	 */
	public static boolean inSpaceObstacles(Ball ball , int largeur , int longueur){
		return ( ball.getX() < largeur * ViewSpace.UNITE - ball.getRadius()
				&& ball.getY() < longueur * ViewSpace.UNITE - ball.getRadius()
				&& ball.getX() > ball.getRadius()
				&& ball.getY() > ball.getRadius()
			);
	}
	
	/**
	 * Une fois le level reussi => savoir combien de point le joueur a obtenu
	 * @param time
	 * @return
	 */
	public static int pointWinScore(int time){
		int pointWin=0;
		if(time>=0 && time <5)
			pointWin=1000;
		else if(time>=5 && time <8)
			pointWin=950;
		else if(time>=8 && time <10)
			pointWin=900;
		else if(time>=10 && time <15)
			pointWin=800;
		else if(time>=15 && time <20)
			pointWin=700;
		else if(time>=20 && time <30)
			pointWin=400;
		else if(time>=30 && time <50)
			pointWin=100;
		else pointWin =50;
		return pointWin;
	}
	
	//Si la ball touche le point gagnant
	public static boolean isLevelWin(Ball ball, WinnerPoint point){
		return Formules.distance2point(ball,point)<ball.getRadius()+point.getRadius();
	}
}
