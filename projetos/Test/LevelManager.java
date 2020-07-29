package projet.jeu.bille;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Chronometer;

public class LevelManager {

	private int levelActual;
	private Space space;
    private MediaPlayer soundLevel;
    private MediaPlayer soundCollision;
	
	public LevelManager(int level,Sounds sounds, Levels levels, int largeur, int hauteur){
        levelActual = level;
        this.soundLevel = sounds.initiateSoundLevel(levelActual);
        this.soundCollision = sounds.initiateSoundCollision();
        this.space = createSpace(levels.getInputStreamLevel(levelActual), largeur,hauteur);

	}
	/**
	 * Fichier (ordre de infos) : point Debut, Point arrivee, TabObstacle
	 * @param levelNumber
	 * @param largeur
	 * @param hauteur
	 * @return
	 */
  
	//GETTERS-------------------------    
	public Space getSpace() {
		
		return space;
	}
	public MediaPlayer getSoundLevel() {
		return soundLevel;
	}
	public MediaPlayer getSoundCollision() {
		return soundCollision;
	}
	public int getLevelActual() {
		return levelActual;
	}
	
	//SETTERS-----------------------------	
	public void setSpace(Space space) {
		this.space = space;
	}
	public void setLevelActual(int levelActual) {
		this.levelActual = levelActual;
	}
	public void setLevelActual() {
		this.levelActual++;
	}

    private Space createSpace(InputStream inputStream, int largeur, int hauteur) {
    	int UNITE = ViewSpace.UNITE;
        FactoryObstacles factoryObstacles = new FactoryObstacles(true);
        Obstacles obstacles = factoryObstacles.getConfigurationObstacle(largeur,hauteur);
        
		InputStream file = inputStream;
		BufferedReader br = new BufferedReader(new InputStreamReader(file),8 * 1024);
		Scanner scan = new Scanner(br);
		
		Point depart = new Point(Integer.parseInt(scan.nextLine()) * UNITE ,
								Integer.parseInt(scan.nextLine()) * UNITE);
		
		WinnerPoint arrivee = new WinnerPoint(Integer.parseInt(scan.nextLine()) * UNITE ,
												Integer.parseInt(scan.nextLine()) * UNITE);
		
		// 4 entiers : Bordure Haut, Droite, Basse, Gauche
		if(Integer.parseInt(scan.nextLine())==1){
			for(int i=0;i<largeur;i++){
				obstacles.setObstacles(i,0);
			}
		}
		if(Integer.parseInt(scan.nextLine())==1){
			for(int i=0;i<hauteur;i++){
				obstacles.setObstacles(largeur-1,i);
			}
		}
		if(Integer.parseInt(scan.nextLine())==1){
			for(int i=0;i<largeur;i++){
				obstacles.setObstacles(i,hauteur-1);
			}
		}
		if(Integer.parseInt(scan.nextLine())==1){
			for(int i=0;i<hauteur;i++){
				obstacles.setObstacles(0,i);
			}
		}
		String temp;
		int Largeur=0;
		int j=0;
		while(scan.hasNextLine()&&j<hauteur){
			temp = scan.nextLine();
			if(temp.length()<largeur)Largeur = temp.length(); else Largeur =largeur;
			for(int i=0; i<Largeur; i++){
				if(Integer.parseInt(temp.substring(i, i+1))==1){
					obstacles.setObstacles(i, j);
				}
			}
			j++;
		}
 
        FactorySpace factorySpace = new FactorySpace(true);
        Space parcours = factorySpace.getConfigurationSpace(largeur,hauteur,new Ball(), depart, arrivee, obstacles);
        return parcours;
    }
}
