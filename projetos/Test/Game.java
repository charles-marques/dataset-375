package projet.jeu.bille;

import android.widget.Chronometer;

public class Game {
    final static int levelMax=3;
	private int largeur, hauteur;
	private GameRules gameRules;
	private Sounds sounds;
	private Levels levels;
	private LevelManager levelManager ;
	private Chronometer chronometer;


	Game(Sounds sounds, Levels levels,Chronometer chronometer, int largeur, int hauteur){
		this.gameRules = new GameRules();
        this.sounds = sounds;
        this.levels = levels;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.chronometer = chronometer;
		this.chronometer.setFormat("MM:SS");
        this.levelManager = new LevelManager(2,getSounds(),levels,
											largeur,hauteur);

    }

	//GETTERS-------------------------
	public GameRules getGameRules() {
		return gameRules;
	}
	public Sounds getSounds() {
		return sounds;
	}

	public Levels getLevels() {
		return levels;
	}

    public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public LevelManager getLevelManager() {
		return levelManager;
	}
	
	public Chronometer getChronometer() {
		return chronometer;
	}

	//SETTERS-------------------------	
	public void setLevelManager(LevelManager levelManager) {
		this.levelManager = levelManager;
	}
}
