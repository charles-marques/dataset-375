package projet.jeu.bille;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProjetJeuBilleActivity extends Activity{
	Button vButton1, vButton2;
	 @Override
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);		

			vButton1 = (Button)findViewById(R.id.button1);
			vButton1.setOnClickListener(new OnClickListener()
			{
				@Override
			    public void onClick(View v)
			    {
			    	 Intent intent = new Intent(ProjetJeuBilleActivity.this,GameActivity.class);
		               startActivity(intent);
			    }
			});
			vButton2 = (Button)findViewById(R.id.button2);
			vButton2.setOnClickListener(new OnClickListener()
			{
			    public void onClick(View v)
			    {
			    	 Intent intent = new Intent(ProjetJeuBilleActivity.this, ChangeViewInstructions.class);
		                startActivity(intent);
			    }
			});
	
	 }
}