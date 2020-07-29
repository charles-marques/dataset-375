package projet.jeu.bille;

import android.content.Context;
import android.media.MediaPlayer;


public class Sounds {
  Context context;
	public Sounds(Context context){
	  	this.context = context;
	}
    public MediaPlayer initiateSoundLevel(int level){
    	MediaPlayer soundLevel;
    	switch(level){
    	case(1):
            soundLevel = MediaPlayer.create(context,R.raw.sound_level1);
    		break;
    /*	case(2):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level2);
            break;
    	case(3):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level3);
            break;
    	case(4):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level4);
            break;
    	case(5):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level5);
            break;
    	case(6):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level6);
            break;
    	case(7):
            soundLevel = MediaPlayer.create(this,R.raw.sound_level7);
            break;*/
    	default :
            soundLevel = MediaPlayer.create(context,R.raw.sound_level1);
    	}
	return soundLevel;
	}
    
    public MediaPlayer initiateSoundCollision(){ 
    	return MediaPlayer.create(context,R.raw.sound_collision2); 	
    }
}
