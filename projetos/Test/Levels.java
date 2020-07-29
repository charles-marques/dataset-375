package projet.jeu.bille;

import java.io.InputStream;
import android.content.Context;

public class Levels{
	Context context;
	
    Levels(Context context){
    	this.context = context;
    }

    public InputStream getInputStreamLevel(int level){
    	InputStream levelStream;
    	switch(level){
    	case(1):
    		levelStream = context.getResources().openRawResource(R.raw.level_1);
    		break;
    	case(2):
    		levelStream = context.getResources().openRawResource(R.raw.level_2);
    		break;
    	case(3):
    		levelStream = context.getResources().openRawResource(R.raw.level_3);
    		break;
    	case(4):
    		levelStream = context.getResources().openRawResource(R.raw.level_4);
    		break;
    	case(5):
    		levelStream = context.getResources().openRawResource(R.raw.level_5);
    		break;
    	case(6):
    		levelStream = context.getResources().openRawResource(R.raw.level_6);
    		break;
    	case(7):
    		levelStream = context.getResources().openRawResource(R.raw.level_7);
    		break;
    	default:
    		levelStream = context.getResources().openRawResource(R.raw.level_1);
    		break;
    	}
    	return levelStream;
    }
}
