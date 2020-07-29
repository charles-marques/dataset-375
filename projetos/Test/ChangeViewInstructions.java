package projet.jeu.bille;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChangeViewInstructions extends Activity{
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
	       TextView tv = new TextView(this);
	       tv.setText("Regles : But atteindre le point bleu avec la balle en creant des obstacles " +
	       		"pour que la balle puisse rebondir et changer de direction\n"
	    		 +" Pour commencer appuyer sur Jouer, puis sur la surface de jeu pour que la balle avance.\n" +
	    		 "Il faut maintenir le doigt appuyer sur l'écran et bouger pour dessiner des obstacles.");
	       setContentView(tv);
    }
}
