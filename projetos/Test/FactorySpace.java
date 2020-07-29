package projet.jeu.bille;

public class FactorySpace {
	
	boolean isNaive;
	
	public FactorySpace (boolean isNaive){
		this.isNaive=isNaive;
	}
	
	public Space getConfigurationSpace(int largeur, int hauteur,Ball bille,
            Point depart, WinnerPoint arrivee,Obstacles obs){
		if(isNaive){
			return new NaiveSpace(largeur,hauteur,bille,depart,arrivee,obs);
		}else{
			return new OptiSpace(largeur,hauteur,bille,depart,arrivee,obs);
		}
	}
}
