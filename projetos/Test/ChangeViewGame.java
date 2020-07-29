package projet.jeu.bille;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ChangeViewGame extends Activity {//implements OnTouchListener{
	ViewSpace view;
	//Game game;
	public void onCreate(Bundle savedInstanceState) //A la   creation de la vue
    {
        super.onCreate(savedInstanceState);
     /*   Display  display= ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth(); 
        int height = display.getHeight();
        Log.d("WIDTH: ", String.valueOf(width));
        Log.d("HEIGHT: ", String.valueOf(height));*/
    //    	game = new Game(480, 800);
       // 	game.play();

 //       view = new ViewGame(this,game.levelManager.levelState.space);
        // Suppression de la barre de titre de l'application
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Affichage en plein ecran
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         //  view.setOnTouchListener(this);
           setContentView(view);
  //          view.runGame();
    	}

/*	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		switch (arg1.getAction()){
		case MotionEvent.ACTION_MOVE:
	        Log.d("X ", String.valueOf(arg1.getX()));
	        Log.d("Y ", String.valueOf(arg1.getY()));
			//game.levelManager.levelState.space.obstacles.setObstacles((int)arg1.getX(),(int)arg1.getY());
		break;
		}
		return true;
	}

*/
}
